package widget;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import android.support.v7.widget.RecyclerView;


import com.pasc.lib.videolib.R;

import java.util.List;

import common.callback.ShareAdapter;
import common.model.ShareModel;

public class SharePopupWindow extends PopupWindow {
    private RelativeLayout rlytInput;
    private View mPopView;
    private Context context;
    private RecyclerView recyclerView;
    private int resLayout;
    private List<ShareModel> shareDatas;

    public SharePopupWindow(Context context, List<ShareModel> shareBeanList, int layout) {
        this.context = context;
        this.resLayout = layout;
        this.shareDatas = shareBeanList;
        init(shareDatas, resLayout);
    }


    private void init(List<ShareModel> shareDatas, int layout) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mPopView = inflater.inflate(R.layout.layout_share_window, null);
        recyclerView = mPopView.findViewById(R.id.ryContent);
        recyclerView.setAdapter(new ShareAdapter(layout, shareDatas));
        mPopView.findViewById(R.id.tvDismiss).setOnClickListener(onClickListener);
    }

    /**
     * 设置窗口的相关属性
     */
    @SuppressLint("InlinedApi")
    private void setPopupWindow() {
        this.setContentView(mPopView);// 设置View
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setBackgroundDrawable(null);
        setAnimationStyle(R.style.take_popwindow_anim);
        //是否具有获取焦点的能力
        this.setFocusable(true);

    }


    public void showAtLocation(View rootView) {
        showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvDismiss) {
                dismiss();
            }
        }
    };
}
