package model;

/**
 * Created by PSI_DEV_07 on 1/24/2018.
 */

public class Room {
    private Integer checkInTime;
    private String checkPerson;
    private Boolean checked;
    private Integer costSum;
    private Boolean enable;
    private Integer groupId;
    private Integer id;
    private String ip;
    private String mac;
    private Integer money;
    private String name;
    private Boolean online;
    private long timeMills;
    private Integer unfinishedOrderCount;
    private Integer versionCode;
    private String versionText;
    private String welcomWord;
    private String wifiName;
    private String wifiPassword;
    private Integer wifiStatus;

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setCheckInTime(Integer checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public void setCostSum(Integer costSum) {
        this.costSum = costSum;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public void setTimeMills(long timeMills) {
        this.timeMills = timeMills;
    }

    public void setUnfinishedOrderCount(Integer unfinishedOrderCount) {
        this.unfinishedOrderCount = unfinishedOrderCount;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionText(String versionText) {
        this.versionText = versionText;
    }

    public void setWelcomWord(String welcomWord) {
        this.welcomWord = welcomWord;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public void setWifiStatus(Integer wifiStatus) {
        this.wifiStatus = wifiStatus;
    }

    public Boolean getChecked() {
        return checked;
    }

    public Integer getCheckInTime() {
        return checkInTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCostSum() {
        return costSum;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public String getName() {
        return name;
    }

    public Boolean getOnline() {
        return online;
    }

    public Integer getMoney() {
        return money;
    }

    public long getTimeMills() {
        return timeMills;
    }

    public String getIp() {
        return ip;
    }

    public Integer getUnfinishedOrderCount() {
        return unfinishedOrderCount;
    }

    public String getMac() {
        return mac;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public Integer getWifiStatus() {
        return wifiStatus;
    }

    public String getVersionText() {
        return versionText;
    }

    public String getWelcomWord() {
        return welcomWord;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }
}
