/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Roberto Cotrino
 */
@Entity
public class Cell implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    
    @ManyToOne
    private Stack stack;
    
    private double bay;
    private double location;
    private double tier;
    private double m_reeferFore;
    private double m_reeferAft;
    private double m_cap20Aft;
    private double m_cap20Fore;
    private double m_cap40;
    private double m_cap45;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    
    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public double getBay() {
        return bay;
    }

    public void setBay(double bay) {
        this.bay = bay;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public double getTier() {
        return tier;
    }

    public void setTier(double tier) {
        this.tier = tier;
    }

    public double getM_reeferFore() {
        return m_reeferFore;
    }

    public void setM_reeferFore(double m_reeferFore) {
        this.m_reeferFore = m_reeferFore;
    }

    public double getM_reeferAft() {
        return m_reeferAft;
    }

    public void setM_reeferAft(double m_reeferAft) {
        this.m_reeferAft = m_reeferAft;
    }

    public double getM_cap20Aft() {
        return m_cap20Aft;
    }

    public void setM_cap20Aft(double m_cap20Aft) {
        this.m_cap20Aft = m_cap20Aft;
    }

    public double getM_cap20Fore() {
        return m_cap20Fore;
    }

    public void setM_cap20Fore(double m_cap20Fore) {
        this.m_cap20Fore = m_cap20Fore;
    }

    public double getM_cap40() {
        return m_cap40;
    }

    public void setM_cap40(double m_cap40) {
        this.m_cap40 = m_cap40;
    }

    public double getM_cap45() {
        return m_cap45;
    }

    public void setM_cap45(double m_cap45) {
        this.m_cap45 = m_cap45;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vessel_profile.Cell[ id=" + id + " ]";
    }
    
}
