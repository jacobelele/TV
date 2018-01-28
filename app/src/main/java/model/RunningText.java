package model;

/**
 * Created by nazam on 29/01/18.
 */

public class RunningText {
    private Integer alltime;
    private String clientIds;
    private String content;
    private String endDate;
    private String endTime;
    private Integer everyday;
    private Integer id;
    private Integer published;
    private Boolean sending;
    private String startDate;
    private String startTime;
    private Boolean timeout;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlltime(Integer alltime) {
        this.alltime = alltime;
    }

    public void setClientIds(String clientIds) {
        this.clientIds = clientIds;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEveryday(Integer everyday) {
        this.everyday = everyday;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public void setSending(Boolean sending) {
        this.sending = sending;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setTimeout(Boolean timeout) {
        this.timeout = timeout;
    }

    public Integer getAlltime() {
        return alltime;
    }

    public Boolean getTimeout() {
        return timeout;
    }

    public Boolean getSending() {
        return sending;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEveryday() {
        return everyday;
    }

    public Integer getPublished() {
        return published;
    }

    public String getClientIds() {
        return clientIds;
    }

    public String getContent() {
        return content;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }
}
