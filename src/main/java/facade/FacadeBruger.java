/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Bruger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author walkm
 */
public class FacadeBruger {
    
    EntityManagerFactory emf;
    
    public FacadeBruger()
    {
        
    }
    
    public FacadeBruger(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public EntityManagerFactory getEmf()
    {
        return emf;
    }
    
    public Bruger addBruger(Bruger u)
    {

        EntityManager em = emf.createEntityManager();

        u.addRole("Bruger");

        try
        {

            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return u;

        }
        finally
        {
            if (em != null)
            {
                em.close();
            }
        }

    }
}
