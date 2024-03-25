package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.pasc.lib.videolib.R;

public class LoadingView extends RelativeLayout {


    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        //这里的原理就是简单的动态布局加入
        View view = View.inflate(context, R.layout.layout_view_loading, this);
        ImageView imgLoading = view.findViewById(R.id.imgLoading);
        if (null != context) {
            Glide.with(context)
                    .load(R.drawable.szrm_sdk_tiny_loading)
                    .into(imgLoading);
        }
    }
}
