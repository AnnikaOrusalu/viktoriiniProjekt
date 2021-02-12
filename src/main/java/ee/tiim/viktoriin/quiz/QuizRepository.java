package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class QuizRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addQuestion(JsonController linker) {
        String sql = "INSERT INTO questions (question_text) VALUES (:question_text)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("question_text", linker.getQuestionText());
        jdbcTemplate.update(sql, paramMap);
    }

    public void addAnswerAndRadioButton(String answerText, Boolean isTrue, Integer questionId) {
        String sql = "INSERT INTO answers (answer_text, true_false, answers_to_a_question) VALUES (:answer_text, :true_false, :answers_to_a_question)";
        Map<String, Object> paramMap4 = new HashMap();
        paramMap4.put("answer_text", answerText);
        paramMap4.put("true_false", isTrue);
        paramMap4.put("answers_to_a_question", questionId);
        jdbcTemplate.update(sql, paramMap4);
    }

    // Seda meetodit on vaja, et vastus teaks missuguse kysimuse vastus ta on
    public Integer getQuestionIdByText(String questionText) {
        String sql = "SELECT questions_id FROM questions WHERE question_text = :question_text";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("question_text", questionText);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

}
