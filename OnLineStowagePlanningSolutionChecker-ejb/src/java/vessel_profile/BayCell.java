package vessel_profile;

/**
 *
 * @author Rob
 */
public class BayCell{
    double bay;
    Integer x;
    Integer y;

    public BayCell(double bay,Integer x, Integer y) {
        this.bay = bay;
        this.x = x;
        this.y = y;
    }

    public double getBay() {
        return bay;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
    
    public boolean equals(BayCell bc){
        if(this.getBay()==bc.getBay() && this.getX()==bc.getX() && this.getY()==bc.getY()){
            return true;
        }
        return false;
    }
}
