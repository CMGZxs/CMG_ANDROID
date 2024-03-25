package smartrefresh.layout.listener;

import android.content.Context;
import android.support.annotation.NonNull;


import smartrefresh.layout.api.RefreshLayout;


/**
 * 默认全局初始化器
 * Created by scwang on 2018/5/29 0029.
 */
public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout layout);
}
