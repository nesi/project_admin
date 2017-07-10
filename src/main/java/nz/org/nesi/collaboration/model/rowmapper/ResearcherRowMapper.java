package nz.org.nesi.collaboration.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import nz.org.nesi.collaboration.model.Researcher;

public class ResearcherRowMapper implements RowMapper<Object>{
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Researcher researcher = new Researcher(rs.getInt("id"), rs.getString("email"), rs.getString("fullName"));
		return researcher;
	}
}
