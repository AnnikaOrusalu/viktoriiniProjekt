package ee.tiim.viktoriin.quiz;

import java.util.List;

public class TestAnswer {
    private String questionText;
    private List<AnswersValue> answerTexts;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswersValue> getAnswerTexts() {
        return answerTexts;
    }

    public void setAnswerTexts(List<AnswersValue> answerTexts) {
        this.answerTexts = answerTexts;
    }
}
