/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introsde.assignment.soap.utils;

/**
 *
 * @author benhur
 */
//@XmlRootElement(name = "Measure")	
//@XmlAccessorType(XmlAccessType.FIELD)
public class Measure {
private String value;		
	private String datechanged;
//	@XmlAttribute(name="id")
	private String tid;

	public Measure() {
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDatechanged() {
		return datechanged;
	}
	public void setDatechanged(String datechanged) {
		this.datechanged = datechanged;
	}
	
	public String getTypeId() {
		return tid;
	}
	public void setTypeId(String tid) {
		this.tid = tid;
	}

}

