package widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


import com.just.agentweb.AgentWebView;

public class BaseWebView extends AgentWebView {
    public BaseWebView(@NonNull Context context) {
        super(context);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
