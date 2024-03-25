package common.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import common.constants.Constants;

public class ImageUtils {
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    private static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();
    public final static String SAVE_REAL_PATH =  SDCardUtils.getSDCardPath() + "DCIM";

    public static boolean saveBitmap2file(Bitmap bmp, Context context, Handler handler, CallBackFunction function) {

        String savePath;
        String fileName = generateFileName() + ".JPEG";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = SAVE_REAL_PATH;
        } else {
            Message message = new Message();
            message.what = 2;
            message.obj = function;
            handler.sendMessage(message);
            Looper.prepare();
            ToastUtils.showShort("保存失败");
            Looper.loop();
            return false;
        }
        File filePic = new File(savePath + fileName);
        try {
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Message message = new Message();
            message.what = 1;
            message.obj = function;
            handler.sendMessage(message);
            Looper.prepare();
            ToastUtils.showShort("保存成功,位置:" + filePic.getAbsolutePath());
            Looper.loop();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    filePic.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + savePath + fileName)));
        return true;
    }
}