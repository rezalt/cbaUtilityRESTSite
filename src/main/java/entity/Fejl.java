/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rezalt
 */
@Entity
@Table(name = "fejl")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Fejl.findAll", query = "SELECT f FROM Fejl f"),
    @NamedQuery(name = "Fejl.findById", query = "SELECT f FROM Fejl f WHERE f.id = :id"),
    @NamedQuery(name = "Fejl.findByLokale", query = "SELECT f FROM Fejl f WHERE f.lokale = :lokale"),
    @NamedQuery(name = "Fejl.findByPrio", query = "SELECT f FROM Fejl f WHERE f.prio = :prio"),
    @NamedQuery(name = "Fejl.findByDato", query = "SELECT f FROM Fejl f WHERE f.dato = :dato"),
    @NamedQuery(name = "Fejl.findByBeskrivelse", query = "SELECT f FROM Fejl f WHERE f.beskrivelse = :beskrivelse"),
    @NamedQuery(name = "Fejl.findByManglerLys", query = "SELECT f FROM Fejl f WHERE f.manglerLys = :manglerLys"),
    @NamedQuery(name = "Fejl.findByManglerProjekter", query = "SELECT f FROM Fejl f WHERE f.manglerProjekter = :manglerProjekter"),
    @NamedQuery(name = "Fejl.findByManglerBorde", query = "SELECT f FROM Fejl f WHERE f.manglerBorde = :manglerBorde"),
    @NamedQuery(name = "Fejl.findByManglerStole", query = "SELECT f FROM Fejl f WHERE f.manglerStole = :manglerStole")
})
public class Fejl implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Lokale")
    private BigDecimal lokale;
    @Column(name = "prio")
    private String prio;
    @Column(name = "dato")
    @Temporal(TemporalType.DATE)
    private Date dato;
    @Size(max = 140)
    @Column(name = "beskrivelse")
    private String beskrivelse;
    @Column(name = "manglerLys")
    private Boolean manglerLys;
    @Column(name = "manglerProjekter")
    private Boolean manglerProjekter;
    @Column(name = "manglerBorde")
    private Boolean manglerBorde;
    @Column(name = "manglerStole")
    private Boolean manglerStole;

    public Fejl()
    {
    }

    public Fejl(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Fejl(BigDecimal lokale, String beskrivelse) {
        this.lokale = lokale;
        this.beskrivelse = beskrivelse;
    }
    
    public Fejl(BigDecimal lokale, String beskrivelse, Boolean manglerLys) {
        this.lokale = lokale;
        this.beskrivelse = beskrivelse;
        this.manglerLys = manglerLys;
    }
    
    public Fejl(BigDecimal lokale, String prio, Date dato, String beskrivelse, Boolean manglerLys, Boolean manglerProjekter, Boolean manglerBorde, Boolean manglerStole) {
        this.lokale = lokale;
        this.prio = prio;
        this.dato = dato;
        this.beskrivelse = beskrivelse;
        this.manglerLys = manglerLys;
        this.manglerProjekter = manglerProjekter;
        this.manglerBorde = manglerBorde;
        this.manglerStole = manglerStole;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

     public BigDecimal getLokale()
    {
        return lokale;
    }

    public void setLokale(BigDecimal lokale)
    {
        this.lokale = lokale;
    }

    public String getPrio()
    {
        return prio;
    }

    public void setPrio(String prio)
    {
        this.prio = prio;
    }

    public String getDato()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        return dateFormat.format(dato);
    }

    public void setDato(Date dato)
    {
        this.dato = dato;
    }

    public String getBeskrivelse()
    {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse)
    {
        this.beskrivelse = beskrivelse;
    }

    public Boolean getManglerLys()
    {
        return manglerLys;
    }

    public void setManglerLys(Boolean manglerLys)
    {
        this.manglerLys = manglerLys;
    }

    public Boolean getManglerProjekter()
    {
        return manglerProjekter;
    }

    public void setManglerProjekter(Boolean manglerProjekter)
    {
        this.manglerProjekter = manglerProjekter;
    }

    public Boolean getManglerBorde()
    {
        return manglerBorde;
    }

    public void setManglerBorde(Boolean manglerBorde)
    {
        this.manglerBorde = manglerBorde;
    }

    public Boolean getManglerStole()
    {
        return manglerStole;
    }

    public void setManglerStole(Boolean manglerStole)
    {
        this.manglerStole = manglerStole;
    }

     @Override
    public String toString()
    {
        return "entity.Fejl[ id=" + id + " ]";
    }
    
      @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokaler))
        {
            return false;
        }
        Fejl other = (Fejl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }
    
   
}

