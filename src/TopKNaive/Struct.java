
package TopKNaive;

/**
 *
 * @author apurvakatti
 */
public class Struct
{
    private int dist;
    private String str;
    
    public Struct(int dist,String str)
    {
        this.dist=dist;
        this.str=str;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }    
    
}
