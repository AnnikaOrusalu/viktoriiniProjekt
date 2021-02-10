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

//    @Autowired
//    private QuizService quizService;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @PostMapping("addQuestion")
    public void addQuestion(@RequestBody JsonController linker) {
        String sql = "INSERT INTO questions (question_text) VALUES (:question_text)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("question_text", linker.getQuestionText());
        jdbcTemplate.update(sql, paramMap);
    }


   /* @PostMapping("addQuestionAndAnswers")
    public void addQuestionAndAnswers(@RequestBody JsonThingy linker) {
        String sql1 = "INSERT INTO questions (question_text) VALUES (:question_text)";
        String sql2 = "INSERT INTO answers (question_text) VALUES (:question_text)";
        Map<String, Object> paramMap1 = new HashMap();
        paramMap.put("question_text", linker.getQuestionText());
        jdbcTemplate.update(sql, paramMap);
    }*/

}
