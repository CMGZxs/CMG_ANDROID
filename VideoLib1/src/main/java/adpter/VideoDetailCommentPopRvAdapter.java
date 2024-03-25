package adpter;


import static common.constants.Constants.VIDEOTAG;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.pasc.lib.videolib.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import brvah.BaseMultiItemQuickAdapter;
import brvah.BaseViewHolder;
import brvah.entity.MultiItemEntity;
import common.callback.JsonCallback;
import common.http.ApiConstants;
import common.model.CommentLv1Model;
import common.model.ReplyLv2Model;
import common.utils.DateUtils;
import common.utils.GetTimeAgo;
import common.utils.PersonInfoManager;
import common.utils.ToastUtils;
import ui.activity.VideoDetailActivity;
import ui.activity.VideoHomeActivity;
import utils.GlideUtil;

public class VideoDetailCommentPopRvAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private Context mContext;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    private List<MultiItemEntity> mDatas;
    private String myContentId;
    private CommentPopRvAdapter.Lv1CommentClick lv1CommentClick;
    private CommentPopRvAdapter.Lv1No1Click lv1No1Click;
    private CommentPopRvAdapter.Lv1No2Click lv1No2Click;
    private CommentPopRvAdapter.Lv2ReplyClick lv2ReplyClick;


    public VideoDetailCommentPopRvAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(TYPE_LEVEL_1, R.layout.comment_pop_rv_item);
        addItemType(TYPE_LEVEL_2, R.layout.comment_pop_lv2_item);
    }

    public void setSrc(List<MultiItemEntity> src) {
        this.mDatas = new ArrayList<>(src);
    }

    public void setLv1CommentClick(CommentPopRvAdapter.Lv1CommentClick mLv1Click) {
        this.lv1CommentClick = mLv1Click;
    }

    public interface Lv1CommentClick {
        void Lv1Comment(String id, String replyName);
    }

    public void setLv2ReplyClick(CommentPopRvAdapter.Lv2ReplyClick mLv2ReplyClick) {
        this.lv2ReplyClick = mLv2ReplyClick;
    }

    public interface Lv2ReplyClick {
        void Lv2ReplyClick(String id, String replyName);
    }

    public void setLv1No1Click(CommentPopRvAdapter.Lv1No1Click mLv1No1Click) {
        this.lv1No1Click = mLv1No1Click;
    }

    public interface Lv1No1Click {
        void lv1No1Click(String id, String replyName);
    }

    public void setLv1No2Click(CommentPopRvAdapter.Lv1No2Click mLv1No2Click) {
        this.lv1No2Click = mLv1No2Click;
    }

    public interface Lv1No2Click {
        void lv1No2Click(String id, String replyName);
    }


    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_1:
                LinearLayout reback1Ll = helper.getView(R.id.reback1_ll);
                LinearLayout reback2Ll = helper.getView(R.id.reback2_ll);
                TextView loadMoreInfo = helper.getView(R.id.load_more_info);
                ImageView commentUserHead = helper.getView(R.id.comment_user_head);
                ImageView commentTopLabel = helper.getView(R.id.comment_top_label);
                TextView reback1NameContent = helper.getView(R.id.reback1_name_content);
                TextView reback2NameContent = helper.getView(R.id.reback2_name_content);
                final LinearLayout lv1CommentLin = helper.getView(R.id.lv1_comment_lin);
                //Lv1展开更多
                final LinearLayout lv1Extend = helper.getView(R.id.lv1_extend);
                final CommentLv1Model.DataDTO.RecordsDTO lv1Model = (CommentLv1Model.DataDTO.RecordsDTO) item;
                if (null != mContext && !((VideoDetailActivity) mContext).isFinishing()
                        && !((VideoDetailActivity) mContext).isDestroyed()) {
                    GlideUtil.displayCircle(commentUserHead, lv1Model.getHead(), true, mContext);
                }
                helper.setText(R.id.comment_pop_username, lv1Model.getNickname());
                if (TextUtils.isEmpty(lv1Model.getCreateTime())) {
                    helper.setText(R.id.comment_date, "");
                } else {
                    helper.setText(R.id.comment_date,
                            GetTimeAgo.getTimeAgo(Long.parseLong(DateUtils.date2TimeStamp(DateUtils.utc2Local(lv1Model.getCreateTime()), "yyyy-MM-dd HH:mm:ss"))));
                }
                helper.setText(R.id.comment_content, lv1Model.getContent());

                if (TextUtils.equals(lv1Model.isIsTop(), "false")) {
                    commentTopLabel.setVisibility(View.GONE);
                } else {
                    commentTopLabel.setVisibility(View.VISIBLE);
                }

                //设置第一条回复
                if (null == lv1Model.getSubItem(0) || lv1Model.getSubItems().size() == 0) {
                    reback1Ll.setVisibility(View.GONE);
                } else {
                    reback1Ll.setVisibility(View.VISIBLE);
                    if (TextUtils.equals(lv1Model.getSubItem(0).getOfficial(), "true")) {
                        reback1NameContent.setText(Html.fromHtml("<font color=#DB0025><strong>" +
                                lv1Model.getSubItem(0).getNickname()
                                + "：" + "</strong></font>" + lv1Model.getSubItem(0).getContent()));
                    } else {
                        if (TextUtils.isEmpty(lv1Model.getSubItem(0).getRnikeName())) {
                            reback1NameContent.setText(Html.fromHtml("<font color=#000000><strong>" +
                                    lv1Model.getSubItem(0).getNickname()
                                    + "：" + "</strong></font>" + lv1Model.getSubItem(0).getContent()));
                        } else {
                            reback1NameContent.setText(Html.fromHtml("<font color=#000000><strong>"
                                    + lv1Model.getSubItem(0).getNickname() + " 回复 " +
                                    lv1Model.getSubItem(0).getRnikeName() + "</strong></font>" + "：" +
                                    lv1Model.getSubItem(0).getContent()));
                        }
                    }

                    if (null == lv1Model.getSubItem(0).getCreateTime() || TextUtils.isEmpty(lv1Model.getSubItem(0).getCreateTime())) {
                        helper.setText(R.id.reback1_date, "");
                    } else {
                        helper.setText(R.id.reback1_date, GetTimeAgo.getTimeAgo(Long.parseLong(DateUtils.date2TimeStamp(DateUtils.utc2Local(lv1Model.getSubItem(0).getCreateTime()), "yyyy-MM-dd HH:mm:ss"))));
                    }
                }

                //设置第二条回复
                if (null == lv1Model.getSubItem(1) || lv1Model.getSubItems().size() <= 1) {
                    reback2Ll.setVisibility(View.GONE);
                } else {
                    reback2Ll.setVisibility(View.VISIBLE);
                    if (TextUtils.equals(lv1Model.getSubItem(1).getOfficial(), "true")) {
                        reback2NameContent.setText(Html.fromHtml("<font color=#DB0025><strong>" +
                                lv1Model.getSubItem(1).getNickname()
                                + "：" + "</strong></font>" + lv1Model.getSubItem(1).getContent()));
                    } else {
                        if (TextUtils.isEmpty(lv1Model.getSubItem(1).getRnikeName())) {
                            reback2NameContent.setText(Html.fromHtml("<font color=#000000><strong>" +
                                    lv1Model.getSubItem(1).getNickname()
                                    + "：" + "</strong></font>" + lv1Model.getSubItem(1).getContent()));
                        } else {
                            reback2NameContent.setText(Html.fromHtml("<font color=#000000><strong>"
                                    + lv1Model.getSubItem(1).getNickname() + " 回复 " +
                                    lv1Model.getSubItem(1).getRnikeName() + "</strong></font>" + "：" +
                                    lv1Model.getSubItem(1).getContent()));
                        }
                    }

                    if (TextUtils.isEmpty(lv1Model.getSubItem(1).getCreateTime())) {
                        helper.setText(R.id.reback2_date, "");
                    } else {
                        helper.setText(R.id.reback2_date, GetTimeAgo.getTimeAgo(Long.parseLong(DateUtils.date2TimeStamp(DateUtils.utc2Local(lv1Model.getSubItem(1).getCreateTime()), "yyyy-MM-dd HH:mm:ss"))));
                    }
                }

                if (null != lv1Model.getReply() && Integer.parseInt(lv1Model.getReply().getReplyNum()) > 2
                        && lv1Model.isShow()) {
                    lv1Extend.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(lv1Model.getReply().getReplyName())) {
                        String replyName = "<strong><font color=#000000>" + lv1Model.getReply().getReplyName() + "</font></strong>";
                        loadMoreInfo.setText(Html.fromHtml(replyName +
                                "等人共" + lv1Model.getReply().getReplyNum() + "条回复"));
                    }
                } else {
                    lv1Extend.setVisibility(View.GONE);
                }

                /**
                 * 第一级里的展开
                 */
                lv1Extend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //如果存在第一级里面的回复列表没有值 代表第一次展开
                        if (lv1Model.getReplyLv2CacheList().isEmpty()) {
                            getReCommentList("1", "9999", lv1Model.getId(), lv1Model, helper);
                        } else {
                            //从第一级缓存的回复列表里拿数据展开
                            int count = Math.min(lv1Model.getReplyLv2CacheList().size(), 5);
                            for (int j = 0; j < count; j++) {
                                lv1Model.getSubItems().add(lv1Model.getReplyLv2CacheList().get(0));
                                getData().add(helper.getAdapterPosition() + 1 + j, lv1Model.getReplyLv2CacheList().get(0));
                                lv1Model.getReplyLv2CacheList().remove(0);
                            }
                            lv1Model.setShow(false);
                            notifyDataSetChanged();
                        }
                    }
                });

                /**
                 * 评论回复点击
                 */
                lv1CommentLin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv1CommentClick.Lv1Comment(lv1Model.getId(), lv1Model.getNickname());
                    }
                });

                /**
                 * 第一条回复点击
                 */
                reback1Ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv1No1Click.lv1No1Click(lv1Model.getSubItem(0).getId()
                                , lv1Model.getSubItem(0).getNickname());
                    }
                });

                /**
                 * 第二条回复点击
                 */
                reback2Ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv1No2Click.lv1No2Click(lv1Model.getSubItem(1).getId()
                                , lv1Model.getSubItem(1).getNickname());
                    }
                });


                break;
            case TYPE_LEVEL_2:
                final ReplyLv2Model.ReplyListDTO lv2Model = (ReplyLv2Model.ReplyListDTO) item;
                TextView lv2RebackNameContent = helper.getView(R.id.lv2_reback_name_content);
                TextView lv2RebackDate = helper.getView(R.id.lv2_reback_date);
                LinearLayout lv2ExtendCos = helper.getView(R.id.lv2_extend_cos);
                TextView lv2LoadMoreInfo = helper.getView(R.id.lv2_load_more_info);
                final TextView lv2LoadMoreExtendTv = helper.getView(R.id.lv2_load_more_extend);

                //设置lv2评论
                if (TextUtils.equals(lv2Model.getOfficial(), "true")) {
                    lv2RebackNameContent.setText(Html.fromHtml("<font color=#DB0025><strong>" +
                            lv2Model.getNickname()
                            + "：" + "</strong></font>" + lv2Model.getContent()));
                } else {
                    if (TextUtils.isEmpty(lv2Model.getRnikeName())) {
                        lv2RebackNameContent.setText(Html.fromHtml("<font color=#000000><strong>" +
                                lv2Model.getNickname()
                                + "：" + "</strong></font>" + lv2Model.getContent()));
                    } else {
                        lv2RebackNameContent.setText(Html.fromHtml("<font color=#000000><strong>"
                                + lv2Model.getNickname() + " 回复 " +
                                lv2Model.getRnikeName() + "</strong></font>" + "：" +
                                lv2Model.getContent()));
                    }
                }

                if (TextUtils.isEmpty(lv2Model.getCreateTime())) {
                    lv2RebackDate.setText("");
                } else {
                    lv2RebackDate.setText(GetTimeAgo.getTimeAgo(Long.parseLong(DateUtils.date2TimeStamp(DateUtils.utc2Local(lv2Model.getCreateTime()), "yyyy-MM-dd HH:mm:ss"))));
                }

                int postion = helper.getAdapterPosition();
                if (postion == getData().size() - 1) {
                    for (int i = postion; i >= 0; i--) {
                        if (getData().get(i) instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                            CommentLv1Model.DataDTO.RecordsDTO level1Item = (CommentLv1Model.DataDTO.RecordsDTO) getData().get(i);
                            Log.d("yqhaaa", "展开完毕 cache -> " + level1Item.getReplyLv2CacheList().size()
                                    + " sub -> " + level1Item.getSubItems().size());
                            if (level1Item.getReplyLv2CacheList().isEmpty()) {
                                lv2ExtendCos.setVisibility(View.VISIBLE);
                                lv2LoadMoreInfo.setText("");
                                lv2LoadMoreExtendTv.setText("收起");
                                break;
                            } else {
                                lv2ExtendCos.setVisibility(View.VISIBLE);
                                lv2LoadMoreInfo.setText("");
                                lv2LoadMoreExtendTv.setText("继续展开");
                                break;
                            }
                        }
                    }
                } else {
                    if (getData().get(postion + 1) instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                        for (int i = postion; i >= 0; i--) {
                            if (getData().get(i) instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                CommentLv1Model.DataDTO.RecordsDTO level1Item = (CommentLv1Model.DataDTO.RecordsDTO) getData().get(i);
                                Log.d("yqhaaa", "展开完毕 cache -> " + level1Item.getReplyLv2CacheList().size()
                                        + " sub -> " + level1Item.getSubItems().size());
                                if (level1Item.getReplyLv2CacheList().isEmpty()) {
                                    lv2ExtendCos.setVisibility(View.VISIBLE);
                                    lv2LoadMoreInfo.setText("");
                                    lv2LoadMoreExtendTv.setText("收起");
                                    break;
                                } else {
                                    lv2ExtendCos.setVisibility(View.VISIBLE);
                                    lv2LoadMoreInfo.setText("");
                                    lv2LoadMoreExtendTv.setText("继续展开");
                                    break;
                                }
                            }
                        }
                    } else {
                        lv2ExtendCos.setVisibility(View.GONE);
                    }
                }

                //收起
                lv2ExtendCos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (lv2LoadMoreExtendTv.getText().toString().equals("收起")) {
                            for (int i = helper.getAdapterPosition(); i >= 0; i--) {
                                if (getData().get(i) instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                    CommentLv1Model.DataDTO.RecordsDTO level1Item = (CommentLv1Model.DataDTO.RecordsDTO) getData().get(i);
                                    level1Item.setExpanded(true);
                                    collapse(i);
                                    for (int j = level1Item.getSubItems().size() - 1; j >= 2; j--) {
                                        level1Item.getReplyLv2CacheList().add(0, level1Item.getSubItems().get(j));
                                        level1Item.getSubItems().remove(j);
                                    }/*你这个地方不要remove 就可以剩下两条*/
                                    level1Item.setShow(true);
                                    notifyItemChanged(helper.getAdapterPosition());
                                    notifyItemRangeRemoved(helper.getAdapterPosition() + 1, level1Item.getSubItems().size()-3);
                                    break;
                                }
                            }
                        } else {
                            for (int i = helper.getAdapterPosition(); i >= 0; i--) {
                                if (getData().get(i) instanceof CommentLv1Model.DataDTO.RecordsDTO) {
                                    CommentLv1Model.DataDTO.RecordsDTO level1Item = (CommentLv1Model.DataDTO.RecordsDTO) getData().get(i);
                                    if (level1Item.getReplyLv2CacheList().size() > 0) {
                                        Log.d("yqhaaa", "cache -> " + level1Item.getReplyLv2CacheList().size());
                                        int removeCount = Math.min(level1Item.getReplyLv2CacheList().size(), 5);
                                        for (int j = 0; j < removeCount; j++) {
                                            level1Item.getSubItems().add(level1Item.getReplyLv2CacheList().get(0));
                                            getData().add(helper.getAdapterPosition() + 1 + j, level1Item.getReplyLv2CacheList().get(0));
                                            level1Item.getReplyLv2CacheList().remove(0);
                                            Log.d("yqhaaa", "展开操作 cache -> " + level1Item.getReplyLv2CacheList().size()
                                                    + " sub -> " + level1Item.getSubItems().size());
                                        }
//                                        notifyItemChanged(helper.getAdapterPosition());
//                                        notifyItemRangeRemoved(helper.getAdapterPosition() + 1, removeCount);
                                        notifyDataSetChanged();
                                    }
                                    break;
                                }
                            }
                        }

                    }
                });

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv2ReplyClick.Lv2ReplyClick(lv2Model.getId(), lv2Model.getNickname());
                    }
                });

                break;
        }
    }

    public void setContentId(String mContentId) {
        this.myContentId = mContentId;
    }

    /**
     * 获取回复列表
     */
    public void getReCommentList(String pageIndex, String pageSize, String pcommentId, final CommentLv1Model.DataDTO.RecordsDTO lv1Model, final BaseViewHolder helper) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("contentId", myContentId);
            jsonObject.put("pageIndex", pageIndex);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pcommentId", pcommentId);
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

                        if(null!=response.body().getCode()&&response.body().getCode().equals("200")) {
                            if (null == response.body().getData()) {
                                ToastUtils.showShort(R.string.data_err);
                                return;
                            }
                            lv1Model.setShow(false);
                            List<ReplyLv2Model.ReplyListDTO> list = new ArrayList<>();
                            int size = response.body().getData().getRecords().size();
                            List<ReplyLv2Model.ReplyListDTO> fiveList = new ArrayList<>();
                            for (int i = 0; i < size; i++) {
                                if (i == 0 || i == 1) {
                                    continue;
                                }
                                CommentLv1Model.DataDTO.RecordsDTO replayModel = response.body().getData().getRecords().get(i);
                                ReplyLv2Model.ReplyListDTO lv2Model = new ReplyLv2Model.ReplyListDTO();
                                lv2Model.setId(replayModel.getId());
                                lv2Model.setContentId(replayModel.getContentId());
                                lv2Model.setUserId(replayModel.getUserId());
                                lv2Model.setContent(replayModel.getContent());
                                lv2Model.setCreateTime(replayModel.getCreateTime());
                                lv2Model.setTitle(replayModel.getTitle());
                                lv2Model.setEditor(replayModel.getEditor());
                                lv2Model.setNickname(replayModel.getNickname());
                                lv2Model.setHead(replayModel.getHead());
                                lv2Model.setTimeDif(replayModel.getTimeDif());
                                lv2Model.setIssueTimeStamp(replayModel.getIssueTimeStamp());
                                lv2Model.setChildren(replayModel.getChildren());
                                lv2Model.setIsTop(replayModel.isIsTop());
                                lv2Model.setScore(replayModel.getScore());
                                lv2Model.setPrizeId(replayModel.getPrizeId());
                                lv2Model.setPrizeOrderId(replayModel.getPrizeOrderId());
                                lv2Model.setOnShelve(replayModel.getOnShelve());
                                lv2Model.setOfficial(replayModel.getOfficial());
                                lv2Model.setReply(replayModel.getReply());
                                lv2Model.setRnikeName(replayModel.getRnikeName());
                                lv2Model.setRcommentId(replayModel.getRcommentId());
                                lv2Model.setPcommentId(replayModel.getPcommentId());
                                lv2Model.setRuserId(replayModel.getRuserId());
                                lv2Model.setPosition(i);
                                lv2Model.setParentPosition(lv1Model.getPosition());
                                if (i < 7) {
                                    fiveList.add(lv2Model);
//                                    lv1Model.getSubItems().add(lv2Model);
                                }else {
                                    lv1Model.getReplyLv2CacheList().add(lv2Model);
                                }
                                list.add(lv2Model);
                            }
//                            lv1Model.setReplyLv2CacheList(fiveList);
                            lv1Model.getSubItems().addAll(fiveList);
                            lv1Model.setReplyLv2Alllist(list);
                            getData().addAll(helper.getAdapterPosition() + 1, fiveList);
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Response<CommentLv1Model> response) {
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                            return;
                        }
                        ToastUtils.showShort(R.string.net_err);
                    }
                });
    }

}
