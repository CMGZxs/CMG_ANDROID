package common.manager;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import common.model.BuriedPointModel;
import common.utils.NumberFormatTool;

/**
 * 埋点管理
 */
public class BuriedPointModelManager {

    /**
     * 点赞，收藏
     *
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param contentType
     * @return
     */
    public static String getLikeAndFavorBuriedPointData(String contentId, String title, String contentKey, String contentList,
                                                        String firstClassify, String secondClassify, String publishTime, String contentType) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setContent_type(contentType);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 分享按钮
     *
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param contentType
     * @param moduleTitle
     * @return
     */
    public static String getShareClick(String contentId, String title, String contentKey, String contentList,
                                       String firstClassify, String secondClassify, String publishTime, String contentType,
                                       String moduleTitle) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setContent_type(contentType);
        buriedPointModel.setModule_title(moduleTitle);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 分享类型
     *
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param contentType
     * @param forwardType
     * @return
     */
    public static String getShareType(String contentId, String title, String contentKey, String contentList,
                                      String firstClassify, String secondClassify, String publishTime, String contentType,
                                      String forwardType) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setContent_type(contentType);
        buriedPointModel.setForward_type(forwardType);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 视频开始播放
     *
     * @param moduleSource
     * @param isRenew
     * @param contentId
     * @param contentName
     * @param contentKey
     * @param contentList
     * @return
     */
    public static String getVideoStartPlay(String moduleSource, String isRenew, String contentId, String contentName,
                                           String contentKey, String contentList, String firstClassify, String secondClassify,
                                           String contentType, String publishTime) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setModule_source(moduleSource);
        buriedPointModel.setIs_renew(isRenew);
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(contentName);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setContent_type(contentType);
        buriedPointModel.setPublish_time(publishTime);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 视频播放完成
     *
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param contentType
     * @param playDuration
     * @return
     */
    public static String getVideoPlayComplate(String contentId, String title, String contentKey, String contentList,
                                              String firstClassify, String secondClassify, String publishTime, String contentType, String playDuration) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setContent_type(contentType);
        buriedPointModel.setPlay_duration(playDuration);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 视频播放倍速
     *
     * @param speedN
     * @param contentId
     * @param contentName
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @return
     */
    public static String getVideoPlaySpeed(String speedN, String contentId, String contentName, String contentKey,
                                           String contentList, String firstClassify, String secondClassify, String publishTime) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setSpeed_n(speedN);
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(contentName);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 埋点评论
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param contentType
     * @return
     */
    public static String getVideoComment(String contentId, String title, String contentKey, String contentList,
                                         String firstClassify, String secondClassify, String publishTime,String contentType) {
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setContent_type(contentType);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 埋点 视频播放时长
     * @param contentId
     * @param title
     * @param contentKey
     * @param contentList
     * @param firstClassify
     * @param secondClassify
     * @param publishTime
     * @param playDuration
     * @return
     */
    public static String getVideoDuration(String contentId, String title, String contentKey, String contentList,
                                          String firstClassify, String secondClassify, String publishTime,String playDuration){
        BuriedPointModel buriedPointModel = new BuriedPointModel();
        buriedPointModel.setContent_id(contentId);
        buriedPointModel.setContent_name(title);
        buriedPointModel.setContent_key(contentKey);
        buriedPointModel.setContent_list(contentList);
        buriedPointModel.setContent_first_classify(firstClassify);
        buriedPointModel.setContent_second_classify(secondClassify);
        buriedPointModel.setPublish_time(publishTime);
        buriedPointModel.setPlay_duration(playDuration);
        String jsonstring = JSON.toJSONString(buriedPointModel);
        if (null == jsonstring || TextUtils.isEmpty(jsonstring)) {
            return "";
        }
        return jsonstring;
    }

    /**
     * 上报播放时长
     */
    public static void reportPlayTime(double mReportVodStartTime,String contentId, String title, String contentKey, String contentList,
                               String firstClassify, String secondClassify, String publishTime) {
        if (mReportVodStartTime != -1) {
            double reportEndTime = System.currentTimeMillis();
            double diff = (reportEndTime - mReportVodStartTime) / 1000;
            String jsonString = BuriedPointModelManager.getVideoDuration(contentId, title, contentKey,contentList,firstClassify, secondClassify,publishTime, NumberFormatTool.save2Num(diff));
        }
    }
}
