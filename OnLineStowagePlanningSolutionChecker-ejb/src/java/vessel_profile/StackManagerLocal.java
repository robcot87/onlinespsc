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
public interface StackManagerLocal {
    
   public void addStack(double bay,double location,double m_weight_aft, double m_weight_fore, double m_weight_40,double m_height_aft,
                         double m_height_fore,double m_lcg_aft,double m_lcg_fore, double m_lcg_40,double m_vcg_aft,
                         double m_vcg_fore,double m_tcg_aft,double m_tcg_fore,double m_russian,double m_level,
                         double stk_number);
    
   public void addCellsToStack(Stack s,List<Cell> cellsStack);
   
   //public List<Cell> findCells(Stack st);
   
   public List<Stack> findStack(double stack_number);
   
   //public Stack findStackForCell(double stack_number,double bay,double location,double levelFromTier);
   
   public List<Stack> findStackForCell(double stack_number,double bay,double location,double levelFromTier);
   
   public List<Stack> findStackBay(double bay_number);
   
   public List<Stack> findStackBayGroupLevel(double bay_number,double m_level);
   
   public void setPositionStack(double stk_number,int pos);
      
   public List<Double> findBays();
   
   public List<Stack> findStacks();
   
   public void removeStack(Stack s);
   
   public List<Double> findStackNumberForBay(double bay);
   
   public List<Double> findBayFromLocation(double locationDouble);
}