/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rob
 */
@Local
public interface ContainerManagerLocal {
    
//    public void addContainer(long container_cap,long container_weight,double container_height,boolean reefer,String departure_port,String destination_port);
    //public void addContainer(double bayFromLocation,double stackDouble,double tierDouble,long container_cap,long container_weight,double container_height,boolean reefer,String departure_port,String destination_port, int portDestinationNumberInt);
    public void addContainer(double bayFromLocation,double stackDouble,double tierDouble,long container_cap,double container_weight,boolean reefer,int portDestinationNumberInt);

    public List<Integer> extractDestinationPorts();

    public Integer maxNumDestinationPorts();
}
