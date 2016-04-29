/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final1.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benhur
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPid", query = "SELECT p FROM Person p WHERE p.pid = :pid"),
    @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname"),
    @NamedQuery(name = "Person.findByBirthdate", query = "SELECT p FROM Person p WHERE p.birthdate = :birthdate"),
    @NamedQuery(name = "Person.findByUsertype", query = "SELECT p FROM Person p WHERE p.usertype = :usertype")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pid")
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "birthdate")
    private String birthdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usertype")
    private String usertype;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "uname")
    private String uname;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(Integer pid) {
	this.pid = pid;
    }

    public Person(Integer pid, String firstname, String lastname, String birthdate, String usertype, String uname, String password) {
	this.pid = pid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.birthdate = birthdate;
	this.usertype = usertype;
	this.uname = uname;
	this.password = password;
    }

    public Integer getPid() {
	return pid;
    }

    public void setPid(Integer pid) {
	this.pid = pid;
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

    public String getBirthdate() {
	return birthdate;
    }

    public void setBirthdate(String birthdate) {
	this.birthdate = birthdate;
    }

    public String getUsertype() {
	return usertype;
    }

    public void setUsertype(String usertype) {
	this.usertype = usertype;
    }

    public String getUname() {
	return uname;
    }

    public void setUname(String uname) {
	this.uname = uname;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (pid != null ? pid.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Person)) {
	    return false;
	}
	Person other = (Person) object;
	if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "final1.domain.Person[ pid=" + pid + " ]";
    }
    
}
