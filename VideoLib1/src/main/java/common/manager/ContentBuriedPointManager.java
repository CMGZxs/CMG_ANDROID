package common.manager;

import static common.callback.VideoInteractiveParam.param;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import common.constants.Constants;
import common.model.ContentBuriedPointModel;
import common.model.ParamsModel;
import common.utils.AppUtils;
import common.utils.NetworkUtil;
import common.utils.PersonInfoManager;


public class ContentBuriedPointManager {
    public static JSONObject setContentBuriedPoint(Context context, String contentId, String duration, String percent, String event,
                                                   String category_name, String requestId, String appName) {
        if (TextUtils.equals(param.getApplicationIsAgree(), "1")) {
            try {
                if (TextUtils.isEmpty(contentId) || TextUtils.isEmpty(param.getDeviceId())) {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ContentBuriedPointModel model = ContentBuriedPointModel.getInstance();

            //user对象 ---start
            ContentBuriedPointModel.UserDTO userDTO = new ContentBuriedPointModel.UserDTO();
            try {
                userDTO.setBddid(param.getDeviceId());
                userDTO.setUser_unique_id(param.getDeviceId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //user对象 ---end

            //header对象 ---start
            ContentBuriedPointModel.HeaderDTO headerDTO = new ContentBuriedPointModel.HeaderDTO();
            String netType;
            if (NetworkUtil.isWifi(context)) {
                netType = "wifi";
            } else {
                netType = "4G5G";
            }
            headerDTO.setTraffic_type(netType);
            headerDTO.setApp_channel("");
            headerDTO.setOs_name("android");
            headerDTO.setApp_name(appName);
            headerDTO.setDevice_brand(AppUtils.getDeviceBrand());
            headerDTO.setApp_version(String.valueOf(AppUtils.getVersionCode(context)));
            headerDTO.setClient_ip("");
            headerDTO.setAb_sdk_version("");
            headerDTO.setPlatform("");
            headerDTO.setApp_platform("");
            headerDTO.setOs_version(AppUtils.getSystemVersion());
            headerDTO.setApp_package(AppUtils.getPackageName(context));
            headerDTO.setDevice_model(AppUtils.getSystemModel());
            //header对象 ---end


            //events对象 ---start
            List<ContentBuriedPointModel.EventsDTO> events = new ArrayList<>();
            ContentBuriedPointModel.EventsDTO eventsDTO = new ContentBuriedPointModel.EventsDTO();
            eventsDTO.setEvent(event);
            eventsDTO.setLocal_time_ms(String.valueOf(System.currentTimeMillis()));
            //params对象 ---start
            ParamsModel paramsModel = new ParamsModel();
            paramsModel.setEnter_from(Constants.ENTER_FROM);
            paramsModel.setCategory_name(category_name);
            paramsModel.setDuration(duration);
            paramsModel.setPercent(percent);
            paramsModel.setParams_for_special(Constants.PARAMS_FOR_SPECIAL);
            if (TextUtils.isEmpty(requestId)) {
                paramsModel.setReq_id("");
            } else {
                paramsModel.setReq_id(requestId);
            }

            //视频内容 id
            paramsModel.setGroup_id(contentId);
            List<ParamsModel.ItemsDTO> __items = new ArrayList<>();
            ParamsModel.ItemsDTO itemsDTO = new ParamsModel.ItemsDTO();
            List<ParamsModel.ItemsDTO.GroupItemDTO> groupItemDTOS = new ArrayList<>();
            ParamsModel.ItemsDTO.GroupItemDTO groupItemDTO = new ParamsModel.ItemsDTO.GroupItemDTO();
            groupItemDTO.setId(contentId);
            groupItemDTOS.add(groupItemDTO);
            itemsDTO.setGroup_item(groupItemDTOS);
            __items.add(itemsDTO);
            paramsModel.set__items(__items);
            Gson gson = new Gson();
            String paramJson = gson.toJson(paramsModel);
            //params对象 ---end
            eventsDTO.setParams(paramJson);
            events.add(eventsDTO);
            //events对象 ---end

            ContentBuriedPointModel.IdsDTO idsDTO = new ContentBuriedPointModel.IdsDTO();
            try {
                idsDTO.setDevice_id(param.getDeviceId());
                idsDTO.setUser_unique_id(param.getDeviceId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            idsDTO.setUser_id(PersonInfoManager.getInstance().getUserId());
            model.setUser(userDTO);
            model.setHeader(headerDTO);
            model.setEvents(events);
            model.setIds(idsDTO);
            String jsonStr = gson.toJson(model);
            JSONObject json = null;
            try {
                json = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }
        return null;
    }
}
