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
public interface CellFacadeLocal {
    
    void create(Cell cell);

    void edit(Cell cell);

    void remove(Cell cell);

    Cell find(Object id);

    List<Cell> findAll();

    List<Cell> findRange(int[] range);

    int count();
    
    //public List<Cell> findCellsForStack(double stack_number,double bay,double location);
    //public List<Cell> findCellsForStack(Stack stack_number);
    //public List<Cell> findCellsForStack(double stack_number,double bay,double location,double level);
    public List<Cell> findCellsForStack(double stack_number,double bay);
}
