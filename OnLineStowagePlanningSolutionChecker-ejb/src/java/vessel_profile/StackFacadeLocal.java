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
public interface StackFacadeLocal {
    
    void create(Stack stk);

    void edit(Stack stk);

    void remove(Stack stk);

    Stack find(Object id);

    List<Stack> findAll();

    List<Stack> findRange(int[] range);

    int count();
    
    public boolean exists_stack(Stack loc);
    
    public List<Stack> findStack(double stack_number);
    
    //Versione che da errore di More than one result
    //public Stack findStackForCell(double stack_number,double bay,double location,double levelFromTier);
    
    public List<Stack> findStackForCell(double stack_number,double bay,double location,double levelFromTier);
            
    public List<Stack> findStackBay(double bay_number);
    
    public List<Stack> findStackBayGroupLevel(double bay_number,double m_level);
    
    public void setPositionStack(double stack_number,int pos);
    
    public List<Double> findBays();
    
     public List<Stack> findStacks();
     
     //public List<Double> findStackNumberForBay(double bay);
     
     public List<Stack> findStackNumberForBay(double bay);
     
      public List<Double> findBayFromLocation(double locationDouble);
}
