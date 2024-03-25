package common.http;

public class ApiConstants {
    public String baseUrl = "https://fuse-api-gw.zhcs.csbtv.com/";
    public String adminBaseUrl = "https://fuse-admin.zhcs.csbtv.com/";
    public String h5BaseUrl;

    private static ApiConstants instance;

    public static ApiConstants getInstance() {
        if (instance == null) {
            synchronized (ApiConstants.class) {
                if (instance == null) {
                    instance = new ApiConstants();
                }
            }
        }
        return instance;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAdminBaseUrl() {
        return adminBaseUrl;
    }

    public void setAdminBaseUrl(String adminBaseUrl) {
        this.adminBaseUrl = adminBaseUrl;
    }

    public void setH5BaseUrl(String h5BaseUrl) {
        this.h5BaseUrl = h5BaseUrl;
    }

    public String getH5BaseUrl() {
        return h5BaseUrl;
    }

    /**
     * 获取视频下拉列表
     */
    public String getVideoDetailListUrl() {
        return getBaseUrl() + "api/cms/client/video/queryVideoPullDownList";
    }

    /**
     * 获取视频详情
     */
    public String getVideoDetailUrl() {
        return getBaseUrl() + "api/cms/client/video/getVideoDetails/";
    }

    /**
     * 获取视频合集
     */
    public String getVideoCollectionUrl() {
        return getBaseUrl() + "api/cms/client/video/getVideoCollect/";
    }

    /**
     * 获取视频评论列表
     */
    public String getCommentListUrl() {
        return getBaseUrl() + "api/cms/client/comment/getCommentByContent";
    }

    /**
     * 获取盖楼评论列表
     */
    public String getCommentWithReply() {
        return getBaseUrl() + "api/cms/client/comment/getCommentWithReply";
    }

    /**
     * 添加评论
     */
    public String addComment() {
        return getBaseUrl() + "api/cms/client/comment/add";
    }

    /**
     * 添加回复
     */
    public String addUserReply() {
        return getBaseUrl() + "api/cms/client/comment/addUserReply";
    }

    /**
     * 查询统计数据
     */
    public String queryStatsData() {
        return getBaseUrl() + "api/cms/client/contentStats/queryStatsData";
    }

    /**
     * 收藏/取消收藏
     */
    public String addOrCancelFavor() {
        return getBaseUrl() + "api/cms/client/favor/addOrCancelFavor";
    }

    /**
     * 点赞/取消点赞
     */
    public String addOrCancelLike() {
        return getBaseUrl() + "api/cms/client/like/likeOrCancel";
    }

    /**
     * 获取token值
     */
    public String getToken() {
        return getBaseUrl() + "api/sys/login/mycs";
    }

    /**
     * 我的长沙token登录
     */
    public String mycsToken() {
        return getBaseUrl() + "api/sys/login/mycs/token";
    }

    /**
     * 浏览量+1接口
     */
    public String addViews() {
        return getBaseUrl() + "api/cms/client/contentStats/view/count/";
    }

    /**
     * 获取推荐滚动列表
     */
    public String recommendList() {
        return getBaseUrl() + "api/cms/client/content/recommend";
    }

    /**
     * 埋点上报接口
     */
    public String trackingUpload() {
        return getBaseUrl() + "api/cms/client/tracking/upload";
    }

    /**
     * 上传视频
     *
     * @return
     */
    public String uploadVideo() {
        return getBaseUrl() + "api/media/file/upload";
    }

    /**
     * 获取话题
     *
     * @return
     */
    public String topicData() {
        return getBaseUrl() + "api/cms/client/content/page";
    }

    /**
     * 发布内容
     */
    public String releaseContent() {
        return getBaseUrl() + "api/cms/client/content/activity/works/create";
    }

    /**
     * 关注
     */
    public String toFollow() {
        return getBaseUrl() + "api/sys/user/me/follow/";
    }

    /**
     * 取消关注
     */
    public String cancelFollow() {
        return getBaseUrl() + "api/sys/user/me/unfollow/";
    }

    /**
     * 活动规则
     */
    public String getActivityRule() {
        return getBaseUrl() + "api/cms/client/panel/info";
    }

    /**
     * 话题
     */
    public String getTopic() {
        return getBaseUrl() + "api/cms/client/content/page";
    }

    /**
     * 单条视频获取视频合集
     */
    public String getCollectToVideo() {
        return getBaseUrl() + "api/cms/client/video/getCollectToVideo/";
    }

    /**
     * 视频合集列表接口
     */
    public String getSpecList() {
        return getBaseUrl() + "api/cms/client/content/getSpecList";
    }

    public String getCategoryData() {
        return getBaseUrl() + "api/cms/client/category/getCategoryData";
    }

    /**
     * 数智融媒登录
     */
    public String getLoginParty() {
        return getBaseUrl() + "api/sys/login/3rd/party";
    }

    /**
     * 获取随机视频
     */
    public String queryRandomVideoList() {
        return getBaseUrl() + "api/cms/client/video/queryRandomVideoList";
    }

    /**
     * 获取机构
     */
    public String getCfg() {
        return getBaseUrl() + "api/sys/client/org/cfg";
    }

    /**
     * 在星沙推荐列表
     */
    public String getCategoryCompositeData() {
        return getAdminBaseUrl() + "api/cms/client/category/getCategoryCompositeData";
    }

    /**
     *
     * @return
     */
    public String getContentList(){
        return getH5BaseUrl() + "api/cms/client/content/getContentList";
    }

    /**
     * 获取广电云token
     */
    public String gdyToken(){
        return getBaseUrl() + "api/sys/getTokenGdyByTgt";
    }
}
