package common.model;


import android.support.annotation.Keep;

@Keep
public class BuriedPointModel {
    /**
     * 内容ID
     */
    private String content_id;
    /**
     * 内容名称
     */
    private String content_name;
    /**
     * 内容关键词
     */
    private String content_key;
    /**
     * 内容标签
     */
    private String content_list;
    /**
     * 内容一级栏目
     */
    private String content_first_classify;
    /**
     * 内容二级栏目
     */
    private String content_second_classify;
    /**
     * 发布时间 (时间戳)
     */
    private String publish_time;
    /**
     * 内容类别
     */
    private String content_type;
    /**
     * 所属模块
     */
    private String module_title;
    /**
     * 转发方式
     */
    private String forward_type;
    /**
     * 入口来源
     */
    private String module_source;
    /**
     * 视频fragment是否为重播
     */
    private String is_renew;
    /**
     * 小康生活fragment是否为重播
     */
    private String xksh_renew;
    /**
     * 播放完成 播放时长
     */
    private String play_duration;
    /**
     * 倍速
     */
    private String speed_n;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getContent_key() {
        return content_key;
    }

    public void setContent_key(String content_key) {
        this.content_key = content_key;
    }

    public String getContent_list() {
        return content_list;
    }

    public void setContent_list(String content_list) {
        this.content_list = content_list;
    }

    public String getContent_first_classify() {
        return content_first_classify;
    }

    public void setContent_first_classify(String content_first_classify) {
        this.content_first_classify = content_first_classify;
    }

    public String getContent_second_classify() {
        return content_second_classify;
    }

    public void setContent_second_classify(String content_second_classify) {
        this.content_second_classify = content_second_classify;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getModule_title() {
        return module_title;
    }

    public void setModule_title(String module_title) {
        this.module_title = module_title;
    }

    public String getForward_type() {
        return forward_type;
    }

    public void setForward_type(String forward_type) {
        this.forward_type = forward_type;
    }

    public String getModule_source() {
        return module_source;
    }

    public void setModule_source(String module_source) {
        this.module_source = module_source;
    }

    public String getIs_renew() {
        return is_renew;
    }

    public void setIs_renew(String is_renew) {
        this.is_renew = is_renew;
    }

    public String getXksh_renew() {
        return xksh_renew;
    }

    public void setXksh_renew(String xksh_renew) {
        this.xksh_renew = xksh_renew;
    }

    public String getPlay_duration() {
        return play_duration;
    }

    public void setPlay_duration(String play_duration) {
        this.play_duration = play_duration;
    }

    public String getSpeed_n() {
        return speed_n;
    }

    public void setSpeed_n(String speed_n) {
        this.speed_n = speed_n;
    }

}
