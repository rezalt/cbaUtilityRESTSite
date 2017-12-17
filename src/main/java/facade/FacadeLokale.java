/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Lokaler;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Rezalt
 */

public class FacadeLokale
{

    EntityManagerFactory emf;
    
    public FacadeLokale()
    {
    }

    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public EntityManagerFactory getEmf()
    {
        return emf;
    }    
    
    public FacadeLokale(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public Lokaler getLokale(int id)
    {
        EntityManager em = emf.createEntityManager();

        Lokaler l = null;
        
        try
        {
            em.getTransaction().begin();
            l = em.find(Lokaler.class, id);
            em.getTransaction().commit();
            return l;
        }
        finally
        {
            em.close();
        }    
    }
    
    
    public List<Lokaler> getAlleLokaler()
    {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT l FROM Lokaler l");
        return query.getResultList();
    }

    
}
