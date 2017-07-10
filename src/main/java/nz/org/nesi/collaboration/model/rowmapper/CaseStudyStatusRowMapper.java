package nz.org.nesi.collaboration.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import nz.org.nesi.collaboration.model.CaseStudyStatus;

public class CaseStudyStatusRowMapper implements RowMapper<Object>{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CaseStudyStatus status  = new CaseStudyStatus(rs.getInt("id"), rs.getString("status"));
		return status;
	}

}
