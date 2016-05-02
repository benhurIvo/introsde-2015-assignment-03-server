package introsde.assignment.soap.utils;

public class HealthProfilez {
	private String weight; // in kg
	private String height; // in m
	private String bloodpressure;

	public HealthProfilez(String weight, String height,String bloodpressure) {
		this.weight = weight;
		this.height = height;
		this.bloodpressure = bloodpressure;
	}

	public HealthProfilez() {
	}
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getBloodpressure() {
		return bloodpressure;
	}

	public void setBloodpressure(String bloodpresure) {
		this.bloodpressure = bloodpresure;
	}
	
	public String toString() {
		return "Height="+height+", Weight="+weight;
	}

	}
