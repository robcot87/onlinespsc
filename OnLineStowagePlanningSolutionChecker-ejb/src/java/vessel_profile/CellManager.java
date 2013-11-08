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
public class CellManager implements CellManagerLocal {

    @EJB
    CellFacadeLocal cellFacade;
    
   @Override
   //public void addCell(double bay,Stack stack,double tier,double location,double m_reeferFore,double m_reeferAft,double m_cap20Aft,double m_cap20Fore,double m_cap40,double m_cap45){
       public void addCell(double bay,List<Stack> stack,double tier,double location,double m_reeferFore,double m_reeferAft,double m_cap20Aft,double m_cap20Fore,double m_cap40,double m_cap45){
       Cell cell = new Cell();
       cell.setBay(bay);
       cell.setTier(tier);
       cell.setLocation(location);
       cell.setM_reeferFore(m_reeferFore);
       cell.setM_reeferAft(m_reeferAft);
       cell.setM_cap20Aft(m_cap20Aft);
       cell.setM_cap20Fore(m_cap20Fore);
       cell.setM_cap40(m_cap40);
       cell.setM_cap45(m_cap45);
       cell.setStack(stack.get(0));
       
           
    //  if(!cellFacade.find(cell).equals(cell))   // TO DO EXISTANCE CONTROL 
       cellFacade.create(cell);
       
   }
   
    @Override
   public List<Cell> findCellsForStack(double stack_number,double bay){
       return cellFacade.findCellsForStack(stack_number,bay);
   
   }
}
