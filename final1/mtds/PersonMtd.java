/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final1.mtds;

import final1.dao.Life;
import final1.domain.*;
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
    public static Person returnPersonById(int personId) {
        EntityManager em = Life.instance.createEntityManager();
        Person p = em.find(Person.class, personId);
	   Person pzz = new Person();
	    pzz.setPid(p.getPid());
	    pzz.setFirstname(p.getFirstname());
	    pzz.setLastname(p.getLastname());
	    pzz.setBirthdate(p.getBirthdate());
	
	//pzz.setHProfile(hp);
        Life.instance.closeConnections(em);
        return pzz;
    }

    public static List<Person> getAll() {	
	List<Person> pz = new ArrayList<Person>();
        EntityManager em = Life.instance.createEntityManager();
        List<Person> Plist = em.createNamedQuery("Person.findAll", Person.class)
          .getResultList();
//	for(Person p:Plist){
//	    Person pzz = new Person();
//	    pzz.setPid(p.getPid());
//	    pzz.setFirstname(p.getFirstname());
//	    pzz.setLastname(p.getLastname());
//	    pzz.setBirthdate(p.getBirthdate());
//	List<Healthprofile> list = em.createNamedQuery("Healthprofile.findByPid", Healthprofile.class)
//		.setParameter("pid", p.getPid())
//		.getResultList();
//	
//	}	
        Life.instance.closeConnections(em);
        return Plist;
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
    
      public static List<Healthprofile> getPersonMeasure(int personId,String ms) {
	EntityManager em = Life.instance.createEntityManager();
       List<Healthprofile> list = new ArrayList<Healthprofile>();//getHealthprof(personId);
	List<Type> tt = TypeMtd.getTypeByString(ms);
	if(!tt.equals(null))
	    list = HealthMtd.getByPidTid(personId, tt.get(0).getTid());
	
        Life.instance.closeConnections(em);
        return list;
    }     
     
      
        public static List<Healthprofile> getHealthprof(int personId) {
        EntityManager em = Life.instance.createEntityManager(); 
         List<Healthprofile> list = em.createNamedQuery("Healthprofile.findByPid", Healthprofile.class)
		.setParameter("pid", personId)
		.getResultList();
        Life.instance.closeConnections(em);
        return list;
    }
      
      public static Healthprofile getPersonMeasureId(int personId,String ms,int mid) {
	 EntityManager em = Life.instance.createEntityManager();
       List<Healthprofile> list = HealthMtd.getByPidHid(mid, mid);	
        Life.instance.closeConnections(em);
        return list.get(0);
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
