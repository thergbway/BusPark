import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.naming.OperationNotSupportedException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    synchronized public Date getCurrentDate() {
        return new Date();
    }

    @Override
    synchronized public String getOrganizationName() {
        String sql = "select value from regs where key = \'organizationName\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    synchronized public String getCodeOKPO() {
        String sql = "select value from regs where key = \'codeOKPO\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    synchronized public String getOrganizationAddress() {
        String sql = "select value from regs where key = \'organizationAddress\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    synchronized public String getOrganizationPhoneNumber() {
        String sql = "select value from regs where key = \'organizationPhoneNumber\'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
    }

    @Override
    synchronized public String getNextListNumber() {
        String sql = "select value from regs where key = \'nextListNumber\'";
        String result = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
        if(result == null)
            return new Integer(-1).toString();
        else {
            String nextNumber = (new Integer(Integer.parseInt(result) + 1)).toString();
            String upd = "update regs set value = \'" + nextNumber + "\'" +
                    "where key = \'nextListNumber\'";
            jdbcTemplate.execute(upd);
            return result;
        }
    }

    @Override
    public String getNextListNumberWithoutIncrementing() {
        String sql = "select value from regs where key = \'nextListNumber\'";
        String result = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("value");
            }
        });
        if(result == null)
            return new Integer(-1).toString();
        else {
            return result;
        }
    }

    @Override
    synchronized public void offerOrganizationName(String organizationName) {
        if (organizationName.equals(getOrganizationName()))
            return;

        String sql = "update regs set value = \'" + organizationName + "\'" +
                "where key = \'organizationName\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    synchronized public void offerCodeOKPO(String codeOKPO) {
        if (codeOKPO.equals(getCodeOKPO()))
            return;

        String sql = "update regs set value = \'" + codeOKPO + "\'" +
                "where key = \'codeOKPO\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    synchronized public void offerOrganizationAddress(String organizationAddress) {
        if (organizationAddress.equals(getOrganizationAddress()))
            return;

        String sql = "update regs set value = \'" + organizationAddress + "\'" +
                "where key = \'organizationAddress\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    synchronized public void offerOrganizationPhoneNumber(String organizationPhoneNumber) {
        if (organizationPhoneNumber.equals(getOrganizationPhoneNumber()))
            return;

        String sql = "update regs set value = \'" + organizationPhoneNumber + "\'" +
                "where key = \'organizationPhoneNumber\'";
        jdbcTemplate.execute(sql);
    }

    @Override
    synchronized public void offerListNumber(Integer listNumber) {
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

    @Override
    synchronized public void setNextListNumber(Integer nextListNumber) {
        String upd = "update regs set value = \'" + nextListNumber + "\'" +
                "where key = \'nextListNumber\'";
        jdbcTemplate.execute(upd);
    }

    @Override
    synchronized public void offerBusBrand(String busBrand) {
        List<String> busBrandList = getBusBrandList();
        if(busBrandList.contains(busBrand))
            return;

        String sql = "insert into regs values (\'busBrand\', \'" + busBrand + "\')";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void offerBusLicensePlate(String busLicensePlate) {
        List<String> busLicensePlateList = getBusLicensePlateList();
        if(busLicensePlateList.contains(busLicensePlate))
            return;

        String sql = "insert into regs values (\'busLicensePlate\', \'" + busLicensePlate + "\')";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void offerBusDriverFullName(String busDriverFullName) {
        List<String> busDriverFullNameList = getBusDriverFullNameList();
        if (busDriverFullNameList.contains(busDriverFullName))
            return;

        String sql = "insert into regs values (\'busDriverFullName\', \'" + busDriverFullName + "\')";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void offerBusConductorFullName(String busConductorFullName) {
        List<String> busConductorFullNameList = getBusConductorFullNameList();
        if (busConductorFullNameList.contains(busConductorFullName))
            return;

        String sql = "insert into regs values (\'busConductorFullName\', \'" + busConductorFullName + "\')";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void offerRouteName(String routeName) {
        List<String> routeNameList = getRouteNameList();
        if(routeNameList.contains(routeName))
            return;

        String sql = "insert into regs values (\'routeName\', \'" + routeName + "\')";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public List<String> getBusBrandList() {
        String sql = "SELECT value FROM regs where key = \'busBrand\'";

        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> new String(resultSet.getString(1)));
        return result;
    }
    @Override
    synchronized public List<String> getBusLicensePlateList() {
        String sql = "SELECT value FROM regs where key = \'busLicensePlate\'";

        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> new String(resultSet.getString(1)));
        return result;
    }

    @Override
    synchronized public List<String> getBusDriverFullNameList() {
        String sql = "SELECT value FROM regs where key = \'busDriverFullName\'";

        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> new String(resultSet.getString(1)));
        return result;
    }

    @Override
    synchronized public List<String> getBusConductorFullNameList() {
        String sql = "SELECT value FROM regs where key = \'busConductorFullName\'";

        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> new String(resultSet.getString(1)));
        return result;
    }

    @Override
    synchronized public List<String> getRouteNameList() {
        String sql = "SELECT value FROM regs where key = \'routeName\'";

        List<String> result = jdbcTemplate.query(sql,
                (resultSet, i) -> new String(resultSet.getString(1)));
        return result;
    }

    @Override
    synchronized public void deleteBusBrand(String busBrand) {
        if (!getBusBrandList().contains(busBrand))
            return;

        String sql = "delete from regs where key =\'busBrand\' and value = \'" + busBrand + "\'";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void deleteBusLicensePlate(String busLicensePlate) {
        if (!getBusLicensePlateList().contains(busLicensePlate))
            return;

        String sql = "delete from regs where key =\'busLicensePlate\' and value = \'" + busLicensePlate + "\'";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void deleteBusDriverFullName(String busDriverFullName) {
        if (!getBusDriverFullNameList().contains(busDriverFullName))
            return;

        String sql = "delete from regs where key =\'busDriverFullName\' and value = \'" + busDriverFullName + "\'";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void deleteBusConductorFullName(String busConductorFullName) {
        if (!getBusConductorFullNameList().contains(busConductorFullName))
            return;

        String sql = "delete from regs where key =\'busConductorFullName\' and value = \'" + busConductorFullName + "\'";
        jdbcTemplate.update(sql);
    }

    @Override
    synchronized public void deleteRouteName(String routeName) {
        if (!getRouteNameList().contains(routeName))
            return;

        String sql = "delete from regs where key =\'routeName\' and value = \'" + routeName + "\'";
        jdbcTemplate.update(sql);
    }
}