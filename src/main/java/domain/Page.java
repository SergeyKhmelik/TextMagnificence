package domain;

import javax.validation.constraints.*;
import java.util.List;

public class Page {

    @Min(value = 1, message = "Page id should be positive integer.")
    private int idPage;

    private int idChapter;

    private boolean endChapter;

    @Digits(integer = 6, fraction = 0, message = "Next chapter field should contain next chapter id.")
    private int nextChapter;

    private List<Screen> screens;

    public int getIdPage() {
        return idPage;
    }

    public void setIdPage(int idPage) {
        this.idPage = idPage;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public boolean isEndChapter() {
        return endChapter;
    }

    public void setEndChapter(boolean endChapter) {
        this.endChapter = endChapter;
    }

    public int getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(int nextChapter) {
        this.nextChapter = nextChapter;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    @Override
    public String toString() {
        return "Page{" +
                "idPage=" + idPage +
                ", idChapter=" + idChapter +
                ", endChapter=" + endChapter +
                ", nextChapter=" + nextChapter +
                ", screens=" + screens +
                '}';
    }
    
}
