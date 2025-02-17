package smartrefresh.layout.listener;

import android.content.Context;
import android.support.annotation.NonNull;


import smartrefresh.layout.api.RefreshHeader;
import smartrefresh.layout.api.RefreshLayout;


/**
 * 默认Header创建器
 * Created by scwang on 2018/1/26.
 */
public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout);
}
