package nz.org.nesi.collaboration.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import nz.org.nesi.collaboration.model.Project;

public class ProjectRowMapper implements RowMapper<Object>{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Project project = new Project(rs.getInt("id"), rs.getString("department"), 
				rs.getString("name"), rs.getString("projectCode"));
		return project;
	}

}
