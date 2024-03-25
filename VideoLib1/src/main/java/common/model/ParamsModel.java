package common.model;


import android.support.annotation.Keep;

import java.util.List;

@Keep
public class ParamsModel {

    private String enter_from;
    private String category_name;
    private String group_id;
    private List<ItemsDTO> __items;
    private String stay_time;
    private String percent;
    private String params_for_special;
    private String duration;
    private String req_id;

    public String getEnter_from() {
        return enter_from;
    }

    public void setEnter_from(String enter_from) {
        this.enter_from = enter_from;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public List<ItemsDTO> get__items() {
        return __items;
    }

    public void set__items(List<ItemsDTO> __items) {
        this.__items = __items;
    }

    public String getStay_time() {
        return stay_time;
    }

    public void setStay_time(String stay_time) {
        this.stay_time = stay_time;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getParams_for_special() {
        return params_for_special;
    }

    public void setParams_for_special(String params_for_special) {
        this.params_for_special = params_for_special;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReq_id() {
        return req_id;
    }

    public void setReq_id(String requestId) {
        this.req_id = requestId;
    }

    @Keep
    public static class ItemsDTO {
        private List<GroupItemDTO> group_item;

        public List<GroupItemDTO> getGroup_item() {
            return group_item;
        }

        public void setGroup_item(List<GroupItemDTO> group_item) {
            this.group_item = group_item;
        }
        @Keep
        public static class GroupItemDTO {
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
