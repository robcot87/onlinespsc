/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rob
 */
@Stateless
public class ContainerManager implements ContainerManagerLocal {

   @EJB
   ContainerFacadeLocal containerFacade;
    
   
   
   //public void addContainer(double bayFromLocation,double stackDouble,double tierDouble,long container_cap,long container_weight,double container_height,boolean reefer,String departure_port,String destination_port, int portDestinationNumberInt){
   @Override
   public void addContainer(double bayFromLocation,double stackDouble,double tierDouble,long container_cap,double container_weight,boolean reefer,int portDestinationNumberInt){
       Container c = new Container();
       c.setContainer_bay(bayFromLocation);
       c.setContainer_stack(stackDouble);
       c.setContainer_tier(tierDouble);
       c.setContainer_cap(container_cap);
       c.setContainer_weight(container_weight);
       c.setReefer(reefer);
       /*
       c.setContainer_height(container_height);
       c.setDeparture_port(departure_port);
       c.setDestination_port(destination_port);
       */ 
       c.setPortNumber(portDestinationNumberInt);
       
       containerFacade.create(c);
       
   }

   @Override
   public List<Integer> extractDestinationPorts(){
   
        return containerFacade.extractDestinationPorts();
   
   }
   
   @Override
   public Integer maxNumDestinationPorts(){
        return containerFacade.maxNumDestinationPorts();
   }
}
