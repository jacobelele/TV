package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PSI_DEV_07 on 1/25/2018.
 */

public class TvChannel implements Parcelable {
    private Boolean checked;
    private Integer id;
    private String imagePath;
    private Boolean imageVisible;
    private String name;
    private String nameVisible;
    private Integer number;
    private String rtspUrl;
    private Boolean timeshift;
    private Boolean timeshifting;
    private String url;

    protected TvChannel(Parcel in) {
        byte tmpChecked = in.readByte();
        checked = tmpChecked == 0 ? null : tmpChecked == 1;
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        imagePath = in.readString();
        byte tmpImageVisible = in.readByte();
        imageVisible = tmpImageVisible == 0 ? null : tmpImageVisible == 1;
        name = in.readString();
        nameVisible = in.readString();
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        rtspUrl = in.readString();
        byte tmpTimeshift = in.readByte();
        timeshift = tmpTimeshift == 0 ? null : tmpTimeshift == 1;
        byte tmpTimeshifting = in.readByte();
        timeshifting = tmpTimeshifting == 0 ? null : tmpTimeshifting == 1;
        url = in.readString();
    }

    public static final Creator<TvChannel> CREATOR = new Creator<TvChannel>() {
        @Override
        public TvChannel createFromParcel(Parcel in) {
            return new TvChannel(in);
        }

        @Override
        public TvChannel[] newArray(int size) {
            return new TvChannel[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageVisible(Boolean imageVisible) {
        this.imageVisible = imageVisible;
    }

    public void setNameVisible(String nameVisible) {
        this.nameVisible = nameVisible;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public void setTimeshift(Boolean timeshift) {
        this.timeshift = timeshift;
    }

    public void setTimeshifting(Boolean timeshifting) {
        this.timeshifting = timeshifting;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getChecked() {
        return checked;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Boolean getImageVisible() {
        return imageVisible;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getTimeshift() {
        return timeshift;
    }

    public Boolean getTimeshifting() {
        return timeshifting;
    }

    public Integer getNumber() {
        return number;
    }

    public String getNameVisible() {
        return nameVisible;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

//    public TvChannel(Parcel in){
//        this.checked = (Boolean) in.readValue(ClassLoader.getSystemClassLoader());
//        this.
//    }


    public static Creator<TvChannel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.checked);
        parcel.writeValue(this.imageVisible);
        parcel.writeInt(this.id);
        parcel.writeString(this.imagePath);
        parcel.writeString(this.name);
        parcel.writeString(this.nameVisible);
        parcel.writeInt(this.number);
        parcel.writeString(this.rtspUrl);
        parcel.writeString(this.url);
        parcel.writeValue(this.timeshift);
        parcel.writeValue(this.timeshifting);
    }

}
