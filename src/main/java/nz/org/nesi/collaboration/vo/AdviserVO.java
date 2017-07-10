package nz.org.nesi.collaboration.vo;

import java.sql.Timestamp;
import java.util.Comparator;

public class AdviserVO {

	private int id;

	private String address;

	private String department;

	private String division;

	private String email;

	private String endDate;

	private String fullName;

	private String institution;

	private byte isAdmin;

	private Timestamp lastModified;

	private String notes;

	private String phone;

	private String pictureUrl;

	private String startDate;

	private String tuakiriToken;

	private String tuakiriUniqueId;

	public AdviserVO() {
	}

	public AdviserVO(int id, String department, String email, String fullName, byte isAdmin) {
		this.id = id;
		this.department = department;
		this.email = email;
		this.fullName = fullName;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public byte getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(byte isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Timestamp getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTuakiriToken() {
		return this.tuakiriToken;
	}

	public void setTuakiriToken(String tuakiriToken) {
		this.tuakiriToken = tuakiriToken;
	}

	public String getTuakiriUniqueId() {
		return this.tuakiriUniqueId;
	}

	public void setTuakiriUniqueId(String tuakiriUniqueId) {
		this.tuakiriUniqueId = tuakiriUniqueId;
	}

	public static Comparator<AdviserVO> adviserVOComparator = new Comparator<AdviserVO>() {

		public int compare(AdviserVO adviser1, AdviserVO adviser2) {

			String adviserName1 = adviser1.getFullName().toUpperCase();
			String adviserName2 = adviser2.getFullName().toUpperCase();

			// ascending order
			return adviserName1.compareTo(adviserName2);

			// descending order
			// return adviserName2.compareTo(adviserName1);
		}

	};

}