package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quiz")
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    //localhost:8088/quiz/addQuestion
    @PostMapping("addQuestion")
    public void addQuestion(@RequestBody AddQuestionsRequest linker) {
        quizService.addQuestion(linker);

    }

    @PostMapping("addQuestionAndAnswers")
    public void addQuestionAndAnswers(@RequestBody AddQuestionsRequest request) {
        quizService.addQuestionAndAnswers(request);
    }

    /*
    @PostMapping("getQuestionText")
    public void getQuestionTextById(@RequestBody AddQuestionsRequest  questions_id) {
        quizService.getQuestionTextById(questions_id);
    }
     */
//    http://localhost:8080/quiz/questionAndAnswers/2
    @GetMapping("questionAndAnswers/{questionId}")
    public QuestionWithAnswers getQuestionWithAnswers(@PathVariable("questionId") Integer questionId) {

        return quizService.getQuestionWithAnswers(questionId);
    }

    // Kuidas saada panna küsimused kuvama randomiga ?


    @PostMapping("buttonValue")
    public Boolean buttonValueTheUserSelected(@RequestBody AnswersValue request) {
        return quizService.buttonValue(request);
    }

    @GetMapping("questionIds")
    public List<Integer> getQuestionIds() {
        return quizService.getQuestionIds();
    }
//    public String makeRandomString() {
//        Random random = new Random();
//        int randomNumber = random.nextInt(1000);
//        String randomString = "EE" + String.valueOf(randomNumber);
//        return randomString;
//    }
}
