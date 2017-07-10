package nz.org.nesi.collaboration.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.model.rowmapper.AdviserRowMapper;
import nz.org.nesi.collaboration.model.rowmapper.CaseStudyStatusRowMapper;
import nz.org.nesi.collaboration.model.rowmapper.ProjectCodeRowMapper;
import nz.org.nesi.collaboration.model.rowmapper.ProjectRowMapper;
import nz.org.nesi.collaboration.model.rowmapper.ResearcherRowMapper;
import nz.org.nesi.collaboration.util.Util;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

@Repository
public class CasestudyRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CaseStudyVO> findAll(){		
		String sql = "SELECT casestudy1.id, casestudy1.name, project1.projectCode as project, adviser1.fullName as requestor, researchers, casestudy1.mainresearcher, casestudy1.contactperson, casestudy1.requested, "
					+ "casestudy1.inprogress, casestudy1.rejected, casestudy1.approved, casestudy1.withdrawn, casestudy1.published, csstatus.status, casestudy1.url " 
						+"FROM projectdb.casestudy as casestudy1 "
						+"LEFT JOIN projectdb.project as project1 ON casestudy1.project = project1.id "
						+"LEFT JOIN projectdb.adviser as adviser1 ON casestudy1.requestor = adviser1.id "
						+"LEFT JOIN projectdb.casestudystatus as csstatus ON casestudy1.status = csstatus.id";
		List<CaseStudyVO> result = jdbcTemplate.query(sql, 
				(rse, rowNum) -> new CaseStudyVO(rse.getInt("id"), rse.getString("name"), rse.getString("project"), 
						rse.getString("requestor"), Util.formatDate(rse.getString("requested"), "yyyy-MM-dd"), Util.formatDate(rse.getString("approved"), "yyyy-MM-dd"),Util.formatDate(rse.getString("inprogress"), "yyyy-MM-dd"),
						Util.formatDate(rse.getString("withdrawn"), "yyyy-MM-dd"), Util.formatDate(rse.getString("rejected"), "yyyy-MM-dd"), Util.formatDate(rse.getString("published"), "yyyy-MM-dd"), rse.getString("researchers"), 
						rse.getString("mainresearcher"), rse.getString("contactperson"), rse.getString("status"), rse.getString("url")));
		
