package ee.tiim.viktoriin.quiz;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question result = new Question();
        result.setId(resultSet.getInt("answers_id"));
        result.setText(resultSet.getString("answer_text"));
        return result;
    }
}
