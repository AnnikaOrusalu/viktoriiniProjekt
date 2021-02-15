package ee.tiim.viktoriin.quiz;

public class AddQuestionsRequest {

    String questionText;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Boolean answer1value;
    Boolean answer2value;
    Boolean answer3value;
    Boolean answer4value;
    Integer questionId;



    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }


    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public Boolean getAnswer1value() {
        return answer1value;
    }

    public void setAnswer1value(Boolean answer1value) {
        this.answer1value = answer1value;
    }

    public Boolean getAnswer2value() {
        return answer2value;
    }

    public void setAnswer2value(Boolean answer2value) {
        this.answer2value = answer2value;
    }

    public Boolean getAnswer3value() {
        return answer3value;
    }

    public void setAnswer3value(Boolean answer3value) {
        this.answer3value = answer3value;
    }

    public Boolean getAnswer4value() {
        return answer4value;
    }

    public void setAnswer4value(Boolean answer4value) {
        this.answer4value = answer4value;
    }

}
