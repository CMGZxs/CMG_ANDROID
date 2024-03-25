package utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import android.support.annotation.DimenRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
    /**
     * 展示圆形图片
     *
     * @param imageView
     * @param uri
     * @param cache     缓存
     */
    public static void displayCircle(ImageView imageView, String uri, boolean cache, Context ctx) {
        try {
            if (imageView != null) {
                Glide.with(ctx).load(uri)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .thumbnail(0.5f)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
