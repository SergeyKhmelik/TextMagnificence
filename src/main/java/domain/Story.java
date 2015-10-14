package domain;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by koloturka on 20.07.15.
 */

public class Story {

    @Min(value = 1, message = "Story id should be a positive integer number.")
    private int idStory;

    @Pattern(regexp = ".+")
    private String name;

    @NotEmpty(message = "Story should have at least 1 author.")
    private List<String> author;

    @Past(message = "Story creation date should be earlier than now.")
    private Date date;

    private int startPage;

    @Pattern(regexp = "(https?:\\/\\/.*\\.(?:png|jpg|jpeg|gif))", message = "Image link should be a URL-address to image file (only jpg/jpeg/gif/png format allowed.)")
    private String image;

    @NotEmpty(message = "Story should have at least 1 genre.")
    private Set<String> genre;

    @NotNull(message = "Story should have an annotation.")
    @Pattern(regexp = ".{10,}", message = "Annotation should be at least 10 symbols length.")
    private String annotation;

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + idStory +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", startPage=" + startPage +
                ", image='" + image + '\'' +
                ", genre=" + genre +
                ", annotation='" + annotation + '\'' +
                '}';
    }

}
