package entity;

import java.util.ArrayList;

/**
 * Created by koloturka on 20.07.15.
 */
public class Page {

    private int id;
    private boolean endChapter;
    private int nextChapter;
    private ArrayList<Screen> screens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Screen> getScreens() {
        return screens;
    }

    public void setScreens(ArrayList<Screen> screens) {
        this.screens = screens;
    }
}
