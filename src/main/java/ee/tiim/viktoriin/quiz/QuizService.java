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

//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
//    private QuizRepository quizRepository;

}
