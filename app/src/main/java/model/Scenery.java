package model;

import java.util.List;

/**
 * Created by PSI_DEV_07 on 1/30/2018.
 */

public class Scenery {
    private String description;
    private Integer id;
    private String imagePath;
    private List<String> items;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
