package ee.tiim.viktoriin.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    QuizRepository quizRepository;

    public void addQuestion(JsonController linker) {
        quizRepository.addQuestion(linker);

    }

    public void addAnswerAndRadioButton(String answerText, Boolean isTrue, Integer questionId) {
        quizRepository.addAnswerAndRadioButton(answerText, isTrue, questionId);
    }

    // see meetod leiab ylesse kysimuse Id selle sama kysimuse texti j2rgi.
    public Integer getQuestionIdByText(String questionText) {

        return quizRepository.getQuestionIdByText(questionText);
    }

    public void addQuestionAndAnswers(JsonController linker) {
        quizRepository.addQuestion(linker);
//        String sql1 = "INSERT INTO questions (question_text) VALUES (:question_text)";
//        Map<String, Object> paramMap1 = new HashMap();
//        paramMap1.put("question_text", linker.getQuestionText());
//        jdbcTemplate.update(sql1, paramMap1);
        // jdbc template tohiks olla ainult repositorys.
        // quizService.addQuestion(JsonController linker) <-- siia viide Service kihist!
        // Kysi just sisestatud kysimuse ID
        int questionId = getQuestionIdByText(linker.getQuestionText());
        // Sisesta vastust 4 korda
        addAnswerAndRadioButton(linker.getAnswer1(), linker.getAnswer1value(), questionId);
        addAnswerAndRadioButton(linker.getAnswer2(), linker.getAnswer2value(), questionId);
        addAnswerAndRadioButton(linker.getAnswer3(), linker.getAnswer3value(), questionId);
        addAnswerAndRadioButton(linker.getAnswer4(), linker.getAnswer4value(), questionId);
    }
//    public void isCount(String questionText) {
//        int count = quizRepository.isCount(questionText);
//        if (count > 0) {
//            throw new QuizException("Selline kysimus on juba kahjuks sisestatud!");
//        }
//    }

}
// kysin andmebaasist kas selline asi on olemas v mitte, (counti), kui on selline asi olemas siis viskn exceptioni.
// SELECT count (*) From questions WHERE question_text = :question_text <--- kui count on 0 on okei, count 1 t2hendab et selline text on juba olemas.