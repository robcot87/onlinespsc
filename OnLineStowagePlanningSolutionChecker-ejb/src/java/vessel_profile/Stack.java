/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.io.Serializable;
import java.util.LinkedList;
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
public class Stack implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    double stack_number;

    double bay;
    double location;
    
    double m_weight_aft;
    double m_weight_fore;
    double m_weight_40;
    double m_height_aft;
    double m_height_fore;
    double m_lcg_aft;
    double m_lcg_fore;
    double m_lcg_40;
    double m_vcg_aft;
    double m_vcg_fore;
    double m_tcg_aft;
    double m_tcg_fore;
 
    double m_russian;
    
    double m_level; //1=on, 2=below deck
    
    int position_stack;
    
    /*
    @OneToMany(mappedBy = "stack")
    private List<Cell> m_cells;

    */
     private List<Cell> m_cells_copy;

    public List<Cell> getM_cells_copy() {
        return m_cells_copy;
    }

    public void setM_cells_copy(List<Cell> m_cells_copy) {
        this.m_cells_copy = m_cells_copy;
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
    
    
    public double getStack_number() {
        return stack_number;
    }

    public void setStack_number(double stack_number) {
        this.stack_number = stack_number;
    }

    public double getM_weight_aft() {
        return m_weight_aft;
    }

    public void setM_weight_aft(double m_weight_aft) {
        this.m_weight_aft = m_weight_aft;
    }

    public double getM_weight_fore() {
        return m_weight_fore;
    }

    public void setM_weight_fore(double m_weight_fore) {
        this.m_weight_fore = m_weight_fore;
    }

    public double getM_weight_40() {
        return m_weight_40;
    }

    public void setM_weight_40(double m_weight_40) {
        this.m_weight_40 = m_weight_40;
    }

    public double getM_height_aft() {
        return m_height_aft;
    }

    public void setM_height_aft(double m_height_aft) {
        this.m_height_aft = m_height_aft;
    }

    public double getM_height_fore() {
        return m_height_fore;
    }

    public void setM_height_fore(double m_height_fore) {
        this.m_height_fore = m_height_fore;
    }

    public double getM_lcg_aft() {
        return m_lcg_aft;
    }

    public void setM_lcg_aft(double m_lcg_aft) {
        this.m_lcg_aft = m_lcg_aft;
    }

    public double getM_lcg_fore() {
        return m_lcg_fore;
    }

    public void setM_lcg_fore(double m_lcg_fore) {
        this.m_lcg_fore = m_lcg_fore;
    }

    public double getM_lcg_40() {
        return m_lcg_40;
    }

    public void setM_lcg_40(double m_lcg_40) {
        this.m_lcg_40 = m_lcg_40;
    }

    public double getM_vcg_aft() {
        return m_vcg_aft;
    }

    public void setM_vcg_aft(double m_vcg_aft) {
        this.m_vcg_aft = m_vcg_aft;
    }

    public double getM_tcg_aft() {
        return m_tcg_aft;
    }

    public void setM_tcg_aft(double m_tcg_aft) {
        this.m_tcg_aft = m_tcg_aft;
    }

    public double getM_tcg_fore() {
        return m_tcg_fore;
    }

    public void setM_tcg_fore(double m_tcg_fore) {
        this.m_tcg_fore = m_tcg_fore;
    }

    public double getM_russian() {
        return m_russian;
    }

    public void setM_russian(double m_russian) {
        this.m_russian = m_russian;
    }

    public double getM_level() {
        return m_level;
    }

    public void setM_level(double m_level) {
        this.m_level = m_level;
    }

    /*
    @OneToMany(mappedBy="stack")
    public List<Cell> getM_cells() {
        return m_cells;
    }

    public void setM_cells(List<Cell> m_cells) {
        this.m_cells = m_cells;
    }
*/
    public double getM_vcg_fore() {
        return m_vcg_fore;
    }

    public void setM_vcg_fore(double m_vcg_fore) {
        this.m_vcg_fore = m_vcg_fore;
    }

    public int getPosition_stack() {
        return position_stack;
    }

    public void setPosition_stack(int position_stack) {
        this.position_stack = position_stack;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Stack)) {
            return false;
        }
        Stack other = (Stack) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vessel_profile.Stack[ id=" + id + " ]";
    }
    
}
