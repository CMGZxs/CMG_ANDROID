package smartrefresh.layout.listener;

import android.content.Context;
import android.support.annotation.NonNull;


import smartrefresh.layout.api.RefreshFooter;
import smartrefresh.layout.api.RefreshLayout;


/**
 * 默认Footer创建器
 * Created by scwang on 2018/1/26.
 */
public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);
}
