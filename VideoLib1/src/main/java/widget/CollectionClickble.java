package widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.pasc.lib.videolib.R;


public class CollectionClickble extends ClickableSpan implements View.OnClickListener{
    private final View.OnClickListener mListener;
    private Context context;

    public CollectionClickble(View.OnClickListener mListener, Context mContext) {
        this.mListener = mListener;
        this.context = mContext;
    }

    @Override
    public void onClick(@NonNull View view) {
        mListener.onClick(view);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(context.getResources().getColor(R.color.white));
        ds.setUnderlineText(false);
    }
}
