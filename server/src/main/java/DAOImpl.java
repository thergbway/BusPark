import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOImpl implements DAO {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        initDBIfNeed();
    }

    private void initDBIfNeed() {
        boolean isRegsExists = false;
        try {
            Connection conn = jdbcTemplate.getDataSource().getConnection();
            ResultSet rs = conn.getMetaData().getTables(null,null,"%",null);
            while (rs.next()) {
                if(rs.getString(3).equals("regs"))
                    isRegsExists = true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS regs (key TEXT NOT NULL,value TEXT)");
        if(!isRegsExists) {
            jdbcTemplate.update("insert into regs values (\'nextListNumber\', NULL)");
            jdbcTemplate.update("insert into regs values (\'organizationName\', NULL)");
            jdbcTemplate.update("insert into regs values (\'codeOKPO\', NULL)");
            jdbcTemplate.update("insert into regs values (\'organizationAddress\', NULL)");
            jdbcTemplate.update("insert into regs values (\'organizationPhoneNumber\', NULL)");
        }
    }

    @Override
    public String getOrganizationName() {
        String sql = "select value from regs where key = \'organizationName\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    public String getCodeOKPO() {
        String sql = "select value from regs where key = \'codeOKPO\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    public String getOrganizationAddress() {
        String sql = "select value from regs where key = \'organizationAddress\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    public String getOrganizationPhoneNumber() {
        String sql = "select value from regs where key = \'organizationPhoneNumber\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    public String getNextListNumber() {
        String sql = "select value from regs where key = \'nextListNumber\'";
        String result = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
        if(result == null)
            return result;
        else {
            String nextNumber = (new Integer(Integer.parseInt(result) + 1)).toString();
            String upd = "update regs set value = \'" + nextNumber + "\'" +
                    "where key = \'nextListNumber\'";
            jdbcTemplate.execute(upd);
            return result;
        }
    }

    @Override
    public void offerOrganizationName(String organizationName) {
        if (organizationName.equals(getOrganizationName()))
            return;

        String sql = "update regs set value = \'" + organizationName + "\'" +
                "where key = \'organizationName\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void offerCodeOKPO(String codeOKPO) {
        if (codeOKPO.equals(getCodeOKPO()))
            return;

        String sql = "update regs set value = \'" + codeOKPO + "\'" +
                "where key = \'codeOKPO\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void offerOrganizationAddress(String organizationAddress) {
        if (organizationAddress.equals(getOrganizationAddress()))
            return;

        String sql = "update regs set value = \'" + organizationAddress + "\'" +
                "where key = \'organizationAddress\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void offerOrganizationPhoneNumber(String organizationPhoneNumber) {
        if (organizationPhoneNumber.equals(getOrganizationPhoneNumber()))
            return;

        String sql = "update regs set value = \'" + organizationPhoneNumber + "\'" +
                "where key = \'organizationPhoneNumber\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void offerListNumber(Integer listNumber) {
        String sql = "select value from regs where key = \'nextListNumber\'";
        String result = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
        if(result != null)
            return;

        Integer nextListNumber = listNumber + 1;
        String upd = "update regs set value = \'" + nextListNumber + "\'" +
                "where key = \'nextListNumber\'";
        jdbcTemplate.execute(upd);
    }
}