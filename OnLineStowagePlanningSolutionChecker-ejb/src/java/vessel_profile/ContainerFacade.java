/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rob
 */
@Stateless
public class ContainerFacade extends AbstractFacade<Container> implements ContainerFacadeLocal {

   @PersistenceContext(unitName = "OnLineStowagePlanningSolutionChecker-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public ContainerFacade() {
        super(Container.class);
    }
    
   @Override
   public List<Integer> extractDestinationPorts(){
        String strSQL = "SELECT DISTINCT DESTINATIONPORTNUMBER  "
                    + "FROM CONTAINER ";
                    
        return (List<Integer>)em.createNativeQuery(strSQL).getResultList();

    }

   
   public Integer maxNumDestinationPorts(){
        String strSQL = "SELECT MAX(DESTINATIONPORTNUMBER)  "
                    + "FROM CONTAINER ";
                    
        return (Integer)em.createNativeQuery(strSQL).getSingleResult();
   }
   
   
}
