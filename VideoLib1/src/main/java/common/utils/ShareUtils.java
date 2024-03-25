package common.utils;


import common.callback.SdkInteractiveParam;
import common.model.DataDTO;
import common.model.ShareInfo;
import common.model.VideoCollectionModel.DataDTO.RecordsDTO;

public class ShareUtils {
    public static void toShare(DataDTO item, String platform) {
        try {
            SdkInteractiveParam param = SdkInteractiveParam.getInstance();
            ShareInfo shareInfo = ShareInfo.getInstance(item.getShareUrl(), item.getShareImageUrl(),
                    item.getShareBrief(), item.getShareTitle(), platform);
            param.shared(shareInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toShare(RecordsDTO item, String platform) {
        try {
            SdkInteractiveParam param = SdkInteractiveParam.getInstance();
            ShareInfo shareInfo = ShareInfo.getInstance(item.getShareUrl(), item.getShareImageUrl(),
                    item.getShareBrief(), item.getShareTitle(), platform);
            param.shared(shareInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
