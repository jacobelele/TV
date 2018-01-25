package model;

/**
 * Created by PSI_DEV_07 on 1/25/2018.
 */

public class Setting {
//    private Integer apkUpdateType;
//    private Integer chargeMode;
//    private Boolean checkOutCleanFavorite;
//    private Integer indexArea;
//    private Boolean menuCarterVisible;
//    private Boolean menuRecVisible;
//    private Boolean menuSceneryVisible;
//    private String operatorNumber;
    private String protocol;
    private Boolean showLogo;
    private Boolean showWaterMark;
    private String timeshiftPlayPort;
    private String timeshiftServerPort;

    public String getProtocol() {
        return protocol;
    }

    public Boolean getShowLogo() {
        return showLogo;
    }

    public Boolean getShowWaterMark() {
        return showWaterMark;
    }

    public String getTimeshiftPlayPort() {
        return timeshiftPlayPort;
    }

    public String getTimeshiftServerPort() {
        return timeshiftServerPort;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setShowLogo(Boolean showLogo) {
        this.showLogo = showLogo;
    }

    public void setShowWaterMark(Boolean showWaterMark) {
        this.showWaterMark = showWaterMark;
    }

    public void setTimeshiftPlayPort(String timeshiftPlayPort) {
        this.timeshiftPlayPort = timeshiftPlayPort;
    }

    public void setTimeshiftServerPort(String timeshiftServerPort) {
        this.timeshiftServerPort = timeshiftServerPort;
    }
}
