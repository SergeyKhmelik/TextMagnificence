package domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Screen {

    @Min(value = 1, message = "Screen id should be positive integer.")
    private int idScreen;

    @NotNull
    @Size(min = 2, max = 3000)
    private String text;

    private List<Answer> answers;

    public int getIdScreen() {
        return idScreen;
    }

    public void setIdScreen(int idScreen) {
        this.idScreen = idScreen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "idScreen=" + idScreen +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }
}
