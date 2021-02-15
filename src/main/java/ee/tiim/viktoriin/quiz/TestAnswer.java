package ee.tiim.viktoriin.quiz;

import java.util.List;

public class TestAnswer {
    private String questionText;
    private List<Question> answerTexts;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Question> getAnswerTexts() {
        return answerTexts;
    }

    public void setAnswerTexts(List<Question> answerTexts) {
        this.answerTexts = answerTexts;
    }
}
