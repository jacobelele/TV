package model;

/**
 * Created by PSI_DEV_07 on 1/31/2018.
 */

public class FoodType {
    private Integer id;
    private String imagePath;
    private String name;
    private Integer number;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }
}
