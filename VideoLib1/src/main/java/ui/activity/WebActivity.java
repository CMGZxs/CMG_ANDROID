package ui.activity;

import static common.callback.VideoInteractiveParam.param;
import static common.constants.Constants.VIDEOTAG;
import static common.constants.Constants.success_code;
import static common.constants.Constants.token_error;
import static common.utils.AppInit.appId;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import adpter.CommentPopRvAdapter;
import brvah.BaseQuickAdapter;
import brvah.entity.MultiItemEntity;
import common.callback.JsonCallback;
import common.callback.SdkInteractiveParam;
import common.callback.VideoInteractiveParam;
import common.constants.Constants;
import common.http.ApiConstants;
import common.manager.BuriedPointModelManager;
import common.model.AppSystemModel;
import common.model.CommentLv1Model;
import common.model.ContentStateModel;
import common.model.DeviceIdModel;
import common.model.JumpToNativePageModel;
import common.model.MechanismModel;
import common.model.ReplyLv2Model;
import common.model.SdkUserInfo;
import common.model.ShareInfo;
import common.model.ThirdUserInfo;
import common.utils.AppInit;
import common.utils.ButtonSpan;
import common.utils.ImageUtils;
import common.utils.KeyboardUtils;
import common.utils.NumberFormatTool;
import common.utils.PersonInfoManager;
import common.utils.SavePhoto;
import common.utils.ScreenUtils;
import common.utils.SystemUtil;
import common.utils.ToastUtils;
import custompop.CustomPopWindow;
import flyco.tablayout.widget.MsgView;
import io.reactivex.functions.Consumer;
import rxpermission.RxPermissions;
import utils.UUIDUtils;
import utils.VideoScaleUtils;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = WebActivity.class.getSimpleName();
    private LinearLayout imgBack;
    private TextView webTitle;
    private LinearLayout iconShare;
    private ImageView imgClose;
    public static final int LOGIN_REQUEST_CODE = 315;
    public static AgentWeb mAgentWeb;
    private RelativeLayout container;
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
    private String intent = "0";
    private boolean isFinish;
    private ShareInfo shareInfo;
    private RelativeLayout commentPopRl;
    private RelativeLayout webCommentClick;
    private RelativeLayout webLikeClick;
    private RelativeLayout webShareClick;
    private MsgView webCommentMsgTip;
    private MsgView webLikeMsgTip;
    private View popContentView;
    private View sendPopContentView;
    private TextView commentPopCommentTotal;
    private LinearLayout edtParent;
    private EditText edtInput;
    private TextView tvSend;
    private View noLoginTipsView;
    private TextView noLoginTipsCancel;
    private TextView noLoginTipsOk;
    private CustomPopWindow noLoginTipsPop;
    private boolean isReply = false;
    private String replyId;
    //附着在软键盘上的输入弹出窗
    public CustomPopWindow inputAndSendPop;
    private View rootView;
    private LinearLayout webCommentLl;
    private int mPageIndex = 1; //评论列表页数
    private int mPageSize = 10; //评论列表每页多少条
    public String myContentId = ""; //记录当前视频id
    private RelativeLayout dismissPop;
    private RecyclerView commentPopRv;
    private TextView commentEdtInput;
    private RelativeLayout commentPop;
    //评论列表数据
    private List<MultiItemEntity> mCommentPopRvData;
    private List<CommentLv1Model.DataDTO.RecordsDTO> mCommentPopDtoData;
    private CommentPopRvAdapter commentPopRvAdapter;
    //评论列表弹窗
    public CustomPopWindow popupWindow;
    private ImageView webLikeIcon;
    private TextView webCommentEdtInput;
    private RelativeLayout webToolbar;
    private boolean toolBarIsShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        container = findViewById(R.id.container);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        imgClose = findViewById(R.id.imgClose);
        webTitle = findViewById(R.id.webTitle);
        iconShare = findViewById(R.id.iconShare);
        iconShare.setOnClickListener(this);
        ScreenUtils.fullScreen(this, true);
        ScreenUtils.setStatusBarColor(this, R.color.white);
        param = (JumpToNativePageModel) getIntent().getSerializableExtra("param");
        intent = getIntent().getStringExtra("intent");
        shareInfo = (ShareInfo) getIntent().getSerializableExtra("shareInfo");
        webToolbar = findViewById(R.id.webToolbar);
        toolBarIsShow = getIntent().getBooleanExtra("toolBarIsShow", true);

        if (toolBarIsShow) {
            webToolbar.setVisibility(View.VISIBLE);
        } else {
            webToolbar.setVisibility(View.GONE);
        }

        if (null != param) {
            myContentId = param.getContentId();
        }
        if (null != param && !TextUtils.isEmpty(param.getNewsLink())) {
            iconShare.setVisibility(View.VISIBLE);
        } else {
            iconShare.setVisibility(View.GONE);
        }

        sharePopView = View.inflate(this, R.layout.share_pop_view, null);
        shareWxBtn = sharePopView.findViewById(R.id.share_wx_btn);
        shareWxBtn.setOnClickListener(this);
        shareCircleBtn = sharePopView.findViewById(R.id.share_circle_btn);
        shareCircleBtn.setOnClickListener(this);
        shareQqBtn = sharePopView.findViewById(R.id.share_qq_btn);
        shareQqBtn.setOnClickListener(this);
        mBridgeWebView = new BridgeWebView(this);

        commentPopRl = findViewById(R.id.web_comment_pop_rl);
        commentPopRl.setOnClickListener(this);
        webCommentClick = findViewById(R.id.web_comment_click);
        webCommentClick.setOnClickListener(this);
        webLikeClick = findViewById(R.id.web_like_click);
        webLikeClick.setOnClickListener(this);
        webShareClick = findViewById(R.id.web_share_click);
        webShareClick.setOnClickListener(this);
        webCommentMsgTip = findViewById(R.id.web_comment_msg_tip);
        webCommentMsgTip.setOnClickListener(this);
        webLikeMsgTip = findViewById(R.id.web_like_msg_tip);
        webLikeMsgTip.setOnClickListener(this);
        popContentView = View.inflate(this, R.layout.fragment_video_comment_pop, null);
        sendPopContentView = View.inflate(this, R.layout.layout_input_window, null);
        commentPopCommentTotal = popContentView.findViewById(R.id.comment_pop_comment_total);
        edtParent = sendPopContentView.findViewById(R.id.edt_parent);
        edtInput = sendPopContentView.findViewById(R.id.edtInput);
        tvSend = sendPopContentView.findViewById(R.id.tvSend);
        tvSend.setOnClickListener(this);
        noLoginTipsView = View.inflate(this, R.layout.no_login_tips, null);
        noLoginTipsCancel = noLoginTipsView.findViewById(R.id.no_login_tips_cancel);
        noLoginTipsCancel.setOnClickListener(this);
        noLoginTipsOk = noLoginTipsView.findViewById(R.id.no_login_tips_ok);
        noLoginTipsOk.setOnClickListener(this);
        rootView = findViewById(R.id.web_frame);
        dismissPop = popContentView.findViewById(R.id.dismiss_pop);
        dismissPop.setOnClickListener(this);
        commentPopRv = popContentView.findViewById(R.id.comment_pop_rv);
        commentEdtInput = popContentView.findViewById(R.id.comment_edtInput);
        commentPop = popContentView.findViewById(R.id.comment_pop_rl);
        commentPop.setOnClickListener(this);
        webLikeIcon = findViewById(R.id.web_like_icon);
        webCommentEdtInput = findViewById(R.id.web_comment_edtInput);
        initCommentPopRv();
        if (null != param) {
            getCommentList("1", String.valueOf(mPageSize), true);
            getContentState(param.getContentId());
        }
        webCommentLl = findViewById(R.id.web_comment_ll);
        if (TextUtils.equals("1", intent)) {
            PersonInfoManager.getInstance().setIntentUrl(param.getNewsLink());
//            intentUrl = param.getNewsLink();
            initBridge();
            if (TextUtils.isEmpty(param.getContentId())) {
                webCommentLl.setVisibility(View.GONE);
            } else {
                webCommentLl.setVisibility(View.VISIBLE);
            }

            if (TextUtils.equals(param.getDisableComment(), "true")) {
                webCommentEdtInput.setText("该内容禁止评论");
                webCommentEdtInput.setTextColor(getResources().getColor(R.color.black));
            } else {
                webCommentEdtInput.setText("写评论...");
                webCommentEdtInput.setTextColor(getResources().getColor(R.color.black));
            }
        } else {
            getCfg();
            webCommentLl.setVisibility(View.GONE);
        }
    }

    /**
     * 获取机构
     */
    private void getCfg() {
        OkGo.<MechanismModel>get(ApiConstants.getInstance().getCfg())
                .tag("cfg")
                .params("appId", appId)
                .execute(new JsonCallback<MechanismModel>(MechanismModel.class) {
                    @Override
                    public void onSuccess(Response<MechanismModel> response) {
                        if (null == response.body()) {
                            Log.e("getCfg", "cfg获取错误");
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if (null==response.body().getCode()) {
                            Log.e("getCfg", "cfg获取错误");
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                            MechanismModel.DataDTO model = response.body().getData();
                            if (null != model) {
                                PersonInfoManager.getInstance().setCfgStr(JSON.toJSONString(model));
                                PersonInfoManager.getInstance().setLogoUrl(model.getLogo());
                                PersonInfoManager.getInstance().setIntentUrl(model.getConfig().getListUrl());
                                PersonInfoManager.getInstance().setMechanismId(model.getId());
                                PersonInfoManager.getInstance().setAppName(model.getConfig().getAppName());
                                initBridge();
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

//    @NonNull
//    @Override
//    protected ViewGroup getAgentWebParent() {
//        return (ViewGroup) this.findViewById(R.id.container);
//    }

    private WebViewClient getWebViewClient() {
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
                mAgentWeb.getJsAccessEntrace().callJs(str3 + getAppInfo() + str4);
                mAgentWeb.getJsAccessEntrace().callJs(str5 + PersonInfoManager.getInstance().getCfgStr() + str4);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webTitle.setText(view.getTitle());
                mBridgeWebViewClient.onPageFinished(view, url);
                isFinish = true;
            }
        };
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isFinish && null != mAgentWeb && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && null != data) {
            userInfo = (SdkUserInfo.DataDTO) data.getExtras().getSerializable("userInfo");
            PersonInfoManager.getInstance().setUserId(userInfo.getLoginSysUserVo().getId());
            String userInfoStr = JSON.toJSONString(userInfo);
            PersonInfoManager.getInstance().setSzrmUserModel(userInfoStr);
            String str1 = "onAppLogin('";
            String str2 = "')";
            mAgentWeb.getJsAccessEntrace().callJs(str1 + userInfoStr + str2);
        }
    }

    /**
     * 初始化评论列表
     */
    private void initCommentPopRv() {
        mCommentPopRvData = new ArrayList<>();
        mCommentPopDtoData = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentPopRv.setLayoutManager(linearLayoutManager);
        commentPopRvAdapter = new CommentPopRvAdapter(mCommentPopRvData, this);
        commentPopRvAdapter.bindToRecyclerView(commentPopRv);
        commentPopRvAdapter.setEmptyView(R.layout.comment_list_empty);
        commentPopRvAdapter.expandAll();
        commentPopRv.setAdapter(commentPopRvAdapter);
        commentPopRvAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                commentPopRv.post(new Runnable() {
                    @Override
                    public void run() {
                        mPageIndex++;
                        getCommentList(String.valueOf(mPageIndex), String.valueOf(mPageSize), false);
                    }
                });
            }
        }, commentPopRv);

        //评论点赞
        commentPopRvAdapter.setLv1CommentLike(new CommentPopRvAdapter.Lv1CommentLikeListener() {
            @Override
            public void lv1CommentLikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setLv2CommentLike(new CommentPopRvAdapter.Lv2CommentLikeListener() {
            @Override
            public void Lv2CommentLikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setReback1Like(new CommentPopRvAdapter.Reback1LikeBtnListener() {
            @Override
            public void reback1LikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });

        commentPopRvAdapter.setReback2Like(new CommentPopRvAdapter.Reback2LikeBtnListener() {
            @Override
            public void reback2LikeClick(Object o, String targetId, ImageView likeIcon, TextView likeNum) {
                CommentLikeOrCancel(o, targetId, likeIcon, likeNum);
            }
        });
    }


    private void initBridge() {

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebViewClient(getWebViewClient())
                .setWebView(mBridgeWebView)
//                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//               .setDownloadListener(mDownloadListener) 4.0.0 删除该API
                .createAgentWeb()
                .ready() //PersonInfoManager.getInstance().getIntentUrl()
                .go(PersonInfoManager.getInstance().getIntentUrl());
        if (null != mBridgeWebView) {
            setBridge();
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

    /**
     * 设置jsBridge
     */
    private void setBridge() {
        mBridgeWebView.registerHandler("MJBrigeHandler", new BridgeHandler() {
            @Override
            public void handler(final String data, final CallBackFunction function) {
                final JSONObject jsonObject = JSON.parseObject(data);
                String methodName = jsonObject.getString("methodName");
                dataObject = jsonObject.getJSONObject("data");

                if (TextUtils.equals(methodName, Constants.SDK_JS_SETTITLE)) { //设置标题
                    if (null == dataObject) {
                        if (null != param) {
                            webTitle.setText(param.getTitle());
                        }
                    } else {
                        String title = dataObject.getString("title");
                        webTitle.setText(title);
                    }

                } else if (TextUtils.equals(methodName, Constants.SDK_JS_MONITORLIFECYCLE)) { //返回
                    finish();
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETDEVICEID)) { //获取设备id
                    DeviceIdModel model = new DeviceIdModel();
                    model.setDeviceId(UUIDUtils.deviceUUID());
                    String deviceIdStr = JSON.toJSONString(model);
                    function.onCallBack(deviceIdStr);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETUSERINFO)) { //获取用户信息
                    String userInfoStr = PersonInfoManager.getInstance().getSzrmUserModel();
                    function.onCallBack(userInfoStr);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_JUMPTONATIVEPAGE)) { //跳转新webView
                    Intent intent = new Intent(WebActivity.this, WebActivity.class);
                    if (null != dataObject) {
                        JumpToNativePageModel model = JSON.parseObject(JSON.toJSONString(dataObject), JumpToNativePageModel.class);
                        intent.putExtra("param", model);
                        intent.putExtra("intent", "1");
                        startActivity(intent);
                    } else {
                        if (null != param) {
                            JumpToNativePageModel model = JSON.parseObject(JSON.toJSONString(param), JumpToNativePageModel.class);
                            intent.putExtra("param", model);
                            intent.putExtra("intent", "1");
                            startActivity(intent);
                        }
                    }

                } else if (TextUtils.equals(methodName, Constants.SDK_JS_SHARE)) { //分享
                    sharePop();
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_SAVEPHOTO)) { //保存图片
                    String[] writePerMissionGrop = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                    new RxPermissions(WebActivity.this).request(writePerMissionGrop).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (Build.VERSION.SDK_INT >= 30) {
                                if (Environment.isExternalStorageManager()) {
                                    saveImage(aBoolean, function);
                                } else {
                                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            } else {
                                saveImage(aBoolean, function);
                            }

                        }
                    });
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GOLOGING)) { //跳转登录
                    SdkInteractiveParam.getInstance().toLogin();
//                    Intent intent = new Intent(WebActivity.this, LoginActivity.class);
//                    intent.putExtra("mechanismId", mechanismId);
//                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_OPENVIDEO)) { //打开视频
                    if (null != dataObject) {
                        Intent intent = new Intent(WebActivity.this, VideoHomeActivity.class);
                        intent.putExtra("contentId", dataObject.getString("contentId"));
                        intent.putExtra("logoUrl", PersonInfoManager.getInstance().getLogoUrl());
                        intent.putExtra("appName", PersonInfoManager.getInstance().getAppName());
                        startActivity(intent);
                    }
                } else if (TextUtils.equals(methodName, Constants.SDK_JS_GETAPPVERSION)) { //获取设备版本号等信息
                    function.onCallBack(getAppInfo());
                }
            }
        });
    }

    private void saveImage(boolean isAgreePermission, final CallBackFunction function) {
        if (isAgreePermission) {
            String url = "";
            if (null != dataObject) {
                url = dataObject.getString("url");
            } else {
                if (null != param) {
                    url = param.getImgUrl();
                }
            }

            final String finalUrl = url;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ImageUtils.saveBitmap2file(SavePhoto.getBitmap(finalUrl), WebActivity.this
                            , handler, function);
                }
            }).start();
        } else {
            function.onCallBack("0");
            ToastUtils.showShort("请在设置中手动开启写入SD卡权限");
        }
    }

    /**
     * 获取系统的一些信息
     *
     * @return
     */
    private String getAppInfo() {
        AppSystemModel appSystemModel = new AppSystemModel();
        appSystemModel.setOsName("Android");
        appSystemModel.setBrand(SystemUtil.getDeviceBrand());
        appSystemModel.setOsVersion(String.valueOf(SystemUtil.getVersionCode(WebActivity.this)));
        appSystemModel.setAppVersion(SystemUtil.getVersionName(WebActivity.this));
        String appInfo = JSON.toJSONString(appSystemModel);
        return appInfo;
    }

    /**
     * 分享弹窗
     */
    private void sharePop() {
        if (null == sharePop) {
            sharePop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(sharePopView)
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, ButtonSpan.dip2px(150))
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .create()
                    .showAtLocation(container, Gravity.BOTTOM, 0, 0);
        } else {
            sharePop.showAtLocation(container, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imgBack) {
            if (null != mAgentWeb && !mAgentWeb.back()) {
                finish();
            }
        } else if (id == R.id.dismiss_pop) {
            if (null != popupWindow) {
                popupWindow.dissmiss();
            }
        } else if (id == R.id.share_wx_btn) {
            if (null == shareInfo) {
                toShare("WX");
            } else {
                shareInfo.setPlatform("WX");
                SdkInteractiveParam.getInstance().shared(shareInfo);
            }
        } else if (id == R.id.share_circle_btn) {
            if (null == shareInfo) {
                toShare("Circle");
            } else {
                shareInfo.setPlatform("Circle");
                SdkInteractiveParam.getInstance().shared(shareInfo);
            }
        } else if (id == R.id.share_qq_btn) {
            if (null == shareInfo) {
                toShare("QQ");
            } else {
                shareInfo.setPlatform("QQ");
                SdkInteractiveParam.getInstance().shared(shareInfo);
            }
        } else if (id == R.id.iconShare) {
            sharePop();
        } else if (id == R.id.web_comment_pop_rl) {
            if (TextUtils.equals(param.getDisableComment(), "true")) {
                return;
            }
            if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                try {
                    noLoginTipsPop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
//                KeyboardUtils.toggleSoftInput(getWindow().getDecorView());
//                edtInput.setHint("留下你的精彩评论");
//                isReply = false;
//                showInputEdittextAndSend();
                showCommentPopWindow();
            }
        } else if (id == R.id.web_comment_click) {
            if (TextUtils.equals(param.getDisableComment(), "true")) {
                return;
            }
            if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                try {
                    noLoginTipsPop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showCommentPopWindow();
            }
        } else if (id == R.id.web_like_click) {
            if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                noLoginTipsPop();
            } else {
                addOrCancelLike(myContentId, "content", webLikeIcon, webLikeMsgTip);
            }
        } else if (id == R.id.web_share_click) {
            sharePop();
        } else if (id == R.id.tvSend) {
            if (TextUtils.isEmpty(edtInput.getText())) {
                ToastUtils.showShort("请输入评论");
            } else {
                if (isReply) {
                    toReply(replyId);
                } else {
                    toComment(edtInput.getText().toString(), myContentId);
                }
                edtInput.setText("");
            }
        } else if (id == R.id.no_login_tips_cancel) {
            if (null != noLoginTipsPop) {
                noLoginTipsPop.dissmiss();
            }
        } else if (id == R.id.no_login_tips_ok) {
            if (null != noLoginTipsPop) {
                noLoginTipsPop.dissmiss();
            }
            try {
                SdkInteractiveParam.getInstance().toLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.comment_pop_rl) {
            if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
                try {
                    noLoginTipsPop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                KeyboardUtils.toggleSoftInput(getWindow().getDecorView());
                edtInput.setHint("留下你的精彩评论");
                isReply = false;
                showInputEdittextAndSend();
            }
        }
    }

    /**
     * 评论列表弹出框
     */
    private void showCommentPopWindow() {
        if (null == popupWindow) {
            //创建并显示popWindow
            popupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(popContentView)
                    .setOutsideTouchable(false)
                    .setFocusable(true)
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, (int) (AppInit.getContext().getResources().getDisplayMetrics().heightPixels * 0.7))
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .create()
                    .showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        } else {
            popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
//        SystemUtils.hideBottomUIMenuForPopupWindow(popupWindow);
        popupWindow.getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    /**
     * 评论
     */
    private void toComment(String content, String contentId) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("contentId", contentId);
            jsonObject.put("content", content);
            jsonObject.put("title", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addComment())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            org.json.JSONObject mJsonObject = new org.json.JSONObject(response.body());
                            String code = mJsonObject.get("code").toString();

                            if (code.equals(success_code)) {
                                ToastUtils.showShort("评论已提交，请等待审核通过！");
                                if (null != inputAndSendPop) {
                                    inputAndSendPop.dissmiss();
                                }
                                KeyboardUtils.hideKeyboard(getWindow().getDecorView());
                                mPageIndex = 1;
                                getCommentList("1", String.valueOf(mPageSize), true);
                            } else if (code.equals(token_error)) {
                                Log.e("addComment", "无token 去跳登录");
                                try {
                                    VideoInteractiveParam.param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != mJsonObject.getString("message")) {
                                    ToastUtils.showShort(mJsonObject.getString("message"));
                                } else {
                                    ToastUtils.showShort("评论失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("评论失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showShort("评论失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        edtInput.setText("");
                    }
                });
    }

    /**
     * 回复
     */
    private void toReply(String id) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("reply", edtInput.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addUserReply())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }
                        try {
                            org.json.JSONObject mJsonObject = new org.json.JSONObject(response.body());
                            String code = mJsonObject.get("code").toString();

                            if (code.equals(success_code)) {
                                ToastUtils.showShort("回复已提交，请等待审核通过！");
                                if (null != inputAndSendPop) {
                                    inputAndSendPop.dissmiss();
                                }
                                mPageIndex = 1;
                                KeyboardUtils.hideKeyboard(getWindow().getDecorView());
                                getCommentList("1", String.valueOf(mPageSize), true);
                            } else if (code.equals(token_error)) {
                                try {
                                    VideoInteractiveParam.param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != mJsonObject.getString("message")) {
                                    ToastUtils.showShort(mJsonObject.getString("message"));
                                } else {
                                    ToastUtils.showShort("回复失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("回复失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 弹出发送评论弹出窗
     */
    private void showInputEdittextAndSend() {
        //创建并显示popWindow
        if (null == inputAndSendPop) {
            inputAndSendPop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(sendPopContentView)
                    .setOutsideTouchable(false)
                    .setFocusable(true)
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                    .setAnimationStyle(R.style.take_popwindow_anim)
                    .size(AppInit.getContext().getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .create()
                    .showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        } else {
            inputAndSendPop.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
        }
        edtInput.setFocusable(true);
        edtInput.setFocusableInTouchMode(true);
        edtInput.requestFocus();
    }

    /**
     * 没有登录情况下 点击点赞收藏评论 提示登录的提示框
     */
    private void noLoginTipsPop() {
        if (null == noLoginTipsPop) {
            noLoginTipsPop = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(noLoginTipsView)
                    .enableBackgroundDark(true)
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .setAnimationStyle(R.style.AnimCenter)
                    .size(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels)
                    .create()
                    .showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        } else {
            noLoginTipsPop.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    /**
     * 分享
     */
    private void toShare(String platform) {
        String shareStr = "";
        if (null != dataObject) {
            shareStr = JSON.toJSONString(dataObject);
        } else {
            if (null != param) {
                shareStr = JSON.toJSONString(param);
            }
        }
        ShareInfo shareInfo = JSON.parseObject(shareStr, ShareInfo.class);
        shareInfo.setPlatform(platform);
        SdkInteractiveParam.getInstance().shared(shareInfo);
    }

    @Override
    protected void onPause() {
        if (Build.VERSION.SDK_INT >= 11) {
            mBridgeWebView.onPause();
        }

        mBridgeWebView.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (Build.VERSION.SDK_INT >= 11) {
            mBridgeWebView.onResume();
        }
        mBridgeWebView.resumeTimers();
        if (PersonInfoManager.getInstance().isRequestSzrmLogin()) {
            //需要去请求数智融媒的登录
            szrmLoginRequest(true);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (null != mAgentWeb) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        OkGo.getInstance().cancelAll();
        super.onDestroy();
    }

    public static void szrmLoginRequest(final boolean isWeb) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        ThirdUserInfo userInfo = SdkInteractiveParam.getInstance().getUserInfo();
        if (null != userInfo) {
            try {
                jsonObject.put("appId", appId);
                jsonObject.put("userId", userInfo.getUserId());
                jsonObject.put("mobile", userInfo.getPhoneNum());
                jsonObject.put("headProfile", userInfo.getHeadImageUrl());
                jsonObject.put("nickName", userInfo.getNickName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        OkGo.<SdkUserInfo>post(ApiConstants.getInstance().getLoginParty())
                .tag("sdkLogin")
                .upJson(jsonObject)
                .execute(new JsonCallback<SdkUserInfo>() {
                    @Override
                    public void onSuccess(Response<SdkUserInfo> response) {
                        if(null!=response.body().getCode()&&response.body().getCode().equals("200")) {
                            if (null == response.body().getData()) {
                                return;
                            }

                            if (isWeb) {
                                String userInfoStr = JSON.toJSONString(response.body().getData());
                                PersonInfoManager.getInstance().setSzrmUserModel(userInfoStr);
                                String str1 = "onAppLogin('";
                                String str2 = "')";
                                mAgentWeb.getJsAccessEntrace().callJs(str1 + userInfoStr + str2);
                            }


                            String token = response.body().getData().getToken();
                            SdkUserInfo.DataDTO.LoginSysUserVoDTO loginUserInfo = response.body().getData().getLoginSysUserVo();
                            PersonInfoManager.getInstance().setTransformationToken(token);
                            PersonInfoManager.getInstance().setAppId(response.body().getData().getAppId());
                            if (null != loginUserInfo) {
                                if (null != SdkInteractiveParam.getInstance().getUserInfo() &&
                                        !TextUtils.isEmpty(SdkInteractiveParam.getInstance().getUserInfo().getUserId())) {
                                    PersonInfoManager.getInstance().setUserId(SdkInteractiveParam.getInstance().getUserInfo().getUserId());
                                }
                                PersonInfoManager.getInstance().setPhoneNum(loginUserInfo.getPhone());
                                PersonInfoManager.getInstance().setNickName(loginUserInfo.getNickname());
                                PersonInfoManager.getInstance().setUserImageUrl(loginUserInfo.getHead());
                            }
                            String userModelStr = JSON.toJSONString(response.body().getData());
                            PersonInfoManager.getInstance().setSzrmUserModel(userModelStr);
                            Log.e(TAG, "数智融媒 登录成功");
                        } else {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<SdkUserInfo> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.message());
                            return;
                        }
                        ToastUtils.showShort( R.string.net_err);
                    }
                });
    }

    /**
     * 获取评论列表
     */
    public void getCommentList(String pageIndex, String pageSize, final boolean isRefresh) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("contentId", myContentId);
            jsonObject.put("pageIndex", pageIndex);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pcommentId", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkGo.<CommentLv1Model>post(ApiConstants.getInstance().getCommentWithReply())
                .tag(VIDEOTAG)
                .upJson(jsonObject)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<CommentLv1Model>(CommentLv1Model.class) {
                    @Override
                    public void onSuccess(Response<CommentLv1Model> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            if(null!=response.body().getCode()&&response.body().getCode().equals("200")) {
                                if (null == response.body().getData()) {
                                    ToastUtils.showShort(R.string.data_err);
                                    return;
                                }

                                if (isRefresh) {
                                    mCommentPopRvData.clear();
                                    mCommentPopDtoData.clear();
                                }

                                //评论总数
                                int total = Integer.parseInt(NumberFormatTool.getNumStr(response.body().getData().getTotal()));
                                if (total > 0) {
                                    webCommentMsgTip.setVisibility(View.VISIBLE);
                                    webCommentMsgTip.setText(String.valueOf(total));
                                } else {
                                    webCommentMsgTip.setVisibility(View.GONE);
                                }
                                //评论集合
                                List<CommentLv1Model.DataDTO.RecordsDTO> lv1List = response.body().getData().getRecords();
                                for (int i = 0; i < lv1List.size(); i++) {
                                    CommentLv1Model.DataDTO.RecordsDTO lv1Model = lv1List.get(i);
                                    lv1Model.setPosition(i);
                                    lv1Model.setShow(true);
                                    List<ReplyLv2Model.ReplyListDTO> lv2List = lv1Model.getReply().getReplyList();
                                    for (int j = 0; j < lv2List.size(); j++) {
                                        ReplyLv2Model.ReplyListDTO lv2Model = lv2List.get(j);
                                        lv2Model.setPosition(j);
                                        lv2Model.setParentPosition(i);
                                        lv1Model.addSubItem(lv2Model);
                                    }
                                    mCommentPopRvData.add(lv1Model);
                                }

                                mCommentPopDtoData.addAll(lv1List);
                                commentPopRvAdapter.setContentId(myContentId);
                                commentPopRvAdapter.setSrc(mCommentPopRvData);
                                commentPopRvAdapter.setNewData(mCommentPopRvData);

                                //第一级评论点击
                                commentPopRvAdapter.setLv1CommentClick(new CommentPopRvAdapter.Lv1CommentClick() {
                                    @Override
                                    public void Lv1Comment(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第一级评论第一条回复点击
                                commentPopRvAdapter.setLv1No1Click(new CommentPopRvAdapter.Lv1No1Click() {
                                    @Override
                                    public void lv1No1Click(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第一级评论第二条回复点击
                                commentPopRvAdapter.setLv1No2Click(new CommentPopRvAdapter.Lv1No2Click() {
                                    @Override
                                    public void lv1No2Click(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                //第二级回复点击
                                commentPopRvAdapter.setLv2ReplyClick(new CommentPopRvAdapter.Lv2ReplyClick() {
                                    @Override
                                    public void Lv2ReplyClick(String id, String replyName) {
                                        toSetHint(id, replyName);
                                    }
                                });

                                if (mCommentPopDtoData.isEmpty()) {
                                    commentPopCommentTotal.setText("(0)");
                                    webCommentMsgTip.setVisibility(View.GONE);
                                } else {
                                    commentPopCommentTotal.setText("(" + response.body().getData().getTotal() + ")");
                                    webCommentMsgTip.setVisibility(View.VISIBLE);
                                }

                                if (response.body().getData().getRecords().size() == 0) {
                                    commentPopRvAdapter.loadMoreEnd();
                                } else {
                                    commentPopRvAdapter.loadMoreComplete();
                                }

                            } else {
                                commentPopRvAdapter.loadMoreFail();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<CommentLv1Model> response) {
                        commentPopRvAdapter.loadMoreFail();
                    }
                });
    }

    private void toSetHint(String id, String replyName) {
        if (TextUtils.isEmpty(PersonInfoManager.getInstance().getTransformationToken())) {
            try {
                noLoginTipsPop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            KeyboardUtils.toggleSoftInput(getWindow().getDecorView());
            showInputEdittextAndSend();
            edtInput.setHint("回复@" + replyName);
            isReply = true;
            replyId = id;
        }
    }

    /**
     * 获取收藏点赞状态
     */
    public void getContentState(String contentId) {
        if (TextUtils.isEmpty(contentId)) {
            return;
        }
        OkGo.<ContentStateModel>get(ApiConstants.getInstance().queryStatsData())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .params("contentId", contentId)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<ContentStateModel>(ContentStateModel.class) {
                    @Override
                    public void onSuccess(Response<ContentStateModel> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            if(null!=response.body().getCode()&&response.body().getCode().equals(success_code)) {
                                if (null == response.body().getData()) {
                                    ToastUtils.showShort(R.string.data_err);
                                    return;
                                }
                                setLikeCollection(response.body().getData());
                            } else {
                                ToastUtils.showShort(response.body().getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<ContentStateModel> response) {
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }
                });
    }

    public void setLikeCollection(ContentStateModel.DataDTO contentStateModel) {

        if (null != webLikeIcon) {
            if (contentStateModel.getWhetherLike().equals("true")) {
                webLikeIcon.setImageResource(R.drawable.szrm_sdk_comment_like);
                webLikeMsgTip.setTextColor(getResources().getColor(R.color.bz_red));
            } else {
                webLikeIcon.setImageResource(R.drawable.web_comment_unlike);
                webLikeMsgTip.setTextColor(getResources().getColor(R.color.black));
            }
        }

        if (null != webLikeMsgTip) {
            long likeCount = Long.parseLong(NumberFormatTool.getNumStr(contentStateModel.getLikeCountShow()));
            if (likeCount > 0) {
                webLikeMsgTip.setText(NumberFormatTool.formatNum(likeCount, false));
                webLikeMsgTip.setVisibility(View.VISIBLE);
            } else {
                webLikeMsgTip.setVisibility(View.GONE);
            }

        }
    }


    /**
     * 评论点赞/取消点赞
     */
    private void CommentLikeOrCancel(final Object o, String targetId, final ImageView likeImage, final TextView likeNum) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("targetId", targetId);
            jsonObject.put("type", "comment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addOrCancelLike())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            org.json.JSONObject json = new org.json.JSONObject(response.body());
                            if (null != json && json.get("code").toString().equals("200")) {

                                if (json.get("data").toString().equals("1")) {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_comment_like);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        num++;
                                        likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        likeNum.setTextColor(getResources().getColor(R.color.bz_red));
                                    }
                                    if (o instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                        ((CommentLv1Model.DataDTO.RecordsDTO) o).setWhetherLike(true);
                                        ((CommentLv1Model.DataDTO.RecordsDTO) o).setLikeCount(num);
                                    } else if (o instanceof ReplyLv2Model.ReplyListDTO) {
                                        ((ReplyLv2Model.ReplyListDTO) o).setWhetherLike(true);
                                        ((ReplyLv2Model.ReplyListDTO) o).setLikeCount(num);
                                    }
//                                    mDataDTO.setWhetherLike(true);
//                                    playerView.contentStateModel.setWhetherLike("true");
//                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                } else {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_comment_unlike);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        if (num > 0) {
                                            num--;
                                        }
                                        if (num == 0) {
                                            likeNum.setText("");
                                        } else {
                                            likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        }
                                        likeNum.setTextColor(getResources().getColor(R.color.video_c9));

                                        if (o instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                            ((CommentLv1Model.DataDTO.RecordsDTO) o).setWhetherLike(false);
                                            ((CommentLv1Model.DataDTO.RecordsDTO) o).setLikeCount(num);
                                        } else if (o instanceof ReplyLv2Model.ReplyListDTO) {
                                            ((ReplyLv2Model.ReplyListDTO) o).setWhetherLike(false);
                                            ((ReplyLv2Model.ReplyListDTO) o).setLikeCount(num);
                                        }
                                    }
//                                    mDataDTO.setWhetherLike(false);
//                                    playerView.contentStateModel.setWhetherLike("false");
//                                    playerView.contentStateModel.setLikeCountShow(NumberFormatTool.formatNum(num, false).toString());
                                }
//                                if (null != playerView.contentStateModel) {
//                                    playerView.setContentStateModel(myContentId, videoType);
//                                }
                            } else if (json.get("code").toString().equals(token_error)) {
                                Log.e("addOrCancelLike", "无token,跳转登录");
                                try {
                                    VideoInteractiveParam.param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != json.get("message").toString()) {
                                    ToastUtils.showShort(json.get("message").toString());
                                } else {
                                    ToastUtils.showShort("点赞失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("点赞失败");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showShort("点赞失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//                        likeIsRequesting = false;
                    }
                });
    }

    /**
     * 点赞/取消点赞
     */
    private void addOrCancelLike(String targetId, String type, final ImageView likeImage, final TextView likeNum) {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("targetId", targetId);
            jsonObject.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(ApiConstants.getInstance().addOrCancelLike())
                .tag(VIDEOTAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null == response.body()) {
                            ToastUtils.showShort(R.string.data_err);
                            return;
                        }

                        try {
                            org.json.JSONObject json = new org.json.JSONObject(response.body());
                            if (null != json && json.get("code").toString().equals("200")) {
                                if (json.get("data").toString().equals("1")) {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.szrm_sdk_comment_like);
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        num++;
                                        likeNum.setText(NumberFormatTool.formatNum(num, false));
                                        likeNum.setTextColor(getResources().getColor(R.color.bz_red));
                                    }
                                    likeNum.setVisibility(View.VISIBLE);
                                } else {
                                    int num = 0;
                                    if (null != likeImage) {
                                        likeImage.setImageResource(R.drawable.web_comment_unlike);
                                        likeNum.setTextColor(getResources().getColor(R.color.black));
                                    }
                                    if (null != likeNum) {
                                        num = Integer.parseInt(NumberFormatTool.getNumStr(likeNum.getText().toString()));
                                        if (num > 0) {
                                            num--;
                                        }
                                        if (num == 0) {
                                            likeNum.setText("0");
                                            likeNum.setVisibility(View.GONE);
                                        } else {
                                            likeNum.setText(NumberFormatTool.formatNum(num, false));
                                            likeNum.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            } else if (json.get("code").toString().equals(token_error)) {
                                Log.e("addOrCancelLike", "无token,跳转登录");
                                try {
                                    VideoInteractiveParam.param.toLogin();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (null != json.get("message").toString()) {
                                    ToastUtils.showShort(json.get("message").toString());
                                } else {
                                    ToastUtils.showShort("点赞失败");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("点赞失败");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        ToastUtils.showShort("点赞失败");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}