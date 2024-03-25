package common.model;


import android.support.annotation.Keep;


import java.util.List;

import brvah.entity.AbstractExpandableItem;
import brvah.entity.MultiItemEntity;


@Keep
public class ReplyLv2Model {

    private String replyName;
    private String replyNum;
    private List<ReplyListDTO> replyList;

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public List<ReplyListDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListDTO> replyList) {
        this.replyList = replyList;
    }

    @Keep
    public static class ReplyListDTO extends AbstractExpandableItem implements MultiItemEntity {
        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getItemType() {
            return 2;
        }

        private String id;
        private String contentId;
        private String userId;
        private String content;
        private String createTime;
        private String createBy;
        private String title;
        private String editor;
        private String nickname;
        private String head;
        private String timeDif;
        private String issueTimeStamp;
        private Object children;
        private String isTop;
        private String score;
        private String prizeId;
        private String prizeOrderId;
        private String onShelve;
        private Object reply;
        private String rnikeName;
        private String rcommentId;
        private String pcommentId;
        private String ruserId;
        private String official;
        private int position;
        private int parentPosition;
        private boolean whetherLike; //当前用户是否已经点赞过
        private Integer likeCount; //当前评论的点赞数量

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getTimeDif() {
            return timeDif;
        }

        public void setTimeDif(String timeDif) {
            this.timeDif = timeDif;
        }

        public String getIssueTimeStamp() {
            return issueTimeStamp;
        }

        public void setIssueTimeStamp(String issueTimeStamp) {
            this.issueTimeStamp = issueTimeStamp;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public String isIsTop() {
            return isTop;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPrizeId() {
            return prizeId;
        }

        public void setPrizeId(String prizeId) {
            this.prizeId = prizeId;
        }

        public String getPrizeOrderId() {
            return prizeOrderId;
        }

        public void setPrizeOrderId(String prizeOrderId) {
            this.prizeOrderId = prizeOrderId;
        }

        public String getOnShelve() {
            return onShelve;
        }

        public void setOnShelve(String onShelve) {
            this.onShelve = onShelve;
        }

        public Object getReply() {
            return reply;
        }

        public void setReply(Object reply) {
            this.reply = reply;
        }

        public String getRnikeName() {
            return rnikeName;
        }

        public void setRnikeName(String rnikeName) {
            this.rnikeName = rnikeName;
        }

        public String getRcommentId() {
            return rcommentId;
        }

        public void setRcommentId(String rcommentId) {
            this.rcommentId = rcommentId;
        }

        public String getPcommentId() {
            return pcommentId;
        }

        public void setPcommentId(String pcommentId) {
            this.pcommentId = pcommentId;
        }

        public String getRuserId() {
            return ruserId;
        }

        public void setRuserId(String ruserId) {
            this.ruserId = ruserId;
        }

        public String getOfficial() {
            return official;
        }

        public void setOfficial(String official) {
            this.official = official;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getParentPosition() {
            return parentPosition;
        }

        public void setParentPosition(int parentPosition) {
            this.parentPosition = parentPosition;
        }

        public boolean getWhetherLike() {
            return whetherLike;
        }

        public void setWhetherLike(boolean whetherLike) {
            this.whetherLike = whetherLike;
        }

        public Integer getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Integer likeCount) {
            this.likeCount = likeCount;
        }
    }
}
