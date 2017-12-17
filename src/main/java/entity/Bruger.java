/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author walkm
 */
@Entity
public class Bruger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String userName;


    @ElementCollection
    @CollectionTable(
            name = "BRUGER_ROLLER",
            joinColumns = @JoinColumn(name = "BRUGER_ID")
    )
    @Column(name = "ROLLER")
    List<String> roles = new ArrayList();
    
    public Bruger()
    {

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    public List<String> getRoles()
    {
        return roles;
    }



    public Bruger(String userName)
    {
        this.userName = userName;

    }

    public Bruger(String userName, List<String> roles)
    {
        this.userName = userName;
        this.roles = roles;
    }

    public void addRole(String role)
    {
        roles.add(role);
    }
    
}
