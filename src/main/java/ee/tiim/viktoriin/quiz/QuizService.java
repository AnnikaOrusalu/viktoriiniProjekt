package ee.tiim.viktoriin.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    QuizRepository quizRepository;

    public void addQuestion(JsonController linker) {
        quizRepository.addQuestion(linker);

    }

    public void addAnswerAndRadioButton(String answerText, Boolean isTrue, Integer questionId) {
        quizRepository.addAnswerAndRadioButton(answerText, isTrue, questionId);
    }

    // see meetod leiab ylesse kysimuse Id selle sama kysimuse texti j2rgi.
    public Integer getQuestionIdByText(String questionText) {
        return quizRepository.getQuestionIdByText(questionText);
    }

}
