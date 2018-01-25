package model;

/**
 * Created by PSI_DEV_07 on 1/25/2018.
 */

public class TvChannel {
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
}
