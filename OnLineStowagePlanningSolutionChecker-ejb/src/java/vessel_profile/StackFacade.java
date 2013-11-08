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
public class StackFacade extends AbstractFacade<Stack> implements StackFacadeLocal {

    @PersistenceContext(unitName = "OnLineStowagePlanningSolutionChecker-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public StackFacade() {
        super(Stack.class);
    }
    
   @Override
    public boolean exists_stack(Stack stk){
        return !(em.createQuery("select object(a) from Stack as a where a.stack_number = '" + stk.getStack_number() +  "'" ,Stack.class).getResultList().isEmpty());
    
    }
   
    @Override
   public List<Stack> findStack(double stack_number){
    
            return (List<Stack>)(em.createQuery("select object(a) from Stack as a where a.stack_number = '" +stack_number +  "'" ,Stack.class).getResultList());
    }

/*Versione senza lista che da errore di More than one result
@Override
   public Stack findStackForCell(double stack_number,double bay,double location,double levelFromTier){
    
          return (Stack)(em.createQuery("select object(a) from Stack as a where a.stack_number = '" +stack_number +  "' and a.bay = '" + bay + "' and a.location = '" + location + "' and a.m_level = '" + levelFromTier + "'",Stack.class).getSingleResult());
    }
*/
@Override
public List<Stack> findStackForCell(double stack_number,double bay,double location,double levelFromTier){
    
    return (List<Stack>)(em.createQuery("select object(a) from Stack as a where a.stack_number = '" +stack_number +  "' and a.bay = '" + bay + "' and a.location = '" + location + "' and a.m_level = '" + levelFromTier + "'",Stack.class).getResultList());
}
    
    
    
    
    @Override
   public List<Double> findBays(){
       
        return (List<Double>)(em.createQuery("select distinct s.bay from Stack as s order by s.bay" ,Double.class).getResultList());
    }
    
    @Override
   public List<Stack> findStackBay(double bay_number){
       
        return (List<Stack>)(em.createQuery("select object(a) from Stack as a where a.bay = '" +bay_number +  "'" ,Stack.class).getResultList());
   
   }
   
    @Override
   public List<Stack> findStackBayGroupLevel(double bay_number,double m_level){
       
        return (List<Stack>)(em.createQuery("select object(a) from Stack as a where a.bay = '" +bay_number +  "' and a.m_level = '"  + m_level + "' order by a.stack_number",Stack.class).getResultList());
   
   }
    
    @Override
   public void setPositionStack(double stack_number,int pos){
        em.createQuery("UPDATE Stack s SET s.position_stack = " + pos + " WHERE s.stack_number = " + stack_number).executeUpdate();
   }
    
     @Override
   public List<Stack> findStacks(){
    
            return (List<Stack>)(em.createQuery("select object(a) from Stack as a",Stack.class).getResultList());
    }

 @Override
 public List<Stack> findStackNumberForBay(double bay){
    String strSQL = "SELECT s.ID,s.BAY,s.LOCATION,s.M_HEIGHT_AFT,s.M_HEIGHT_FORE,s.M_LCG_40,s.M_LCG_AFT,s.M_LCG_FORE,s.M_LEVEL,s.M_RUSSIAN,s.M_TCG_FORE,s.M_VCG_AFT,s.M_VCG_FORE,s.M_WEIGHT_40,s.M_WEIGHT_AFT,s.M_WEIGHT_FORE,s.POSITION_STACK,s.STACK_NUMBER FROM STACK s, CELL c WHERE s.ID = c.STACK_ID AND s.BAY = " + bay + " ORDER BY s.STACK_NUMBER";
    return (List<Stack>)em.createNativeQuery(strSQL,Stack.class).getResultList();
 }
 
 @Override
 public List<Double> findBayFromLocation(double locationDouble){
    String strSQL = "SELECT s.BAY FROM STACK s WHERE s.LOCATION = " + locationDouble;
    List<Double> loc = (List<Double>)em.createNativeQuery(strSQL).getResultList();
    if(loc.isEmpty())
        System.out.println("Valore location non presente = " + locationDouble);
    
    return loc;
 }
 
}
