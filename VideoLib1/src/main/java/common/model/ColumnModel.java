package common.model;

import android.text.TextUtils;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.List;
@Keep
public class ColumnModel implements Serializable {
    private String columnCode;
    private String columnDesc;
    private String columnId;
    private String columnName;
    private String columnPicName;
    private String columnPicUrl;
    private String columnType;
    private String crtDate;
    private String crtOrgCode;
    private String crtUserCode;
    private String id;
    private String isDefault;
    private String panelCode;

    public String getSkipUrl() {
        if (TextUtils.isEmpty(skipUrl)) {
            return "";
        }
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getpColumnId() {
        if (TextUtils.isEmpty(pColumnId)) {
            return "";
        }
        return pColumnId;
    }

    public void setpColumnId(String pColumnId) {
        this.pColumnId = pColumnId;
    }

    private String skipUrl;
    private String isFocusPict;
    private String isSort;
    private String pColumnId;
    private String path;
    private int rank;
    private String seq;
    private String status;
    private String updDate;
    private String updOrgCode;
    private String updUserCode;

    public String getColumnCode() {
        if (TextUtils.isEmpty(columnCode)) {
            return "";
        }
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getColumnDesc() {
        if (TextUtils.isEmpty(columnDesc)) {
            return "";
        }
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    public String getColumnId() {
        if (TextUtils.isEmpty(columnId)) {
            return "";
        }
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        if (TextUtils.isEmpty(columnName)) {
            return "";
        }
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnPicName() {
        if (TextUtils.isEmpty(columnPicName)) {
            return "";
        }
        return columnPicName;
    }

    public void setColumnPicName(String columnPicName) {
        this.columnPicName = columnPicName;
    }

    public String getColumnPicUrl() {
        if (TextUtils.isEmpty(columnPicUrl)) {
            return "";
        }
        return columnPicUrl;
    }

    public void setColumnPicUrl(String columnPicUrl) {
        this.columnPicUrl = columnPicUrl;
    }

    public String getColumnType() {
        if (TextUtils.isEmpty(columnType)) {
            return "";
        }
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getCrtDate() {
        if (TextUtils.isEmpty(crtDate)) {
            return "";
        }
        return crtDate;
    }

    public void setCrtDate(String crtDate) {
        this.crtDate = crtDate;
    }

    public String getCrtOrgCode() {
        if (TextUtils.isEmpty(crtOrgCode)) {
            return "";
        }
        return crtOrgCode;
    }

    public void setCrtOrgCode(String crtOrgCode) {
        this.crtOrgCode = crtOrgCode;
    }

    public String getCrtUserCode() {
        if (TextUtils.isEmpty(crtUserCode)) {
            return "";
        }
        return crtUserCode;
    }

    public void setCrtUserCode(String crtUserCode) {
        this.crtUserCode = crtUserCode;
    }

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            return "";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDefault() {
        if (TextUtils.isEmpty(isDefault)) {
            return "";
        }
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsFocusPict() {
        if (TextUtils.isEmpty(isFocusPict)) {
            return "";
        }
        return isFocusPict;
    }

    public void setIsFocusPict(String isFocusPict) {
        this.isFocusPict = isFocusPict;
    }

    public String getIsSort() {
        if (TextUtils.isEmpty(isSort)) {
            return "";
        }
        return isSort;
    }

    public void setIsSort(String isSort) {
        this.isSort = isSort;
    }

    public String getPColumnId() {
        if (TextUtils.isEmpty(pColumnId)) {
            return "";
        }
        return pColumnId;
    }

    public void setPColumnId(String pColumnId) {
        this.pColumnId = pColumnId;
    }

    public String getPath() {
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSeq() {
        if (TextUtils.isEmpty(seq)) {
            return "";
        }
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getStatus() {
        if (TextUtils.isEmpty(status)) {
            return "";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdDate() {
        if (TextUtils.isEmpty(updDate)) {
            return "";
        }
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    public String getUpdOrgCode() {
        if (TextUtils.isEmpty(updOrgCode)) {
            return "";
        }
        return updOrgCode;
    }

    public void setUpdOrgCode(String updOrgCode) {
        this.updOrgCode = updOrgCode;
    }

    public String getUpdUserCode() {
        if (TextUtils.isEmpty(updUserCode)) {
            return "";
        }
        return updUserCode;
    }

    public void setUpdUserCode(String updUserCode) {
        this.updUserCode = updUserCode;
    }

    public String getPanelCode() {
        if (TextUtils.isEmpty(panelCode)) {
            return "";
        }
        return panelCode;
    }

    public void setPanelCode(String panelCode) {
        this.panelCode = panelCode;
    }
}
