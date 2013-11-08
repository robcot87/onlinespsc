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
public class CellFacade extends AbstractFacade<Cell> implements CellFacadeLocal {
    @PersistenceContext(unitName = "OnLineStowagePlanningSolutionChecker-ejbPU")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public CellFacade() {
        super(Cell.class);
    }
    
    /*@Override
    public List<Cell> findCellsForStack(Stack st){
        System.out.println("Dentro findCellsForStack valore di st.id = " + st.getId().toString());
         //return (List<Cell>)(em.createQuery("select object(c) from Cell as c where c.stack_id = '" + st.getId() +  "'",Cell.class).getResultList());
         
          return (List<Cell>)(em.createQuery("SELECT c FROM Cell c, Stack s WHERE c.stack = s.id and c.stack = '" + st.getId() + "'", Cell.class).getResultList());  
    }
    */
 /*@Override       
 public List<Cell> findCellsForStack(double stack_number,double bay,double location,double level){
    //System.out.println("Dentro findCellsForStack in cellFacade");
    String strSQL = "SELECT c.ID,c.BAY,c.LOCATION,c.M_CAP20AFT,c.M_CAP20FORE,c.M_CAP40,c.M_CAP45,c.M_REEFERAFT,c.M_REEFERFORE,c.TIER  "
                + "FROM STACK s, CELL c "
                +"WHERE s.ID = c.STACK_ID AND s.BAY = " + bay + " AND s.LOCATION = " + location + " AND s.STACK_NUMBER = " + stack_number + " AND s.M_LEVEL = " + level;
    return (List<Cell>)em.createNativeQuery(strSQL,Cell.class).getResultList();
    
  }   
  */

 @Override       
 public List<Cell> findCellsForStack(double stack_number,double bay){
    String strSQL = "SELECT c.ID,c.BAY,c.LOCATION,c.M_CAP20AFT,c.M_CAP20FORE,c.M_CAP40,c.M_CAP45,c.M_REEFERAFT,c.M_REEFERFORE,c.TIER  "
                + "FROM STACK s, CELL c "
                +"WHERE s.ID = c.STACK_ID AND s.ID IN(SELECT s.ID FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = " + bay + " AND s.STACK_NUMBER = " + stack_number + ")";
    return (List<Cell>)em.createNativeQuery(strSQL,Cell.class).getResultList();
    
    
    }  
    /* * 
    * // Per un particolare Stack
    * SELECT s.ID FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = 1.0 AND s.STACK_NUMBER= 5.0 GROUP BY s.ID
    * 
    * 
    * SELECT s.STACK_NUMBER,s.ID FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = 1.0 AND s.STACK_NUMBER=5.0 GROUP BY s.STACK_NUMBER,s.ID
 
 SELECT c.ID,c.BAY,c.LOCATION,c.M_CAP20AFT,c.M_CAP20FORE,c.M_CAP40,c.M_CAP45,c.M_REEFERAFT,c.M_REEFERFORE,c.TIER
FROM STACK s, CELL c WHERE c.STACK_ID IN (SELECT s.ID FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = 1.0 AND GROUP BY s.ID)
 
 * 
 * 
 * 
 * SELECT s.STACK_NUMBER,s.ID FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = 1.0 GROUP BY s.STACK_NUMBER,s.ID
 */ 
 
 
 
    
}
