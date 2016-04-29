/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final1.mtds;

import final1.dao.Life;
import final1.domain.Goal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author benhur
 */
public class GoalMtd {
     public static Goal getGoalById(int gid) {
        EntityManager em = Life.instance.createEntityManager();
        Goal h = em.find(Goal.class, gid);
        Life.instance.closeConnections(em);
        return h;
    }

    public static List<Goal> getAll() {
        EntityManager em = Life.instance.createEntityManager();
        List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class)
            .getResultList();
        Life.instance.closeConnections(em);
        return list;
    }

    public static Goal saveGoal(Goal h) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(h);
        tx.commit();
        Life.instance.closeConnections(em);
        return h;
    } 

    public static Goal updateGoal(Goal h) {
        EntityManager em = Life.instance.createEntityManager(); 
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        h=em.merge(h);
        tx.commit();
        Life.instance.closeConnections(em);
        return h;
    }

    public static List<Goal> getByPidTid(int tid,int pid) {
     EntityManager em = Life.instance.createEntityManager(); 
         List<Goal> list = em.createNamedQuery("Goal.findByPidTid", Goal.class)
		.setParameter("pid", pid)
		.setParameter("tid", tid)
		.getResultList();
        Life.instance.closeConnections(em);
	return list;
    }
    
    public static void removeGoal(Goal g) {
        EntityManager em = Life.instance.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        g=em.merge(g);
        em.remove(g);
        tx.commit();
        Life.instance.closeConnections(em);
    }
    
}
