/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rezalt
 */
@Entity
@Table(name = "lokaler", catalog = "SkoleAppDB", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Lokaler.findAll", query = "SELECT l FROM Lokaler l"),
    @NamedQuery(name = "Lokaler.findById", query = "SELECT l FROM Lokaler l WHERE l.id = :id"),
    @NamedQuery(name = "Lokaler.findByLokale", query = "SELECT l FROM Lokaler l WHERE l.lokale = :lokale"),
    @NamedQuery(name = "Lokaler.findByPladser", query = "SELECT l FROM Lokaler l WHERE l.pladser = :pladser"),
})
public class Lokaler implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lokale")
    private BigDecimal lokale;
    @Column(name = "pladser")
    private Integer pladser;
    

    public Lokaler()
    {
    }

    public Lokaler(Integer id)
    {
        this.id = id;
    }

    public Lokaler(Integer id, BigDecimal lokale)
    {
        this.id = id;
        this.lokale = lokale;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public Integer getPladser()
    {
        return pladser;
    }

    public void setPladser(Integer pladser)
    {
        this.pladser = pladser;
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
        Lokaler other = (Lokaler) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString()
    {
        return "entity.Lokaler[ id=" + id + " ]";
    }
    
}
