package common.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.just.agentweb.AgentWeb;

import common.callback.SdkInteractiveParam;
import common.callback.SdkParamCallBack;
import common.constants.Constants;
import common.model.SdkUserInfo;
import common.model.ShareInfo;
import common.model.ShareModel;
import common.utils.ImageUtils;
import common.utils.PersonInfoManager;
import common.utils.SPUtils;
import common.utils.SavePhoto;
import common.utils.SystemUtil;
import common.utils.ToastUtils;
import io.reactivex.functions.Consumer;
import ui.activity.EasyWebActivity;
import ui.activity.VideoHomeActivity;
import common.model.SdkUserInfo.DataDTO.LoginSysUserVoDTO;
import utils.UUIDUtils;

public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;
    private TextView titleView;
    private SdkUserInfo.DataDTO userInfo;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }

    public void setTitleView(TextView textView) {
        this.titleView = textView;
    }

    public void setUserInfo(SdkUserInfo.DataDTO info) {
        this.userInfo = info;
    }

    /**
     * 设置标题
     */
    @JavascriptInterface
    public void setTitle(String titleName) {
        if (null != titleView) {
            titleView.setText(titleName);
        }
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void monitorLifeCycle() {
        ((Activity) context).finish();
    }

    /**
     * 获取设备id
     *
     * @return
     */
    @JavascriptInterface
    public String getDeviceId() {
        return UUIDUtils.deviceUUID();
    }

    /**
     * 获取用户信息
     */
    @JavascriptInterface
    public String getUserInfo() {
        String str = JSON.toJSONString(userInfo);
        Log.e("AndroidInterface", "传递的用户信息：" + str);
        return str;
    }


    /**
     * 打开新的webview
     */
    @JavascriptInterface
    public void jumpToNativePage(String url) {
        Intent intent = new Intent(context, EasyWebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    /**
     * 分享
     */
    @JavascriptInterface
    public void share(String shareInfo) {
        JSONObject jsonObject = JSONObject.parseObject(shareInfo);
        ShareInfo share = new ShareInfo();
        share.setShareBrief(jsonObject.getString("platform"));
        share.setShareUrl(jsonObject.getString("shareUrl"));
        share.setShareTitle(jsonObject.getString("shareTitle"));
        share.setShareImage(jsonObject.getString("shareImage"));
        share.setShareBrief(jsonObject.getString("shareBrief"));
        SdkInteractiveParam.getInstance().shared(share);
    }

    /**
     * 保存图片
     */
    @JavascriptInterface
    public void savePhoto(final String imageUrl) {
//        String[] writePerMissionGrop = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        new RxPermissions((FragmentActivity) context).requestEach(writePerMissionGrop).subscribe(new Consumer<Permission>() {
//            @Override
//            public void accept(Permission permission) throws Exception {
//                if (permission.granted) {
//                    ImageUtils.saveImageToGallery(context, SavePhoto.getBitmap(imageUrl));
//                } else {
//                    ToastUtils.showShort("请在设置中手动开启写入SD卡权限");
//                }
//            }
//        });
    }

    /**
     * 打开视频
     */
    @JavascriptInterface
    public void openVideo(String contentId) {
        Intent intent = new Intent(context, VideoHomeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 打开登录页
     */

}
