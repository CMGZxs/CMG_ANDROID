package smartrefresh.layout.wrapper;

import android.annotation.SuppressLint;
import android.view.View;


import smartrefresh.layout.api.RefreshFooter;
import smartrefresh.layout.simple.SimpleComponent;

/**
 * 刷新底部包装
 * Created by scwang on 2017/5/26.
 */
@SuppressLint("ViewConstructor")
public class RefreshFooterWrapper extends SimpleComponent implements RefreshFooter {

    public RefreshFooterWrapper(View wrapper) {
        super(wrapper);
    }

}
