package widget;

import static common.constants.Constants.success_code;
import static common.utils.AppInit.appId;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pasc.lib.videolib.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebView;
import com.just.agentweb.WebViewClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import common.callback.JsonCallback;
import common.constants.Constants;
import common.http.ApiConstants;
import common.model.AppSystemModel;
import common.model.DeviceIdModel;
import common.model.JumpToNativePageModel;
import common.model.MechanismModel;
import common.model.SdkUserInfo;
import common.utils.AppInit;
import common.utils.ButtonSpan;
import common.utils.ImageUtils;
import common.utils.PersonInfoManager;
import common.utils.SavePhoto;
import common.utils.SystemUtil;
import common.utils.ToastUtils;
import custompop.CustomPopWindow;
import io.reactivex.functions.Consumer;
import rxpermission.RxPermissions;
import ui.activity.LoginActivity;
import ui.activity.VideoHomeActivity;
import ui.activity.WebActivity;
import utils.UUIDUtils;

public class SzrmWebView extends AgentWebView {
    private static final String TAG = "SzrmWebView";
    private LinearLayout imgBack;
    private TextView webTitle;
    private LinearLayout iconShare;
    private ImageView imgClose;
    public static final int LOGIN_REQUEST_CODE = 315;
    private AgentWeb mAgentWeb;
    private WebViewClient mWebViewClient;
    private BridgeWebView mBridgeWebView;
    private SdkUserInfo.DataDTO userInfo;
    private Handler handler;
    private CustomPopWindow sharePop;
    private View sharePopView;
    private ImageView shareWxBtn;
    private ImageView shareCircleBtn;
    private ImageView shareQqBtn;
    private JSONObject dataObject;
    private JumpToNativePageModel param;
    private String logoUrl;
    private String appName;
    private String cfgStr; //获取的机构数据json字符串
    private String intent = "0";
    private String mechanismId; //机构Id
    private String intentUrl;

    public SzrmWebView(Context context, ViewGroup view, ViewGroup.LayoutParams lp) {
        super(context);
        init(context, view, lp);
    }

    private void init(Context context, ViewGroup view, ViewGroup.LayoutParams lp) {
//        param = (JumpToNativePageModel) context.getIntent().getSerializableExtra("param");
//        intent = context.getIntent().getStringExtra("intent");
//        if (null != param && !TextUtils.isEmpty(param.getNewsLink())) {
//            iconShare.setVisibility(View.VISIBLE);
//        } else {
//            iconShare.setVisibility(View.GONE);
//        }
        getCfg(context, view, lp);
    }

