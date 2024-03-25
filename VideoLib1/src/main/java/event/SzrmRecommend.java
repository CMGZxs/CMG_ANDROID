package event;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import common.callback.JsonCallback;
import common.callback.SdkInteractiveParam;
import common.constants.Constants;
import common.http.ApiConstants;
import common.model.JumpToNativePageModel;
import common.model.ShareInfo;
import common.utils.PersonInfoManager;
import model.bean.SZContentLoadMoreModel;
import model.bean.SZContentModel;
import ui.activity.VideoHomeActivity;
import ui.activity.WebActivity;

public class SzrmRecommend {
    public static SzrmRecommend szrmRecommend;
    public SingleLiveEvent<List<SZContentModel.DataDTO.ContentsDTO>> contentsEvent = new SingleLiveEvent<>();
    public List<SZContentModel.DataDTO.ContentsDTO> contentsDTOS = new ArrayList<>();
    public SingleLiveEvent<List<SZContentModel.DataDTO.ContentsDTO>> loadMoreContentEvent = new SingleLiveEvent<>();
    public List<SZContentModel.DataDTO.ContentsDTO> loadMoreContentDTOS = new ArrayList<>();

    private SzrmRecommend() {
    }

    public static SzrmRecommend getInstance() {
        if (szrmRecommend == null) {
            synchronized (SdkInteractiveParam.class) {
                if (szrmRecommend == null) {
                    szrmRecommend = new SzrmRecommend();
                }
            }
        }
        return szrmRecommend;
    }

    /**
     * 在星沙调用推荐列表数据
     */
    public void requestContentList(String pageSize) {
        OkGo.<SZContentModel>get(ApiConstants.getInstance().getCategoryCompositeData())
                .tag("zxs_categoryCompositeData")
                .params("categoryCode", "zxs.tuijian")
                .params("personalRec", "1")
                .params("refreshType", "open")
                .params("pageSize", pageSize)
                .params("ssid", PersonInfoManager.getInstance().getANDROID_ID())
                .execute(new JsonCallback<SZContentModel>() {
                    @Override
                    public void onSuccess(Response<SZContentModel> response) {
                        if (!response.body().getData().isEmpty()) {
                            contentsEvent.setValue(response.body().getData().get(0).getContents());
                        }
                    }

                    @Override
                    public void onError(Response<SZContentModel> response) {
                        super.onError(response);
                        if (null == response.body()) {
                            return;
                        }

                        if (null == response.body().getMessage()) {
                            return;
                        }
                        Log.e("zxs_list", response.body().getMessage());
                        contentsEvent.setValue(contentsDTOS);
                    }
                });
    }

    /**
     * 在星沙调用推荐列表数据
     */
    public void requestContentList(String pageSize, final ContentListCallBack contentListCallBack) {
        OkGo.<SZContentModel>get(ApiConstants.getInstance().getCategoryCompositeData())
                .tag("zxs_categoryCompositeData")
                .params("categoryCode", "zxs.tuijian")
                .params("personalRec", "1")
                .params("refreshType", "open")
                .params("pageSize", pageSize)
                .params("ssid", PersonInfoManager.getInstance().getANDROID_ID())
                .execute(new JsonCallback<SZContentModel>() {
                    @Override
                    public void onSuccess(Response<SZContentModel> response) {
                        if (null == response.body().getData()) {
                            contentListCallBack.ContentListErrorCallBack(response.body().message);
                            return;
                        }
                        if (!response.body().getData().isEmpty()) {
                            List<SZContentModel.DataDTO.ContentsDTO> contentsDTOList = new ArrayList<>();
                            for (SZContentModel.DataDTO.ContentsDTO contentsDTO : response.body().getData().get(0).getContents()) {
                                if (!TextUtils.equals(contentsDTO.type,"activity.works")) {
                                    contentsDTOList.add(contentsDTO);
                                }

                                if (TextUtils.equals(contentsDTO.type,"activity.works")) {
                                    Log.e("测试一下过滤ugc", "----------------");
                                }
                            }

                            contentListCallBack.ContentListSuccessCallBack(contentsDTOList);
                        } else {
                            List<SZContentModel.DataDTO.ContentsDTO> list = new ArrayList<>();
                            contentListCallBack.ContentListSuccessCallBack(list);
                        }
                    }

                    @Override
                    public void onError(Response<SZContentModel> response) {
                        super.onError(response);
                        if (null == response.body()) {
                            return;
                        }

                        if (null == response.body().getMessage()) {
                            return;
                        }
                        Log.e("zxs_list", response.body().getMessage());
                        contentListCallBack.ContentListErrorCallBack(response.body().getMessage());
                    }
                });
    }

    private ContentListCallBack contentListCallBack;

    public interface ContentListCallBack {
        void ContentListSuccessCallBack(List<SZContentModel.DataDTO.ContentsDTO> response);

        abstract void ContentListErrorCallBack(String errorMessage);
    }

    public void setContentListCallBack(ContentListCallBack contentListCallBack) {
        this.contentListCallBack = contentListCallBack;
    }

    public MoreContentListCallBack moreContentListCallBack;

    public interface MoreContentListCallBack {
        void MoreContentListSuccessCallBack(List<SZContentModel.DataDTO.ContentsDTO> response);

        abstract void MoreContentListErrorCallBack(String errorMessage);
    }

