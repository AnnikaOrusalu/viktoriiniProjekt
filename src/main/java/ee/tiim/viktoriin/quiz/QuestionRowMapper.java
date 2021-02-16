package ee.tiim.viktoriin.quiz;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<AnswersValue> {
    @Override
    public AnswersValue mapRow(ResultSet resultSet, int i) throws SQLException {
        AnswersValue result = new AnswersValue();
        result.setId(resultSet.getInt("answers_id"));
        result.setText(resultSet.getString("answer_text"));
        return result;
    }
}
