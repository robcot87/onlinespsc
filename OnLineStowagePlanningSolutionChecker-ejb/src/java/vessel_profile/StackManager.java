/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vessel_profile;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Rob
 */
@Stateless
public class StackManager implements StackManagerLocal {

 @EJB
 private StackFacadeLocal stackFacade;
 
 @Override
 public void addStack(double bay,double location,double m_weight_aft, double m_weight_fore, double m_weight_40,double m_height_aft,
                         double m_height_fore,double m_lcg_aft,double m_lcg_fore, double m_lcg_40,double m_vcg_aft,
                         double m_vcg_fore,double m_tcg_aft,double m_tcg_fore,double m_russian,double m_level,
                         double stk_number){
            Stack stk = new Stack();
            stk.setM_weight_aft(m_weight_aft);
            stk.setM_weight_fore(m_weight_fore);
            stk.setM_weight_40(m_weight_40);
            stk.setM_height_aft(m_height_aft);
            stk.setM_height_fore(m_height_fore);
            stk.setM_lcg_aft(m_lcg_aft);
            stk.setM_lcg_fore(m_lcg_fore);
            stk.setM_lcg_40(m_lcg_40);
            stk.setM_vcg_fore(m_vcg_fore);
            stk.setM_tcg_aft(m_tcg_aft);
            stk.setM_tcg_fore(m_tcg_fore);
            stk.setStack_number(stk_number);
            
            stk.setBay(bay);
            stk.setLocation(location);
            stk.setM_level(m_level);
            stk.setM_russian(m_russian);
            
            //if(!stackFacade.exists_stack(stk))
                stackFacade.create(stk);
    }
    
 public void addCellsToStack(Stack s,List<Cell> cellsStack){
 
    //s.setM_cells(cellsStack);
    
    stackFacade.edit(s);
 
 }
 
/*
 @Override
 public List<Cell> findCells(Stack st){
      return st.getM_cells();
 }
 */
 
  @Override
  public List<Stack> findStack(double stack_number){
      
      return stackFacade.findStack(stack_number);
      
  }
  
  /*Versione senza lista che da errore di More than one result
  @Override
  public Stack findStackForCell(double stack_number,double bay,double location,double levelFromTier){
        return stackFacade.findStackForCell(stack_number,bay,location,levelFromTier);
  }
  */
  
  @Override
  public List<Stack> findStackForCell(double stack_number,double bay,double location,double levelFromTier){
        return stackFacade.findStackForCell(stack_number,bay,location,levelFromTier);
  }
  
  
 @Override
  public List<Stack> findStackBay(double bay_number){
      
      return stackFacade.findStackBay(bay_number);
            
  }
  
 @Override
   public List<Stack> findStackBayGroupLevel(double bay_number,double m_level){
      
      return stackFacade.findStackBayGroupLevel(bay_number,m_level);
            
  }
 
 @Override
  public void setPositionStack(double stk_number,int pos){
        System.out.println("Dentro set pos stack di manager");
        stackFacade.setPositionStack(stk_number,pos);
   
   }
 
  
 @Override
  public List<Double> findBays(){
        return stackFacade.findBays();
  }
  
 @Override
  public List<Stack> findStacks(){
        return stackFacade.findStacks();
  }
  
  @Override
  public void removeStack(Stack s){
        stackFacade.remove(s);
  }
  
  @Override
  public List<Double> findStackNumberForBay(double bay){
      List<Double> stackNumbersForBay = new LinkedList<Double>();
      List<Stack> stks =  stackFacade.findStackNumberForBay(bay);
      
      for(Stack s:stks){
          if(!stackNumbersForBay.contains(s.getStack_number())){
              stackNumbersForBay.add(s.getStack_number());
              //System.out.println("Add stack number " + s.getStack_number());
          }
       }
      return stackNumbersForBay;
      
      
  }
  
  
  @Override
  public List<Double> findBayFromLocation(double locationDouble){
  
      return stackFacade.findBayFromLocation(locationDouble);
  }
  
}
