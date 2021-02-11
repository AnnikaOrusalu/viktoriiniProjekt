package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequestMapping("quiz")
@RestController
public class QuizController {


    public String makeRandomString() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        String randomString = "EE" + String.valueOf(randomNumber);
        return randomString;
    }

    @Autowired
    private QuizService quizService;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //localhost:8088/quiz/addQuestion
    @PostMapping("addQuestion")
    public void addQuestion(@RequestBody JsonController linker) {
        quizService.addQuestion(linker);

    }

    @PostMapping("addAnswersAndValues")
    public void addAnswers(@RequestBody JsonController linker) {
        String sql = "INSERT INTO answers (answer_text, true_false, answers_to_question) VALUES (:answer_text, :true_false, :answers_to_question)";
        Map<String, Object> paramMap3 = new HashMap();
        paramMap3.put("answer1", linker.getAnswer1());
        paramMap3.put("answer2", linker.getAnswer2());
        paramMap3.put("answer3", linker.getAnswer3());
        paramMap3.put("answer4", linker.getAnswer4());
        String answer1value;
        String answer2value;
        String answer3value;
        String answer4value;
        jdbcTemplate.update(sql, paramMap3);
    }
    // 1 repo teeb yhe sql p2ringu, services 4 repo v2ljakutset.
    //

    @PostMapping("addQuestionAndAnswers")
    public void addQuestionAndAnswers(@RequestBody JsonController linker) {
        String sql1 = "INSERT INTO questions (question_text) VALUES (:question_text)";
        Map<String, Object> paramMap1 = new HashMap();
        paramMap1.put("question_text", linker.getQuestionText());
        jdbcTemplate.update(sql1, paramMap1);

        String sql2 = "INSERT INTO answers (answer_text) VALUES (:answer_text)";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("answer_text", linker.getAnswer1());
        paramMap2.put("answer_text", linker.getAnswer2());
        paramMap2.put("answer_text", linker.getAnswer3());
        paramMap2.put("answer_text", linker.getAnswer4());
        jdbcTemplate.update(sql2, paramMap2);

        String sql3 = "INSERT INTO answers (true_false) VALUES (:true_false)";
        Map<String, Object> paramMap3 = new HashMap();
        paramMap3.put("true_false", linker.getAnswer1value());
        paramMap3.put("true_false", linker.getAnswer2value());
        paramMap3.put("true_false", linker.getAnswer3value());
        paramMap3.put("true_false", linker.getAnswer4value());
        jdbcTemplate.update(sql3, paramMap3);

    }
}
