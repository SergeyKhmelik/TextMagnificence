package entity;

import java.util.ArrayList;

/**
 * Created by koloturka on 20.07.15.
 */
public class Chapter {

    private int id;
    private String name;
    private String description;
    private ArrayList<Integer> pages;

    public Chapter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Integer> pages) {
        this.pages = pages;
    }

}
