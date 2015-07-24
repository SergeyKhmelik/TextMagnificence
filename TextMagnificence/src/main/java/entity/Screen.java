package entity;

import java.util.ArrayList;

/**
 * Created by koloturka on 20.07.15.
 */
public class Screen {

    private int id;
    private String text;
    private ArrayList<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
