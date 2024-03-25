package common.model;

import android.support.annotation.Keep;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.List;

@Keep
public class CommentModel {

    private Integer code;
    private DataDTO data;
    private String detail;
    private String message;
    private Boolean success;
    private String time;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        if (null == data) {
            return new DataDTO();
        }
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getDetail() {
        if (TextUtils.isEmpty(detail)) {
            return "";
        }
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(message)) {
            return "";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        if (null == success) {
            return false;
        }
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTime() {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Keep
    public static class DataDTO {
        private Integer pageIndex;
        private Integer pageSize;
        private List<RecordsDTO> records;
        private Integer total;

        public Integer getPageIndex() {
            if (null == pageIndex) {
                return 0;
            }
            return pageIndex;
        }

        public void setPageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
        }

        public Integer getPageSize() {
            if (null == pageSize) {
                return 0;
            }
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public List<RecordsDTO> getRecords() {
            if (null == records) {
                List<RecordsDTO> list = new ArrayList<>();
                return list;
            }
            return records;
        }

        public void setRecords(List<RecordsDTO> records) {
            this.records = records;
        }

        public Integer getTotal() {
            if (null == total) {
                return 0;
            }
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        @Keep
        public static class RecordsDTO {
            private List<ChildrenDTO> children;
            private String content;
            private Integer contentId;
            private Integer createBy;
            private String createTime;
            private String editor;
            private String head;
            private Integer id;
            private String nickname;
            private Integer pcommentId;
            private Integer rcommentId;
            private String title;
            private Integer userId;
            private String isTop;
            private String onShelve;

            public List<ChildrenDTO> getChildren() {
                if (null == children) {
                    List<ChildrenDTO> list = new ArrayList<>();
                    return list;
                }
                return children;
            }

            public void setChildren(List<ChildrenDTO> children) {
                this.children = children;
            }

            public String getContent() {
                if (TextUtils.isEmpty(content)) {
                    return "";
                }
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Integer getContentId() {
                if (null == contentId) {
                    return 0;
                }
                return contentId;
            }

            public void setContentId(Integer contentId) {
                this.contentId = contentId;
            }

            public Integer getCreateBy() {
                if (null == createBy) {
                    return 0;
                }
                return createBy;
            }

            public void setCreateBy(Integer createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                if (TextUtils.isEmpty(createTime)) {
                    return "";
                }
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEditor() {
                if (TextUtils.isEmpty(editor)) {
                    return "";
                }
                return editor;
            }

            public void setEditor(String editor) {
                this.editor = editor;
            }

            public String getHead() {
                if (TextUtils.isEmpty(head)) {
                    return "";
                }
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getNickname() {
                if (TextUtils.isEmpty(nickname)) {
                    return "";
                }
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Integer getPcommentId() {
                if (null == pcommentId) {
                    return 0;
                }
                return pcommentId;
            }

            public void setPcommentId(Integer pcommentId) {
                this.pcommentId = pcommentId;
            }

            public Integer getRcommentId() {
                if (null == rcommentId) {
                    return 0;
                }
                return rcommentId;
            }

            public void setRcommentId(Integer rcommentId) {
                this.rcommentId = rcommentId;
            }

            public String getTitle() {
                if (TextUtils.isEmpty(title)) {
                    return "";
                }
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Integer getUserId() {
                if (null == userId) {
                    return 0;
                }
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }

            public String getIsTop() {
                if (TextUtils.isEmpty(isTop)) {
                    return "";
                }
                return isTop;
            }

            public void setIsTop(String isTop) {
                this.isTop = isTop;
            }

            public String getOnShelve() {
                if (TextUtils.isEmpty(onShelve)) {
                    return "";
                }
                return onShelve;
            }

            public void setOnShelve(String onShelve) {
                this.onShelve = onShelve;
            }

            @Keep
            public static class ChildrenDTO {
                private String content;
                private Integer contentId;
                private Integer createBy;
                private String createTime;
                private String editor;
                private String head;
                private Integer id;
                private String nickname;
                private Integer pcommentId;
                private Integer rcommentId;
                private String title;
                private Integer userId;

                public String getContent() {
                    if (TextUtils.isEmpty(content)) {
                        return "";
                    }
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public Integer getContentId() {
                    if (null == contentId) {
                        return 0;
                    }
                    return contentId;
                }

                public void setContentId(Integer contentId) {
                    this.contentId = contentId;
                }

                public Integer getCreateBy() {
                    if (null == createBy) {
                        return 0;
                    }
                    return createBy;
                }

                public void setCreateBy(Integer createBy) {
                    this.createBy = createBy;
                }

                public String getCreateTime() {
                    if (TextUtils.isEmpty(createTime)) {
                        return "";
                    }
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public String getEditor() {
                    if (TextUtils.isEmpty(editor)) {
                        return "";
                    }
                    return editor;
                }

                public void setEditor(String editor) {
                    this.editor = editor;
                }

                public String getHead() {
                    if (TextUtils.isEmpty(head)) {
                        return "";
                    }
                    return head;
                }

                public void setHead(String head) {
                    this.head = head;
                }

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getNickname() {
                    if (TextUtils.isEmpty(nickname)) {
                        return "";
                    }
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public Integer getPcommentId() {
                    if (null == pcommentId) {
                        return 0;
                    }
                    return pcommentId;
                }

                public void setPcommentId(Integer pcommentId) {
                    this.pcommentId = pcommentId;
                }

                public Integer getRcommentId() {
                    if (null == rcommentId) {
                        return 0;
                    }
                    return rcommentId;
                }

                public void setRcommentId(Integer rcommentId) {
                    this.rcommentId = rcommentId;
                }

                public String getTitle() {
                    if (TextUtils.isEmpty(title)) {
                        return "";
                    }
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Integer getUserId() {
                    if (null == userId) {
                        return 0;
                    }
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
                }
            }
        }
    }
}
