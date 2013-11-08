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
public interface CellManagerLocal {
    
    //public void addCell(double bay,Stack stack,double tier,double location,double m_reeferFore,double m_reeferAft,double m_cap20Aft,double m_cap20Fore,double m_cap40,double m_cap45);
    
    public void addCell(double bay,List<Stack> stack,double tier,double location,double m_reeferFore,double m_reeferAft,double m_cap20Aft,double m_cap20Fore,double m_cap40,double m_cap45);
    //public List<Cell> findCellsForStack(double stack_number,double bay,double location);
    //public List<Cell> findCellsForStack(Stack st);
    //public List<Cell> findCellsForStack(Stack st,double level);
    public List<Cell> findCellsForStack(double stack_number,double bay);
}
