package ee.tiim.viktoriin.quiz;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScoreRowMapper implements RowMapper<HighScore> {
    @Override
    public HighScore mapRow(ResultSet resultSet, int i) throws SQLException {
        HighScore result = new HighScore();
        result.setPlayerName(resultSet.getString("player_name"));
        result.setPlayerPoints(resultSet.getInt("player_points"));
        return result;
    }
}
