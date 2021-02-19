package ee.tiim.viktoriin.quiz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quiz")
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("addQuestion")
    public void addQuestion(@RequestBody AddQuestionsRequest request) {
        quizService.addQuestion(request);
    }

    @PostMapping("addQuestionAndAnswers")
    public void addQuestionAndAnswers(@RequestBody AddQuestionsRequest request) {
        quizService.addQuestionAndAnswers(request);
    }

    @GetMapping("questionAndAnswers/{questionId}")
    public QuestionWithAnswers getQuestionWithAnswers(@PathVariable("questionId") Integer questionId) {

        return quizService.getQuestionWithAnswers(questionId);
    }

    @PostMapping("buttonValue")
    public Boolean buttonValueTheUserSelected(@RequestBody Question request) {
        return quizService.buttonValue(request);
    }

    @GetMapping("questionIds")
    public List<Integer> getQuestionIds() {
        return quizService.getQuestionIds();
    }

    @PostMapping("insertUserNameAndPoints")
    public void insertUserNameAndPoints(@RequestBody ResultsRequest request) {
        quizService.insertUserNameAndPoints(request);
    }

    @GetMapping("getUserHighScore")
    public List<HighScore> getUserHighScore() {
        return quizService.getUserHighScore();
    }
}
