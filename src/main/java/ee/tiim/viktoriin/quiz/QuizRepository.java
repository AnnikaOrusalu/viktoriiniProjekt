package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuizRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void addQuestion(AddQuestionsRequest linker) {
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

    // Kontrollib, et ei tekiks duplikaate questions tabelisse
    public Integer isCount(String questionText) {
        String sql = "SELECT count (*) From questions WHERE question_text = :question_text";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("question_text", questionText);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    // Kysib andmebaasist kysimuse teksti question_id j2rgi
    public String getQuestionTextById(Integer questions_id) {
        String sql = "SELECT question_text FROM questions WHERE questions_id = :questionId";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("questionId", questions_id);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public List<Question> getAnswersByQuestionId(Integer questionId) {
        String sql = "SELECT answer_text, answers_id from answers where answers_to_a_question = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", questionId);
        return jdbcTemplate.query(sql, paramMap, new QuestionRowMapper());
    }

//    public Integer getAnswersTextById(Integer Id) {
//        String sql56 = "SELECT question_text FROM questions WHERE questions_id = :questions_id";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("Id", Id);
//        return jdbcTemplate.queryForObject(sql56, paramMap, Integer.class);
//    }
}


// SELECT count (*) From questions WHERE question_text = :question_text <--- kui count on 0 on okei, count 1 t2hendab et selline text on juba olemas.