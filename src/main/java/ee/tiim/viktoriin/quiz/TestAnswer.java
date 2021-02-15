package ee.tiim.viktoriin.quiz;

import java.util.List;

public class TestAnswer {
    private String questionText;
    private List<String> answerTexts;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswerTexts() {
        return answerTexts;
    }

    public void setAnswerTexts(List<String> answerTexts) {
        this.answerTexts = answerTexts;
    }
}
