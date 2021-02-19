package ee.tiim.viktoriin.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public void addQuestion(AddQuestionsRequest linker) {
        quizRepository.addQuestion(linker);

    }

    // see meetod leiab ylesse kysimuse Id selle sama kysimuse texti j2rgi.
    public Integer getQuestionIdByText(String questionText) {
        return quizRepository.getQuestionIdByText(questionText);
    }

    public void addQuestionAndAnswers(AddQuestionsRequest request) {
        int count = quizRepository.isCount(request.questionText);
        if (count > 0) {
            throw new RuntimeException("Selline kysimus on juba kahjuks sisestatud!");
        }
        quizRepository.addQuestion(request);
        int questionId = getQuestionIdByText(request.getQuestionText());
        // Sisesta vastust 4 korda
        addAnswerAndRadioButton(request.getAnswer1(), request.getAnswer1value(), questionId);
        addAnswerAndRadioButton(request.getAnswer2(), request.getAnswer2value(), questionId);
        addAnswerAndRadioButton(request.getAnswer3(), request.getAnswer3value(), questionId);
        addAnswerAndRadioButton(request.getAnswer4(), request.getAnswer4value(), questionId);
    }

    private void addAnswerAndRadioButton(String answerText, Boolean isTrue, Integer questionId) {
        quizRepository.addAnswerAndRadioButton(answerText, isTrue, questionId);
    }

    public String getQuestionTextById(Integer questions_id) {
        return quizRepository.getQuestionTextById(questions_id);
    }


    public QuestionWithAnswers getQuestionWithAnswers(Integer questionId) {
        String question = quizRepository.getQuestionTextById(questionId);
        List<Question> answers = quizRepository.getAnswersByQuestionId(questionId);
        QuestionWithAnswers response = new QuestionWithAnswers();
        response.setQuestionText(question);
        response.setAnswerTexts(answers);
        return response;
    }

    public Boolean buttonValue(Question request) {
        return quizRepository.getAnswersValue(request.getUserAnswer());
    }

    public List<Integer> getQuestionIds() {
        return quizRepository.getQuestionIds();
    }

    public void insertUserNameAndPoints(ResultsRequest request) {
        quizRepository.insertUserNameAndPoints(request);

    }

    public List<HighScore> getUserHighScore() {

        return quizRepository.getUserHighScore();
    }
}