package common.constants;

public class Constants {
    public static final String success_code = "200";
    public static final String token_error = "401";
    public static final String LOGIN_ERROR_CODE = "666666"; //没有绑定手机号
    public static final String USER_ID = "user_id";
    public static final String LOCAL_USER_ID = "local_user_id";
    public static final String TYPE_TOKEN = "token";
    public static final String GDY_TOKEN = "gdy_token";
    public static final String TRANSFORMATION_TOKEN = "transformation_token";
    public static final String TGT_CODE = "tgt_code";
    public static final String USER_PHONE_NUM = "phoneNum";
    public static final String USER_IMAGE_URL = "userImageUrl";
    public static final String USER_NICKNAME = "nickName";
    public static final String USER_APPID = "appId";
    public static final String SZRM_USER_MODEL = "szrmUserModel";
    public static final String CFGSTR = "cfgStr";
    public static final String LOGOURL = "logoUrl";
    public static final String INTENTURL = "intentUrl";
    public static final String MECHANISMID = "mechanismId";
    public static final String APPNAME = "appName";
    public static final String CATEGORYCODE = "categoryCode";
    public static final String PANID = "panId";
    public static final String THIRDUSERID = "thirdUserId";
    public static final String THIRDUSERHEAD = "ThirdUserHead";
    public static final String THIRDUSERNICKNAME = "ThirdUserNickName";
    public static final String THIRDUSERPHONE = "thirdUserPhone";
    public static final String SAVE_PATH_NAME = "ZXS_PATH";


    public static final String KEY_USER = "keyuser";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String PUSH_TOKEN = "pushtoken";
    public static final String SHARE_WX = "wx";
    public static final String SHARE_CIRCLE = "circle";
    public static final String SHARE_QQ = "qq";
    public static final String AGREE_NETWORK = "agree_network";
    public static final String CONTENT_TYPE = "视频";
    public static final String WX_STRING = "朋友";
    public static final String CIRCLE_STRING = "朋友圈";
    public static final String QQ_STRING = "qq";
    public static final String PARAME_VIDEO_CHANNEL = "videochannletype";
    public static final String PANELCODE = "panelCode";
    public static final String VIDEOTAB = "videoTab";
    public static final String CONTENTID = "contentId";
    public static final String TOCURRENTTAB = "toCurrentTab";
    public static final String CATEGORYNAME = "categoryName";
    public static final String REQUESTID = "requestId";
    public static final String MODULE_SOURCE = "module_source";
    public static final String TABNAME = "tabName";
    public static final String VIDEOTAG = "videoTag";
    public static final String VIDEODETAILTAG = "videoDetailTag";
    public static final String TRACKINGUPLOAD = "TrackingUpload";
    public static final String BLUE_V = "blue-v";
    public static final String YELLOW_V = "yellow-v";
    public static final String RED_V = "red-v";
    public static final String UPLOAD_AGREEMENT = "upload_agreement";
    public static final String PORTRAIT_STR = "Portrait";
    public static final String CERTIFICATED = "certificated";
    public static final String FROMHOMETAB = "fromHomeTab";
    public static final String FROMXKSHTAB = "fromXkshTab";

    /**
     * 1.排行榜https://uat-h5.zhcs.csbtv.com/act/xksh/#/ranking
     * 2.话题详情https://uat-h5.zhcs.csbtv.com/act/xksh/#/topicDetails?id=215403
     * 3.个人中心https://uat-h5.zhcs.csbtv.com/act/xksh/#/me
     * 4.头像TA人主页https://uat-h5.zhcs.csbtv.com/act/xksh/#/others?id=53901
     * 5.搜索页 https://uat-h5.zhcs.csbtv.com/fuse/news/#/searchPlus
     */
    public static final String RANKING_LIST = "https://uat-h5.zhcs.csbtv.com/act/xksh/#/ranking";
    public static final String TOPIC_DETAILS = "https://uat-h5.zhcs.csbtv.com/act/xksh/#/topicDetails?id=";
    public static final String PERSONAL_CENTER = "https://uat-h5.zhcs.csbtv.com/act/xksh/#/me";
    public static final String HEAD_OTHER = "https://uat-h5.zhcs.csbtv.com/act/xksh/index.html#/me?id=";
    public static final String SEARCHPLUS = "https://uat-h5.zhcs.csbtv.com/fuse/news/#/searchPlus";

