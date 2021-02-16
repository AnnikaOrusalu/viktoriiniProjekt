package ee.tiim.viktoriin.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public void addQuestion(AddQuestionsRequest linker) {
        quizRepository.addQuestion(linker);

    }

    // see meetod leiab ylesse kysimuse Id selle sama kysimuse texti j2rgi.
    public Integer getQuestionIdByText(String questionText) {

        return quizRepository.getQuestionIdByText(questionText);
    }

    public void addQuestionAndAnswers(AddQuestionsRequest request) {
        int count = quizRepository.isCount(request.questionText);
        if (count > 0) {
            throw new RuntimeException("Selline kysimus on juba kahjuks sisestatud!");
        }
        // exception oma nimega ymber nimetada. P2rast teha exception ja exception handler. Et n2itaks veakoodi 400 mitte 500
        quizRepository.addQuestion(request);
        int questionId = getQuestionIdByText(request.getQuestionText());
        // Sisesta vastust 4 korda
        addAnswerAndRadioButton(request.getAnswer1(), request.getAnswer1value(), questionId);
        addAnswerAndRadioButton(request.getAnswer2(), request.getAnswer2value(), questionId);
        addAnswerAndRadioButton(request.getAnswer3(), request.getAnswer3value(), questionId);
        addAnswerAndRadioButton(request.getAnswer4(), request.getAnswer4value(), questionId);
    }

    // private void
    private void addAnswerAndRadioButton(String answerText, Boolean isTrue, Integer questionId) {
        quizRepository.addAnswerAndRadioButton(answerText, isTrue, questionId);
    }

    public String getQuestionTextById(Integer questions_id) {
        return quizRepository.getQuestionTextById(questions_id);
    }


    public TestAnswer test(Integer questionId) {
        String question = quizRepository.getQuestionTextById(questionId);
        List<AnswersValue> answers = quizRepository.getAnswersByQuestionId(questionId);
        TestAnswer response = new TestAnswer();
        response.setQuestionText(question);
        response.setAnswerTexts(answers);
        return response;
    }


    public Boolean buttonValue(AnswersValue request) {

        return quizRepository.getAnswersValue(request.getAnswerValue());

    }


//    public boolean isCount(String questionText) {
//        boolean count = quizRepository.isCount(questionText);
////        String error = ("Selline kysimus on juba kahjuks sisestatud!");
//        boolean errorCounter = false;
//        if (!count) {
//            errorCounter = true;
//        }
//        return errorCounter;
//    }

}
// kysin andmebaasist kas selline asi on olemas v mitte, (counti), kui on selline asi olemas siis viskn exceptioni.
// SELECT count (*) From questions WHERE question_text = :question_text <--- kui count on 0 on okei, count 1 t2hendab et selline text on juba olemas.
// jdbc template tohiks olla ainult repositorys.
// quizService.addQuestion(JsonController linker) <-- siia viide Service kihist!
// Kysi just sisestatud kysimuse ID