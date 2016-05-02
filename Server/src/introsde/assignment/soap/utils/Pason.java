package introsde.assignment.soap.utils;

import java.util.Date;


// XmlRootElement defines the root element of the XML tree to which this class will be serialized
// --> <person> ... </person> 

//@XmlRootElement(name = "person")	
// XmlType can optionally define the order in which the fields of person are written
//@XmlType(propOrder = { "firstname", "lastname", "birthdate", "healthProfile" })
//// XmlAccessorType indicates what to use to create the mapping: either FIELDS, PROPERTIES (i.e., getters/setters), PUBLIC_MEMBER or NONE (which means, you should indicate manually)
//@XmlAccessorType(XmlAccessType.FIELD)
public class Pason {
	private String firstname;		
	private String lastname;		
	// XmlElement indicates the element to use for this field. Only used if the name in XML will be different than that in the class
	//@XmlElement(name="healthProfile")
	private HealthProfilez healthProfile;	
	private String birthdate;
	// XmlAttribute indicates that this field will be serialized as an attribute
	//@XmlAttribute(name="id")
	private String personId;
	
	public Pason(String personId, String fname, String lname, String birthdate, HealthProfilez hp) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 	
		this.healthProfile=hp;
	}
	
	public Pason(String personId, String fname, String lname, String birthdate) {
		this.setPersonId(personId); 	
		this.setFirstname(fname);
		this.setLastname(lname);
		this.setBirthdate(birthdate); 
		this.healthProfile=new HealthProfilez();
	}
	
	public Pason() {
		this.firstname="Pinco";
		this.lastname="Pallino";
		this.healthProfile=new HealthProfilez();

		// setting personId to a random number between 1 and 9999
		this.personId =""; // Solution to Exercise #01-1d
		this.birthdate = new Date().toString();
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public HealthProfilez getHProfile() {
		return healthProfile;
	}
	public void setHProfile(HealthProfilez hProfile) {
		this.healthProfile = hProfile;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