    private void initBridge(Context context, ViewGroup view, ViewGroup.LayoutParams lp) {
        mBridgeWebView = new BridgeWebView(context);

        mAgentWeb = AgentWeb.with((Activity) context)
                .setAgentWebParent(view, lp)
                .useDefaultIndicator(-1, 2)
                .setWebViewClient(getWebViewClient((Activity) context))
                .setWebView(mBridgeWebView)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//               .setDownloadListener(mDownloadListener) 4.0.0 删除该API
                .createAgentWeb()
                .ready()
                .go(intentUrl);

        if (null != mBridgeWebView) {
            setBridge((Activity) context, view);
        }

        handler = new Handler() {
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                super.dispatchMessage(msg);
                int id = msg.what;
                if (id == 1) {
                    CallBackFunction callBackFunction = (CallBackFunction) msg.obj;
                    callBackFunction.onCallBack(PersonInfoManager.getInstance().getSzrmUserModel());
                } else {
                    CallBackFunction callBackFunction = (CallBackFunction) msg.obj;
                    callBackFunction.onCallBack(PersonInfoManager.getInstance().getSzrmUserModel());
                }
            }
        };
    }

    private WebViewClient getWebViewClient(final Activity context) {
        return new WebViewClient() {
            BridgeWebViewClient mBridgeWebViewClient = new BridgeWebViewClient(mBridgeWebView);

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mBridgeWebViewClient.shouldOverrideUrlLoading(view, url)) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (mBridgeWebViewClient.shouldOverrideUrlLoading(view, request.getUrl().toString())) {
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                String str1 = "javascript: window.userInfo = '";
                String str2 = "javascript: window.deviceId = '";
                String str3 = "javascript: window.appVersion = '";
                String str4 = "'";
                String str5 = "javascript: window.orgInfo = '";
                mAgentWeb.getJsAccessEntrace().callJs(str1 + PersonInfoManager.getInstance().getSzrmUserModel() + str4);
                mAgentWeb.getJsAccessEntrace().callJs(str2 + UUIDUtils.deviceUUID() + str4);
                mAgentWeb.getJsAccessEntrace().callJs(str3 + getAppInfo(context) + str4);
                mAgentWeb.getJsAccessEntrace().callJs(str5 + cfgStr + str4);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mBridgeWebViewClient.onPageFinished(view, url);

            }
        };
    }

    /**
     * 设置jsBridge
     */
    private void setBridge(final Activity context, final ViewGroup view) {
        mBridgeWebView.registerHandler("MJBrigeHandler", new BridgeHandler() {
            @Override
            public void handler(final String data, final CallBackFunction function) {
                final JSONObject jsonObject = JSON.parseObject(data);
                String methodName = jsonObject.getString("methodName");
                dataObject = jsonObject.getJSONObject("data");
                if (TextUtils.equals(methodName, Constants.SDK_JS_SETTITLE)) { //设置标题
                    String title = dataObject.getString("title");
                    webTitle.setText(title);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_MONITORLIFECYCLE)) { //返回
                    context.finish();
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETDEVICEID)) { //获取设备id
                    DeviceIdModel model = new DeviceIdModel();
                    model.setDeviceId(UUIDUtils.deviceUUID());
                    String deviceIdStr = JSON.toJSONString(model);
                    function.onCallBack(deviceIdStr);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETUSERINFO)) { //获取用户信息
                    String userInfoStr = PersonInfoManager.getInstance().getSzrmUserModel();
                    function.onCallBack(userInfoStr);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_JUMPTONATIVEPAGE)) { //跳转新webView
                    Intent intent = new Intent(context, WebActivity.class);
                    JumpToNativePageModel model = JSON.parseObject(JSON.toJSONString(dataObject), JumpToNativePageModel.class);
                    intent.putExtra("param", model);
                    intent.putExtra("intent", "1");
                    context.startActivity(intent);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_SHARE)) { //分享
                    sharePop(context, view);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_SAVEPHOTO)) { //保存图片

                    String[] writePerMissionGrop = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    new RxPermissions((FragmentActivity) context).request(writePerMissionGrop).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                final String url = dataObject.getString("url");
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ImageUtils.saveBitmap2file(SavePhoto.getBitmap(url), context
                                                , handler, function);
                                    }
                                }).start();
                            } else {
                                function.onCallBack("0");
                                ToastUtils.showShort("请在设置中手动开启写入SD卡权限");
                            }
                        }
                    });
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GOLOGING)) { //跳转登录
//                    SdkInteractiveParam.getInstance().toLogin();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("mechanismId", mechanismId);
                    context.startActivityForResult(intent, LOGIN_REQUEST_CODE);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_OPENVIDEO)) { //打开视频
                    Intent intent = new Intent(context, VideoHomeActivity.class);
                    intent.putExtra("contentId", dataObject.getString("contentId"));
                    intent.putExtra("logoUrl", logoUrl);
                    intent.putExtra("appName", appName);
                    context.startActivity(intent);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETAPPVERSION)) { //获取设备版本号等信息
                    function.onCallBack(getAppInfo(context));
                }
            }
        });
    }

    /**
     * 获取系统的一些信息
     *
     * @return
     */
    private String getAppInfo(Activity context) {
        AppSystemModel appSystemModel = new AppSystemModel();
        appSystemModel.setOsName("Android");
        appSystemModel.setBrand(SystemUtil.getDeviceBrand());
        appSystemModel.setOsVersion(String.valueOf(SystemUtil.getVersionCode(context)));
        appSystemModel.setAppVersion(SystemUtil.getVersionName(context));
        String appInfo = JSON.toJSONString(appSystemModel);
        return appInfo;
    }

    /**
     * 分享弹窗
     */
    private void sharePop(Activity context, ViewGroup view) {
        if (null == sharePop) {
            sharePop = new CustomPopWindow.PopupWindowBuilder(context)
                    .setView(sharePopView)
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, ButtonSpan.dip2px(150))
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .create()
                    .showAtLocation(view, Gravity.BOTTOM, 0, 0);
        } else {
            sharePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * 获取机构
     */
    private void getCfg(final Context context, final ViewGroup view, final ViewGroup.LayoutParams lp) {
        OkGo.<MechanismModel>get(ApiConstants.getInstance().getCfg())
                .tag("cfg")
                .params("appId", appId)
                .execute(new JsonCallback<MechanismModel>(MechanismModel.class) {
                    @Override
                    public void onSuccess(Response<MechanismModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                            MechanismModel.DataDTO model = response.body().getData();
                            if (null != model) {
                                cfgStr = JSON.toJSONString(model);
                                logoUrl = model.getLogo();
                                intentUrl = model.getConfig().getListUrl();
                                mechanismId = model.getId();
                                appName = model.getConfig().getAppName();
                                initBridge(context, view, lp);
                            }
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }

                    }

                    @Override
                    public void onError(Response<MechanismModel> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }
                });
    }
}
