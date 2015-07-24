package entity;

import java.util.ArrayList;

/**
 * Created by koloturka on 20.07.15.
 */
public class Story {

    private int id;
    private String name;
    private String imageLink;
    private String annotation;
    private String image;
    private ArrayList<String> genre;
    private ArrayList<Chapter> chapters;

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

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public void addGenre(String genre) {
        this.genre.add(genre);
    }

    public void removeGenre(String genre) {
        this.genre.remove(genre);
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", annotation='" + annotation + '\'' +
                ", image='" + image + '\'' +
                ", genre=" + genre +
                ", chapters=" + chapters +
                '}';
    }
}
