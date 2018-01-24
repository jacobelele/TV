package model;

/**
 * Created by PSI_DEV_07 on 1/23/2018.
 */

public class Food {
    private Integer clickRate;
    private String content;
    private Integer id;
    private String imagePath;
    private String name;
    private Integer price;
    private Integer typeId;

    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getClickRate() {
        return clickRate;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }
}
