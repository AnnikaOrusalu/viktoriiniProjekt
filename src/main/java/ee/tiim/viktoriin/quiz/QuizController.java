package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("test/{id}")
    public TestAnswer getStuff(@PathVariable("id") Integer questionId) {
        return quizService.test(questionId);
    }
    // kasutame jsonist tulevat infot selleks et 2ra m22rata kysimuse question_id !

//    public String makeRandomString() {
//        Random random = new Random();
//        int randomNumber = random.nextInt(1000);
//        String randomString = "EE" + String.valueOf(randomNumber);
//        return randomString;
//    }
// 1 repo teeb yhe sql p2ringu, services 4 repo v2ljakutset.

}
