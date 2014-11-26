package sunshine.test.rankun.org.sunshine;

/**
 * Created by rankun203 on 11/7/14.
 */
public class TrueFalse {
    private int question;
    private boolean trueQuestion;
    // If the question was cheated
    private boolean cheated;

    public TrueFalse(int question, boolean trueQuestion) {
        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTrueQuestion() {
        return trueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }

    public boolean isCheated() {
        return cheated;
    }

    public void setCheated(boolean cheated) {
        this.cheated = cheated;
    }
}
