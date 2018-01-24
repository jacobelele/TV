package model;

/**
 * Created by PSI_DEV_07 on 1/24/2018.
 */

public class Promo {
    private String description;
    private String endDate;
    private Integer everyday;
    private Integer id;
    private String imageUrl;
    private Integer published;
    private String startDate;
    private Boolean timeout;
    private String title;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEveryday(Integer everyday) {
        this.everyday = everyday;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTimeout(Boolean timeout) {
        this.timeout = timeout;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getTimeout() {
        return timeout;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTitle() {
        return title;
    }
}