    public static final String RANKING_LIST_ZS = "https://h5.zhcs.csbtv.com/act/xksh/#/ranking";
    public static final String TOPIC_DETAILS_ZS = "https://h5.zhcs.csbtv.com/act/xksh/#/topicDetails?id=";
    public static final String PERSONAL_CENTER_ZS = "https://h5.zhcs.csbtv.com/act/xksh/#/me";
    public static final String HEAD_OTHER_ZS = "https://h5.zhcs.csbtv.com/act/xksh/index.html#/me?id=";
    public static final String SEARCHPLUS_ZS = "https://h5.zhcs.csbtv.com/fuse/news/#/searchPlus";

//    /**
//     * ssid 火山需要传入的设备id (暂时先固定填上测试  后续需从万达拿)
//     */
//    public static final String SSID = param.getYmSSid();
    /**
     * 双击点击相隔时间
     */
    public static final long CLICK_INTERVAL_TIME = 300;
    /**
     * 刷新操作标识
     */
    public static final String REFRESH_TYPE = "refresh";
    /**
     * 加载更多操作标识
     */
    public static final String LOADMORE_TYPE = "loadmore";

    /**
     * 超时时间
     */
    public static final int REQUEST_TIME_OUT = 99;

    /**
     * 上报内容埋点固定传参
     */
    public static final String ENTER_FROM = "click_category";
    //    public static final String CATEGORY_NAME = "c2402539";
    public static final String PARAMS_FOR_SPECIAL = "content_manager_system";

    /**
     * 数智融媒错误码
     */
    public static final String SZRM_LOGIN_ERROR_CODE = "szrm_login_error_code";


    /**
     * 内容上报事件event
     */
    public static final String CMS_CLIENT_SHOW = "cms_client_show"; //露出即上报
    public static final String CMS_VIDEO_PLAY_AUTO = "cms_video_play_auto"; //在内流中滑动播放视频时上报
    public static final String CMS_VIDEO_OVER_AUTO = "cms_video_over_auto"; //滑动播放结束/自动播放结束
    public static final String CMS_VIDEO_PLAY = "cms_video_play"; //播放完成后点击「重播」
    public static final String CMS_VIDEO_OVER = "cms_video_over";  //重播的视频播放结束
    /**
     * 行为上报事件event
     */
    public static final String SHORT_VIDEO_HOME_CLICK = "short_video_home_click"; //短视频首页上的点击事件
    public static final String WELL_LIFE_HOME_CLICK = "well_life_home_click"; //我的小康生活页面上的点击事件
    public static final String SHORT_VIDEO_START_UPLOAD = "short_video_start_upload"; //点击上传作品 （点击上传作品按钮时触发）
    public static final String SHORT_VIDEO_TOPIC_LISTPAGE = "short_video_topic_listpage"; //热门话题列表页点击
    public static final String SHORT_VIDEO_PERSONAL_BUTTON = "short_video_personal_button"; //视频个人中心页点击（非视频按钮）

    public static final String CONTENT_LIVE_LIST_PAGE = "content_live_list_page"; //浏览直播专区
    public static final String CONTENT_LIVE_CLICK_LIST = "content_live_click_list"; //点击直播列表页
    public static final String CONTENT_LIVE_ENTER_PAGE = "content_live_enter_page"; //进入直播页


    public static final double Horizontal_Proportion = 1.7777;
    public static final double Portrait_Proportion = 0.5625;
    public static final String SDK_JS_SETTITLE = "setTitle";
    public static final String SDK_JS_MONITORLIFECYCLE = "monitorLifeCycle";
    public static final String SDK_JS_GETDEVICEID = "getDeviceId";
    public static final String SDK_JS_GETUSERINFO = "getUserInfo";
    public static final String SDK_JS_JUMPTONATIVEPAGE = "jumpToNativePage";
    public static final String SDK_JS_SHARE = "share";
    public static final String SDK_JS_SAVEPHOTO = "savePhoto";
    public static final String SDK_JS_OPENVIDEO = "openVideo";
    public static final String SDK_JS_GOLOGING = "goLogin";
    public static final String SDK_JS_GETAPPVERSION = "getAppVersion";

    //浏阳 2346992 岳阳 1832192
    public static final String LIUYANG_JGH = "2346992";
    public static final String YUEYANG_JGH = "1832192";
    public static final String KUNMING_JGH = "1911189";

    /**
     * 新闻视频标识
     */
    public static final String NEWS_VIDEO = "news_video";
    public static final String SHORT_VIDEO = "short_video";
    public static final String SPECIAL_NEWS = "special";
    public static final String NEWS_COMMON = "news_common";

    public static final String JUMPTONATIVEPAGEMODEL = "JumpToNativePageModel";
    public static final String WEBINTENT = "intent";
    public static final String SHAREINFO = "ShareInfo";
    public static final String TOOLBARISSHOW = "toolBarIsShow";
}
