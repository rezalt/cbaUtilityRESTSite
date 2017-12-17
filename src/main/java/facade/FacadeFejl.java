/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Fejl;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Rezalt
 */
public class FacadeFejl {

    EntityManagerFactory emf;

    public FacadeFejl() {
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        }
        return emf.createEntityManager();

    }

    public FacadeFejl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Fejl getFejl(int id) {
        EntityManager em = getEntityManager();

        Fejl f = null;

        try {
            em.getTransaction().begin();
            f = em.find(Fejl.class, id);
            em.getTransaction().commit();
            return f;
        } finally {
            em.close();
        }
    }

    public Fejl addFejl(Fejl f) {

        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            SendNotification(f);
            return f;

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    // Send push notifikation til alle der er subscribed - 
    // med lokale nr, og beskrivelse af fejlen/manglen.
    public void SendNotification(Fejl _fejl) {
        try {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic 'TILFØJ REST API KEY HER");
            con.setRequestMethod("POST");
            
            String strJsonBody = "{ \"app_id\": \"'TILFØJ APP ID HER'\", \"included_segments\": [\"All\"], \"data\": { \"foo\": \"bar\" }, \"contents\": { \"en\": \"Ny mangel i lokale: "+_fejl.getLokale()+" \nBeskrivelse: "+_fejl.getBeskrivelse()+"\"  } }";
            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void deleteFejl(Fejl fejl) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        fejl = em.merge(fejl);
        em.remove(fejl);
        em.getTransaction().commit();
        em.close();
    }

    public List<Fejl> getAlleFejl() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT f FROM Fejl f");
        return query.getResultList();
    }

}
