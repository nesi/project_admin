package nz.org.nesi.collaboration.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProjectCodeRowMapper implements RowMapper<Object>{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString("projectCode");
	}

}
