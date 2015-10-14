package domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by koloturka on 20.07.15.
 */
public class Chapter {

    @Min(value = 1, message = "Chapter id should be unique positive integer.")
    private int idChapter;

    /*
    @Min(value = 1, message = "Story id of the chapter should be positive integer.")
    @NotNull(message = "Story id of the chapter should not be null.")
    */
    private int idStory;

    @Pattern(regexp = ".+", message = "Chapter name should consist of at lease 1 symbol.")
    private String name;

    @NotNull(message = "Chapter description should not be NULL")
    private String description;

    private List<Integer> startPages;

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getStartPages() {
        return startPages;
    }

    public void setStartPages(List<Integer> startPages) {
        this.startPages = startPages;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "idChapter=" + idChapter +
                ", idStory=" + idStory +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPages=" + startPages +
                '}';
    }

}