		for(CaseStudyVO casestudy : result) {
			if(casestudy.getResearchers() != null){
				String researcherIds[] = casestudy.getResearchers().split(",");
				String idsIn="?";
				for(int i=0; i < researcherIds.length-1; i++){
					idsIn+=",?";
				}
				List<Researcher> researcher = jdbcTemplate.query("SELECT id, email, fullName FROM projectdb.researcher WHERE id in ("+idsIn+")", researcherIds, (rse, rowNum) -> new Researcher(rse.getInt("id"), rse.getString("email"), rse.getString("fullName")));
				String researchers = "";			
				int i=0;
				for (Researcher res : researcher){
					//don't show the main researcher and contact person in the researcher list for the list page
					if(casestudy.getContactPerson() != null && casestudy.getContactPerson().equals(String.valueOf(res.getId()))){
						continue;
					}
					if(casestudy.getMainResearcher() != null && casestudy.getMainResearcher().equals(String.valueOf(res.getId()))){
						continue;
					}
					if (i==0){
						researchers = res.getFullName();
					} else {
						researchers+=", " + res.getFullName();
					}
					i++;
				}
				casestudy.setResearchers(researchers);
			}
			if(casestudy.getMainResearcher() != null){
				casestudy.setMainResearcher(getResearcherById(Integer.parseInt(casestudy.getMainResearcher())).getFullName());
			}
			if(casestudy.getContactPerson() != null){
				casestudy.setContactPerson(getResearcherById(Integer.parseInt(casestudy.getContactPerson())).getFullName());
			}
		}
		return result;
	}
	
	public List<CaseStudyVO> findCaseStudy(int id){
		String sql = "SELECT casestudy1.id, casestudy1.name, project1.projectCode as project, casestudy1.requestor as requestor, researchers, casestudy1.mainresearcher, casestudy1.contactperson, casestudy1.requested, "
					+ "casestudy1.inprogress, casestudy1.rejected, casestudy1.approved, casestudy1.withdrawn, casestudy1.published, casestudy1.status, casestudy1.url " 
						+"FROM projectdb.casestudy as casestudy1 "
						+"LEFT JOIN projectdb.project as project1 ON casestudy1.project = project1.id "
						+"WHERE casestudy1.id = '" + id + "'";
		List<CaseStudyVO> result = jdbcTemplate.query(sql, 
				(rse, rowNum) -> new CaseStudyVO(rse.getInt("id"), rse.getString("name"), rse.getString("project"), 
						rse.getString("requestor"), Util.formatDate(rse.getString("requested"), "yyyy-MM-dd"), Util.formatDate(rse.getString("approved"), "yyyy-MM-dd"),Util.formatDate(rse.getString("inprogress"), "yyyy-MM-dd"),
						Util.formatDate(rse.getString("withdrawn"), "yyyy-MM-dd"), Util.formatDate(rse.getString("rejected"), "yyyy-MM-dd"), Util.formatDate(rse.getString("published"), "yyyy-MM-dd"), rse.getString("researchers"), 
						rse.getString("mainresearcher"), rse.getString("contactperson"), rse.getString("status"), rse.getString("url")));
		return result;
	}
	
	public List<Object> getProjectCode(){
		String sql = "SELECT DISTINCT projectCode FROM projectdb.project";
		List<Object> projectCodes = jdbcTemplate.query(sql, new ProjectCodeRowMapper());
        System.out.println(projectCodes.size()); 
		return projectCodes;
	}
	
	public List<Object> getProjectByCode(String projectCode){
		String sql = "SELECT * FROM projectdb.project WHERE projectCode ='" + projectCode + "'";
		List<Object> projects = jdbcTemplate.query(sql, new ProjectRowMapper());
        System.out.println(projects.size()); 
		return projects;
	}
	
	public Project getProjectById(int projectId){
		String sql = "SELECT * FROM projectdb.project WHERE id =" + projectId;
		List<Object> projects = jdbcTemplate.query(sql, new ProjectRowMapper());
		return (Project)projects.get(0);
	}	
	
	public Researcher getResearcherById(int researcherId){
		String sql = "SELECT * FROM projectdb.researcher WHERE id =" + researcherId;
		List<Object> researchers = jdbcTemplate.query(sql, new ResearcherRowMapper());
		return (Researcher)researchers.get(0);
	}
	
	public List<Object> getResearchers(){
		String sql = "SELECT * FROM projectdb.researcher";
		List<Object> researchers = jdbcTemplate.query(sql, new ResearcherRowMapper());
        System.out.println(researchers.size()); 
		return researchers;
	}
	
	public List<Object> getAdvisers(){
		String sql = "SELECT * FROM projectdb.adviser";
		List<Object> advisers = jdbcTemplate.query(sql, new AdviserRowMapper());
        System.out.println(advisers.size()); 
		return advisers;
	}
	
	public List<Object> getStatus(){
		String sql = "SELECT * FROM projectdb.casestudystatus";
		List<Object> casestudyStatus = jdbcTemplate.query(sql, new CaseStudyStatusRowMapper());
        System.out.println(casestudyStatus.size()); 
		return casestudyStatus;
	}
	
    public Adviser getAdviserForTuakiriSharedToken(String sharedToken) throws Exception {
    	String sql = "SELECT * FROM projectdb.adviser WHERE tuakiriToken='" + sharedToken + "'";
    	List<Object> advisers = jdbcTemplate.query(sql, new AdviserRowMapper());
    	if (advisers != null) {
            if (advisers.size() == 0) {
                return null;
            }
            return (Adviser)advisers.get(0);
        }
        return null;
    }
    
    public Adviser getAdviserByEppn(String eppn) throws Exception {
    	String sql = "SELECT * FROM projectdb.adviser WHERE tuakiriUniqueId='" + eppn + "'";
    	List<Object> advisers = jdbcTemplate.query(sql, new AdviserRowMapper());
    	if (advisers != null) {
            if (advisers.size() == 0) {
                return null;
            }
            return (Adviser)advisers.get(0);
        }
        return null;
    }
	
	public int insertCasestudy(CaseStudyVO caseStudy){
		String sqlInsert = "INSERT INTO projectdb.casestudy (name, project, requestor, researchers, requested, inprogress, rejected, approved, withdrawn, published, status, url, mainresearcher, contactperson)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] {"id"});
		            ps.setString(1, caseStudy.getName());
		            ps.setInt(2, Integer.parseInt(caseStudy.getProject()));
		            ps.setInt(3, Integer.parseInt(caseStudy.getRequestor()));
		            ps.setString(4, caseStudy.getResearchers());
		            ps.setTimestamp(5, new Timestamp(Calendar.getInstance().getTime().getTime()));
                    ps.setTimestamp(6, null);
                    ps.setTimestamp(7, null);
                    ps.setTimestamp(8, null);
                    ps.setTimestamp(9, null);
                    ps.setTimestamp(10, null);
                    ps.setInt(11, Integer.parseInt(caseStudy.getStatus()));
                    ps.setString(12, caseStudy.getUrl());
                    ps.setString(13, caseStudy.getMainResearcher());
                    ps.setString(14, caseStudy.getContactPerson());
		            return ps;
		        }
		    },
		    keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public void updateCasestudy(CaseStudyVO caseStudy){
		String sqlUpdate = "UPDATE projectdb.casestudy SET name = ?, project = ?, requestor = ?, researchers = ?, requested = ?, inprogress = ?, approved = ?, published = ?, rejected = ?, withdrawn = ?, status = ?, url = ?, mainresearcher = ?, contactperson = ? WHERE id = ?";
        try {
			int rows = jdbcTemplate.update(sqlUpdate, 
					new PreparedStatementSetter(){  
						@Override
						public void setValues(java.sql.PreparedStatement ps) throws SQLException {
							// TODO Auto-generated method stub
		                    ps.setString(1, caseStudy.getName());  
		                    ps.setInt(2, Integer.parseInt(caseStudy.getProject()));  
		                    ps.setInt(3, Integer.parseInt(caseStudy.getRequestor()));
		                    ps.setString(4, caseStudy.getResearchers());
		                    
		                    ps.setTimestamp(5, caseStudy.getRequested() != null && !"".equals(caseStudy.getRequested()) ? Util.convertStringToTimestamp(caseStudy.getRequested()) : null);
		                    ps.setTimestamp(6, caseStudy.getInprogress() != null && !"".equals(caseStudy.getInprogress()) ? Util.convertStringToTimestamp(caseStudy.getInprogress()) : null);
		                    ps.setTimestamp(7, caseStudy.getApproved() != null && !"".equals(caseStudy.getApproved()) ? Util.convertStringToTimestamp(caseStudy.getApproved()) : null);
		                    ps.setTimestamp(8, caseStudy.getPublished() != null && !"".equals(caseStudy.getPublished()) ? Util.convertStringToTimestamp(caseStudy.getPublished()) : null);
		                    ps.setTimestamp(9, caseStudy.getRejected() != null && !"".equals(caseStudy.getRejected()) ? Util.convertStringToTimestamp(caseStudy.getRejected()) : null);
		                    ps.setTimestamp(10, caseStudy.getWithdrawn() != null && !"".equals(caseStudy.getWithdrawn()) ? Util.convertStringToTimestamp(caseStudy.getWithdrawn()) : null);
		                    
		                    if("1".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(5, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }else if("2".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(6, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }else if("3".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(7, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }else if("4".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(8, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }else if("5".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(9, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }else if("6".equals(caseStudy.getStatus())){
		                    	ps.setTimestamp(10, new Timestamp(Calendar.getInstance().getTime().getTime()));
		                    }

		                    ps.setInt(11, Integer.parseInt(caseStudy.getStatus()));
		                    ps.setString(12, caseStudy.getUrl());
		                    ps.setString(13, caseStudy.getMainResearcher());
		                    ps.setString(14, caseStudy.getContactPerson());
		                    ps.setInt(15, caseStudy.getId());
						}  
		            }
			);
			System.out.println(rows);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}