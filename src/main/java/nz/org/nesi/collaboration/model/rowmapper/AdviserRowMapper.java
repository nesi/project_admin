package nz.org.nesi.collaboration.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import nz.org.nesi.collaboration.model.Adviser;

public class AdviserRowMapper implements RowMapper<Object>{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Adviser adviser = new Adviser(rs.getInt("id"), rs.getString("department"), rs.getString("email"), 
				rs.getString("fullName"), rs.getByte("isAdmin"));
		return adviser;
	}
	
}
