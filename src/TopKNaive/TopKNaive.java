
package TopKNaive;

/**
 *
 * @author apurvakatti
 */
import java.io.File;
import java.util.Arrays;
import java.lang.Math;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopKNaive {
    public static int editDistanceCount=0;
    
    
    public int editDistance(String a,String b){
        editDistanceCount++;
        a = a.toLowerCase();
        b = b.toLowerCase();
        int[] costs = new int[b.length() + 1];
        for (int i = 1; i <= a.length(); i++)
        {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++)
            {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        Arrays.sort(costs);
        return costs[0];
    }
       
public static void main(String[] args)
    {
        
        int k=10;
        String query="earth";
        String url = "jdbc:mysql://localhost:3306/abc?verifyServerCertificate=false&useSSL=true";
        String user = "root";
        String password = "Bangalore@007";
        TopKNaive top=new TopKNaive();
        MaxHeap maxheap; 
        int kChanged=0;
        
        if(k%2!=0 && k!=1){
            k=k+1;
            kChanged+=1;
        }
        else if(k==2){
            k=k+2;
            kChanged+=2;
        }
        
        maxheap= new MaxHeap(k);
        
        
        
        
        String title = "SELECT title FROM paper";


        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(title);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int value=top.editDistance(query,rs.getString(1));
                //System.out.println(value);
                if(maxheap.getSize()<k){
                    maxheap.insert(value,rs.getString(1));
                    }
                else if(maxheap.getSize()==k){
                    if(value<maxheap.topelement()){
                        maxheap.maxHeap();
                        maxheap.remove();
                        maxheap.insert(value,rs.getString(1));
                        //System.out.println(value);
                        }
                    }   
                }
            while(kChanged>0){
                maxheap.maxHeap();
                maxheap.remove();
                kChanged--;
            }
            maxheap.print();  
            System.out.println("No of edit distance calculation "+editDistanceCount);
        
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(TopKNaive.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
