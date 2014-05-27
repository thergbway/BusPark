import org.springframework.jdbc.core.JdbcTemplate;

public class DAOImpl implements DAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
