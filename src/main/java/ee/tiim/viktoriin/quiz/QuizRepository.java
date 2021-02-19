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
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("answer_text", answerText);
        paramMap.put("true_false", isTrue);
        paramMap.put("answers_to_a_question", questionId);
        jdbcTemplate.update(sql, paramMap);
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

    public Boolean getAnswersValue(Integer answerId) {
        String sql = "SELECT true_false from answers where answers_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", answerId);
        return jdbcTemplate.queryForObject(sql, paramMap, Boolean.class);
    }

    // meetod, mis kysib välja kõik questions_id´d välja questions tabelist
    public List<Integer> getQuestionIds() {
        String sql = "SELECT questions_id from questions";
        return jdbcTemplate.queryForList(sql, new HashMap<>(), Integer.class);
    }

    public void insertUserNameAndPoints(ResultsRequest request) {
        String sql = "INSERT INTO results (player_name, player_points) VALUES (:player_name, :player_points)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("player_name", request.getPlayerName());
        paramMap.put("player_points", request.getPlayerPoints());
        jdbcTemplate.update(sql, paramMap);
    }


    // select * from tabel order by punktisumma limit by 10
    public List<HighScore> getUserHighScore() {
        String sql = "SELECT player_name, player_points FROM results order by player_points DESC limit 10";
        return jdbcTemplate.query(sql, new HashMap<>(), new HighScoreRowMapper());
    }
}


