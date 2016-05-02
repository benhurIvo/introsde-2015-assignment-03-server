/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introsde.assignment.soap.dao.mtds;

import introsde.assignment.soap.dao.Life;
import introsde.assignment.soap.model.Helthprofile;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author benhur
 */
public class HealthMtd {
       public static Helthprofile getHealthById(int hid) {
        EntityManager em = Life.instance.createEntityManager();
        Helthprofile h = em.find(Helthprofile.class, hid);
        Life.instance.closeConnections(em);
        return h;
    }

    public static List<Helthprofile> getAll() {
        EntityManager em = Life.instance.createEntityManager();
        List<Helthprofile> list = em.createNamedQuery("Health.findAll", Helthprofile.class)
            .getResultList();
        Life.instance.closeConnections(em);
        return list;
    }

    public static Helthprofile saveHealthprofile(Helthprofile h) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(h);
        tx.commit();
        Life.instance.closeConnections(em);
        return h;
    } 

    public static Helthprofile updateHealth(Helthprofile h) {
        EntityManager em = Life.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        h=em.merge(h);
        tx.commit();
        Life.instance.closeConnections(em);
        return h;
    }

    public static List<Helthprofile> getByPidHid(int hid,int pid) {
     EntityManager em = Life.instance.createEntityManager(); 
         List<Helthprofile> list = em.createNamedQuery("Healthprofile.findByPidHid", Helthprofile.class)
		.setParameter("pid", pid)
		.setParameter("hid", hid)
		.getResultList();
        Life.instance.closeConnections(em);
	return list;
    }
    
    public static void removeHealth(Helthprofile h) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        h=em.merge(h);
        em.remove(h);
        tx.commit();
        Life.instance.closeConnections(em);
    }
    
}
