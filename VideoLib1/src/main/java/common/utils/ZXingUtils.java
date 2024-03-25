package common.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public final class ZXingUtils {
    private ZXingUtils(){
    }

    private static final String TAG = ZXingUtils.class.getSimpleName();

    //解析二维码

    private static final Map<DecodeHintType,Object> DECODE_HINTS = null;        //解析配置

    public interface QRScanCallBack{
        /**二维码扫描结果回调
         * succeess 是否成功
         * result   识别数据
         * e        异常信息
         */
        void onReslt(boolean success, Result result, Exception e);
    }
        /**
         *解析二维码图片
         * bitmap          待解析的二维码图片
         * qrScanCallBack  解析结果回调
         */
         public static void decodeQRCode(final Bitmap bitmap,final QRScanCallBack qrScanCallBack){
             if (bitmap == null){
                 if (qrScanCallBack != null){
                     qrScanCallBack.onReslt(false,null,new Exception("bitmap is null"));
                 }
                 return;
             }

             //开始解析
//             new Thread(){
//                 @Override
//                 public void run() {
//                     super.run();
//                 }
//             };
             try {
                 int width = bitmap.getWidth();
                 int height = bitmap.getHeight();
                 int[] pixels = new int[width * height];
                 bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                 RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
                 Result result = new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(source)), DECODE_HINTS);
                 //触发回调
                 if (qrScanCallBack != null){
                     qrScanCallBack.onReslt((result != null),result,null);
                 }
             }catch (Exception e){
                 e.printStackTrace();
                 //触发回调
                 if (qrScanCallBack != null){
                     qrScanCallBack.onReslt(false,null,e);
                 }
             }
         }

         //获取结果

        /**
        * 获取扫描结果
        * result 识别数据
        * return 扫描结果数据
        * */
         public static String getResultData(final Result result){
             return (result != null) ? result.getText() : null;
         }

    /**
     * 解析二维码图片工具类
     */
    public static void analyzeBitmap(Activity activity, final String path, final AnalyzeCallback analyzeCallback) {
        Glide.with(activity).load(path).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (resource instanceof GifDrawable) {
                    return;
                }
                BitmapDrawable bd = (BitmapDrawable) resource;
               Bitmap mBitmap = bd.getBitmap();
                MultiFormatReader multiFormatReader = new MultiFormatReader();

                // 解码的参数
                Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
                // 可以解析的编码类型
                Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
                if (decodeFormats == null || decodeFormats.isEmpty()) {
                    decodeFormats = new Vector<BarcodeFormat>();

                    // 这里设置可扫描的类型，我这里选择了都支持
                    decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
                    decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
                    decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
                }
                hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
                // 设置继续的字符编码格式为UTF8
                // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
                // 设置解析配置参数
                multiFormatReader.setHints(hints);

                // 开始对图像资源解码
                Result rawResult = null;
                try {
                    rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(mBitmap))));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (rawResult != null) {
                   decodeQRCode(mBitmap, new QRScanCallBack() {
                       @Override
                       public void onReslt(boolean success, Result result, Exception e) {
                           if (null!=analyzeCallback){
                               analyzeCallback.onAnalyze(success,result);
                           }
                       }
                   });
                }
            }
        });
    }

    /**
     * 解析二维码结果
     */
    public interface AnalyzeCallback {

        void onAnalyze(boolean success, Result result);
    }

         //编码
    private static final Map<EncodeHintType,Object> ENCODE_HINTS = new EnumMap<>(EncodeHintType.class);

         static {
             //编码类型
             ENCODE_HINTS.put(EncodeHintType.CHARACTER_SET,"utf-8");
             //指定纠错等级，纠错级别（ L 7%、M 15%、Q 25%、H 30% ）
             ENCODE_HINTS.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
             //设置二维码边的空度，非负数
             ENCODE_HINTS.put(EncodeHintType.MARGIN,0);
         }


}
