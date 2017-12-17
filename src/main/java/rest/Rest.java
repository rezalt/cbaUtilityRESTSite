package rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Fejl;
import facade.FacadeFejl;
import entity.Lokaler;
import facade.FacadeLokale;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Rezalt
 */
@Path("/rest")
public class Rest
{

    FacadeLokale fl;
    FacadeFejl ff;
    
    Gson gs = new Gson();
    public Rest()
    {
        fl = new FacadeLokale( Persistence.createEntityManagerFactory( "CbaUtilityPU" ) );
        ff = new FacadeFejl( Persistence.createEntityManagerFactory( "CbaUtilityPU" ) );
    }
    
    
    
    /**
     *
     * @return
     */
    @GET
    @Path("/getdata")
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getDataInJSON()
    {
        return "Test json";
    }
    
    @GET
    @Path("/lokaler")
    public String getLokaler()
    {
        JsonArray result = new JsonArray();
        List<Lokaler> lokaler = fl.getAlleLokaler();
        for (Lokaler lokale : lokaler)
        {
            JsonObject l = new JsonObject();
            l.addProperty("id", lokale.getId());
            l.addProperty("lokale", lokale.getLokale());
            l.addProperty("pladser", lokale.getPladser());

            result.add(l);
        }
        
        return gs.toJson(result);
    }
    
    @GET
    @Path("/fejl")
    public String getFejl()
    {
        JsonArray result = new JsonArray();
        List<Fejl> fejl = ff.getAlleFejl();
        for (Fejl enFejl : fejl)
        {
            JsonObject f = new JsonObject();
            f.addProperty("id", enFejl.getId());
            f.addProperty("lokale", enFejl.getLokale());
            f.addProperty("prio",enFejl.getPrio());
            f.addProperty("dato",enFejl.getDato());
            f.addProperty("beskrivelse",enFejl.getBeskrivelse());
            f.addProperty("manglerLys",enFejl.getManglerLys());
            f.addProperty("manglerProjekter",enFejl.getManglerProjekter());
            f.addProperty("manglerBorde",enFejl.getManglerBorde());
            f.addProperty("manglerStole",enFejl.getManglerStole());
                                       
           // p1.addProperty("lokale", lokale.getLokale());


            result.add(f);
        }
        
        return gs.toJson(result);
    }
    
    @GET
    @Path("lokale/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLokale(@PathParam("id") int id)
    {

        Lokaler lokale = fl.getLokale(id);

        JsonObject l = new JsonObject();
        l.addProperty("id", lokale.getId());
        l.addProperty("lokale", lokale.getLokale());
        l.addProperty("pladser", lokale.getPladser());

        return gs.toJson(l);

    }
    
    @GET
    @Path("fejl/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFejl(@PathParam("id") int id)
    {

        Fejl enFejl = ff.getFejl(id);

        JsonObject f = new JsonObject();

        f.addProperty("id", enFejl.getId());
        f.addProperty("lokale", enFejl.getLokale());
        f.addProperty("prio",enFejl.getPrio());
        f.addProperty("dato",enFejl.getDato());
        f.addProperty("beskrivelse",enFejl.getBeskrivelse());
        f.addProperty("manglerLys",enFejl.getManglerLys());
        f.addProperty("manglerProjekter",enFejl.getManglerProjekter());
        f.addProperty("manglerBorde",enFejl.getManglerBorde());
        f.addProperty("manglerStole",enFejl.getManglerStole());

        return gs.toJson(f);

    }
    
  @POST
  @Path("/addfejl")
  public void newFejl(String fejl)
  {
      JsonObject addFejl;
      addFejl = new JsonParser().parse(fejl).getAsJsonObject();
      entity.Fejl f = new entity.Fejl();
      f.setLokale(addFejl.get("lokale").getAsBigDecimal());
      
      if(addFejl.get("prio").getAsString().length() <= 1)
          f.setPrio("lav");
      else
         f.setPrio(addFejl.get("prio").getAsString());
      
      LocalDate dato = java.time.LocalDate.now();
        java.util.Date datoConverted;
        datoConverted = java.sql.Date.valueOf(dato);
      f.setDato(datoConverted);
      
      f.setBeskrivelse(addFejl.get("beskrivelse").getAsString());
      f.setManglerLys(addFejl.get("manglerLys").getAsBoolean());
      f.setManglerProjekter(addFejl.get("manglerProjekter").getAsBoolean());
      f.setManglerBorde(addFejl.get("manglerBorde").getAsBoolean());
      f.setManglerStole(addFejl.get("manglerStole").getAsBoolean());

      ff.addFejl(f);
  }
  
  
  @DELETE
  @Path("/removefejl/{id}")
  public void deleteFejl(@PathParam("id")int id)
  {
     ff.deleteFejl(ff.getFejl(id));
  }
  
}
