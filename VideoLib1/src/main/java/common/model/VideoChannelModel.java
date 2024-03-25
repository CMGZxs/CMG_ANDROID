package common.model;

import android.support.annotation.Keep;

import java.io.Serializable;
@Keep
public class VideoChannelModel implements Serializable {
    private boolean draggable;
    private boolean isDefault;
    private ColumnModel columnModel;


    public boolean isDefault() {
        return isDefault;
    }


    public ColumnModel getColumnBean() {
        return columnModel;
    }

    public void setColumnBean(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
