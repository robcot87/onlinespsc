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
public interface ContainerFacadeLocal {
    
    void create(Container container);

    void edit(Container container);

    void remove(Container container);

    Container find(Object id);

    List<Container> findAll();

    List<Container> findRange(int[] range);

    int count();
    
    public List<Integer> extractDestinationPorts();
    
    public Integer maxNumDestinationPorts();
}
