/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Rob
 */
@Entity
public class Container implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    double container_bay;
    double container_stack;
    double container_tier;
    boolean release;
    long container_cap;
    double container_weight;
    
    double container_height;
    String departure_port;
    String destination_port;
    boolean reefer;
    int destinationPortNumber;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getContainer_bay() {
        return container_bay;
    }

    public void setContainer_bay(double container_bay) {
        this.container_bay = container_bay;
    }

    public double getContainer_stack() {
        return container_stack;
    }

    public void setContainer_stack(double container_stack) {
        this.container_stack = container_stack;
    }

    public double getContainer_tier() {
        return container_tier;
    }

    public void setContainer_tier(double container_tier) {
        this.container_tier = container_tier;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public long getContainer_cap() {
        return container_cap;
    }

    public void setContainer_cap(long container_cap) {
        this.container_cap = container_cap;
    }

    public double getContainer_weight() {
        return container_weight;
    }

    public void setContainer_weight(double container_weight) {
        this.container_weight = container_weight;
    }

    public double getContainer_height() {
        return container_height;
    }

    public void setContainer_height(double container_height) {
        this.container_height = container_height;
    }

    public String getDeparture_port() {
        return departure_port;
    }

    public void setDeparture_port(String departure_port) {
        this.departure_port = departure_port;
    }

    public String getDestination_port() {
        return destination_port;
    }

    public void setDestination_port(String destination_port) {
        this.destination_port = destination_port;
    }

    public boolean isReefer() {
        return reefer;
    }

    public void setReefer(boolean reefer) {
        this.reefer = reefer;
    }

    public int getPortNumber() {
        return destinationPortNumber;
    }

    public void setPortNumber(int portNumber) {
        this.destinationPortNumber = portNumber;
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
        if (!(object instanceof Container)) {
            return false;
        }
        Container other = (Container) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vessel_profile.Container[ id=" + id + " ]";
    }
    
}
