/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introsde.assignment.soap.dao.mtds;

import introsde.assignment.soap.dao.Life;
import introsde.assignment.soap.model.Helthprofile;
import introsde.assignment.soap.model.Person;
import introsde.assignment.soap.model.Type;
import introsde.assignment.soap.utils.HealthProfilez;
import introsde.assignment.soap.utils.Measure;
import introsde.assignment.soap.utils.MeasureTyp;
import introsde.assignment.soap.utils.Pason;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author benhur
 */
public class PersonMtd {
    public static Pason returnPersonById(int personId) {
        EntityManager em = Life.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
	   Pason pzz = new Pason();
	    pzz.setPersonId(String.valueOf(p.getPid()));
	    pzz.setFirstname(p.getFirstname());
	    pzz.setLastname(p.getLastname());
	    pzz.setBirthdate(p.getBirthdate());
	List<Helthprofile> list = em.createNamedQuery("Healthprofile.findByPid", Helthprofile.class)
		.setParameter("pid", p.getPid())
		.getResultList();
	HealthProfilez hp = new HealthProfilez();
	for(Helthprofile hp1:list){
	if(hp1.getTid().getType().contains("weight"))
	    hp.setWeight(hp1.getValue());
	 if(hp1.getTid().getType().contains("height"))
	    hp.setHeight(hp1.getValue());
	 if(hp1.getTid().getType().contains("bloodpressure"))
	    hp.setBloodpressure(hp1.getValue());
	
	}
	
	pzz.setHProfile(hp);
        Life.instance.closeConnections(em);
        return pzz;
    }

    public static List<Pason> getAll() {	
	List<Pason> pz = new ArrayList<Pason>();
        EntityManager em = Life.instance.createEntityManager();
        List<Person> Plist = em.createNamedQuery("Person.findAll", Person.class)
          .getResultList();
	for(Person p:Plist){
	    Pason pzz = new Pason();
	    pzz.setPersonId(String.valueOf(p.getPid()));
	    pzz.setFirstname(p.getFirstname());
	    pzz.setLastname(p.getLastname());
	    pzz.setBirthdate(p.getBirthdate());
	List<Helthprofile> list = em.createNamedQuery("Healthprofile.findByPid", Helthprofile.class)
		.setParameter("pid", p.getPid())
		.getResultList();
	HealthProfilez hp = new HealthProfilez();
	for(Helthprofile hp1:list){
	    if(hp1.getTid().getType().equals("weight"))
	    hp.setWeight(hp1.getValue());
	 if(hp1.getTid().getType().equals("height"))
	    hp.setHeight(hp1.getValue());
	 if(hp1.getTid().getType().equals("bloodpressure"))
	    hp.setBloodpressure(hp1.getValue());	
		}	
	
	pzz.setHProfile(hp);
	pz.add(pzz);
	}	
        Life.instance.closeConnections(em);
        return pz;
    }

     public static List<Person> getPersonById(int personId) {
       EntityManager em = Life.instance.createEntityManager();
       List<Person> plist = em.createNamedQuery("Person.findByPid", Person.class)
	    .setParameter("pid", personId)
		.getResultList();
        Life.instance.closeConnections(em);
        return plist;
    }
    
    public static Person savePerson(Person p) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(p);
        tx.commit();
        Life.instance.closeConnections(em);
        return p;
    } 

    public static Person updatePerson(Person p) {
        EntityManager em = Life.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        tx.commit();
        Life.instance.closeConnections(em);
        return p;
    }

    public static void removePerson(Person p) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p=em.merge(p);
        em.remove(p);
        tx.commit();
        Life.instance.closeConnections(em);
    }
    
      public static List<Measure> getPersonMeasure(int personId,String ms) {
	  List<Measure> mz = new ArrayList<Measure>();
       EntityManager em = Life.instance.createEntityManager();
       List<Helthprofile> list = getHealthprof(personId);
	HealthProfilez hp = new HealthProfilez();
	for(Helthprofile hp1:list){	    
	    Measure m = new Measure();
	if(hp1.getTid().getType().equals(ms)){
	    m.setValue(hp1.getValue());
	    m.setDatechanged(hp1.getDatecreated());
	    m.setTypeId(hp1.getHid().toString());
	    mz.add(m);
	    }
	}
	
        Life.instance.closeConnections(em);
        return mz;
    }     
     
      
        public static List<Helthprofile> getHealthprof(int personId) {
        EntityManager em = Life.instance.createEntityManager(); 
         List<Helthprofile> list = em.createNamedQuery("Healthprofile.findByPid", Helthprofile.class)
		.setParameter("pid", personId)
		.getResultList();
        Life.instance.closeConnections(em);
        return list;
    }
      
      public static Measure getPersonMeasureId(int personId,String ms,int mid) {
	  List<Measure> mz = new ArrayList<Measure>();
       EntityManager em = Life.instance.createEntityManager();
       List<Helthprofile> list = getHealthprof(personId);
	HealthProfilez hp = new HealthProfilez();
	for(Helthprofile hp1:list){	    
	    Measure m = new Measure();
	if(hp1.getTid().getType().equals(ms)&&hp1.getHid().equals(mid)){
	    m.setValue(hp1.getValue());
	    m.setDatechanged(hp1.getDatecreated());
	    m.setTypeId(hp1.getHid().toString());
	    mz.add(m);
	    }
	    
	}
	
        Life.instance.closeConnections(em);
        return mz.get(0);
    }    
      
//      public static Helthprofile addMeasure(Helthprofile h) {
//         EntityManager em = Life.instance.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        em.persist(h);
//        tx.commit();
//        Life.instance.closeConnections(em);
//        return h;
//    }

      
}
