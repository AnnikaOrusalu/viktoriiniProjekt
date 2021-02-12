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

    // 1 repo teeb yhe sql p2ringu, services 4 repo v2ljakutset.


    @PostMapping("addQuestionAndAnswers")
    public void addQuestionAndAnswers(@RequestBody JsonController linker) {
        quizService.addQuestionAndAnswers(linker);

    }
    // kasutame jsonist tulevat infot selleks et 2ra m22rata kysimuse question_id !
}