    public void setMoreContentListCallBack(ContentListCallBack contentListCallBack) {
        this.contentListCallBack = contentListCallBack;
    }


    public void requestMoreContentList(SZContentModel.DataDTO.ContentsDTO contentsDTO, String pageSize) {
        OkGo.<SZContentLoadMoreModel>get(ApiConstants.getInstance().getContentList())
                .tag("zxs_moreContentList")
                .params("contentId", contentsDTO.getId())
                .params("panelId", PersonInfoManager.getInstance().getPanId())
                .params("pageSize", pageSize)
                .params("vernier", contentsDTO.getVernier())
                .params("personalRec", "1")
                .params("ssid", PersonInfoManager.getInstance().getANDROID_ID())
                .params("refreshType", "loadmore")
                .execute(new JsonCallback<SZContentLoadMoreModel>() {
                    @Override
                    public void onSuccess(Response<SZContentLoadMoreModel> response) {
                        loadMoreContentEvent.setValue(response.body().getData());
                    }

                    @Override
                    public void onError(Response<SZContentLoadMoreModel> response) {
                        super.onError(response);
                        Log.e("zxs_more_list", response.body().getMessage());
                        loadMoreContentEvent.setValue(loadMoreContentDTOS);
                    }
                });
    }

    public void requestMoreContentList(SZContentModel.DataDTO.ContentsDTO contentsDTO, String pageSize, final MoreContentListCallBack moreContentListCallBack) {
        OkGo.<SZContentLoadMoreModel>get(ApiConstants.getInstance().getContentList())
                .tag("zxs_moreContentList")
                .params("contentId", contentsDTO.getId())
                .params("panelId", PersonInfoManager.getInstance().getPanId())
                .params("pageSize", pageSize)
                .params("vernier", contentsDTO.getVernier())
                .params("personalRec", "1")
                .params("ssid", PersonInfoManager.getInstance().getANDROID_ID())
                .params("refreshType", "loadmore")
                .execute(new JsonCallback<SZContentLoadMoreModel>() {
                    @Override
                    public void onSuccess(Response<SZContentLoadMoreModel> response) {
                        if (null == response.body().getData()) {
                            moreContentListCallBack.MoreContentListErrorCallBack(response.body().getMessage());
                            return;
                        }
                        if (!response.body().getData().isEmpty()) {
                            List<SZContentModel.DataDTO.ContentsDTO> contentsDTOList = new ArrayList<>();
                            for (SZContentModel.DataDTO.ContentsDTO contentsDTO : response.body().getData()) {
                                if (!TextUtils.equals(contentsDTO.type,"activity.works")) {
                                    contentsDTOList.add(contentsDTO);
                                }

                                if (TextUtils.equals(contentsDTO.type,"activity.works")) {
                                    Log.e("测试一下过滤ugc", "----------------");
                                }
                            }

                            moreContentListCallBack.MoreContentListSuccessCallBack(contentsDTOList);
                        } else {
                            List<SZContentModel.DataDTO.ContentsDTO> list = new ArrayList<>();
                            moreContentListCallBack.MoreContentListSuccessCallBack(list);
                        }
                    }

                    @Override
                    public void onError(Response<SZContentLoadMoreModel> response) {
                        super.onError(response);
                        if (null == response.body()) {
                            return;
                        }

                        if (null == response.body().getMessage()) {
                            return;
                        }
                        Log.e("zxs_list", response.body().getMessage());
                        moreContentListCallBack.MoreContentListErrorCallBack(response.body().getMessage());
                    }
                });
    }


    /**
     * 进入新闻详情页
     */
    public void routeToDetailPage(Context context, SZContentModel.DataDTO.ContentsDTO contentsDTO) {
        if (TextUtils.equals(contentsDTO.getType(), Constants.SHORT_VIDEO)) {
            Intent intent = new Intent(context, VideoHomeActivity.class);
            intent.putExtra("contentId", contentsDTO.getId());
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(context, WebActivity.class);
            ShareInfo shareInfo = new ShareInfo();
            shareInfo.setShareImage(contentsDTO.getShareImageUrl());
            shareInfo.setShareBrief(contentsDTO.getShareBrief());
            shareInfo.setShareTitle(contentsDTO.getShareTitle());
            shareInfo.setShareUrl(contentsDTO.getShareUrl());
            JumpToNativePageModel jumpToNativePageModel = new JumpToNativePageModel();
            jumpToNativePageModel.setContentId(contentsDTO.getId());
            jumpToNativePageModel.setTitle(contentsDTO.getTitle());
            jumpToNativePageModel.setImgUrl(contentsDTO.getImagesUrl());
            jumpToNativePageModel.setLink(contentsDTO.getDetailUrl());
            jumpToNativePageModel.setNewsLink(contentsDTO.getDetailUrl());
            jumpToNativePageModel.setType(contentsDTO.getType());
            if (TextUtils.isEmpty(contentsDTO.getBrief())) {
                jumpToNativePageModel.setContent(contentsDTO.getTitle());
            } else {
                jumpToNativePageModel.setContent(contentsDTO.getBrief());
            }
            intent.putExtra("param", jumpToNativePageModel);
            intent.putExtra("intent", "1");
            intent.putExtra("shareInfo", shareInfo);
            context.startActivity(intent);
        }
    }

}
