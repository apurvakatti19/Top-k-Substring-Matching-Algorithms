
package TopKLB;

/**
 *
 * @author apurvakatti
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apurvakatti
 */
public class TopKLBNew {
    public static String query;
    public static int editdistanceCount=0;
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/abc?verifyServerCertificate=false&useSSL=true";
        String user = "root";
        String password = "Bangalore@007";
        TopKLBNew topklb=new TopKLBNew();
        MaxHeap maxheap; 
        int k=15;
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
        int editdistance;
        int count=0;
        TopKLBNew.query="Tejas".toLowerCase();
        String string;
        //Boolean kChanged=false;
        
        QGram qgramQuery=new QGram(query.length()-2);
        qgramQuery.constructqgram(query);
        
        String title = "SELECT title FROM paper";

        
        //String[] strings=new String[]{"Jakson Pollack","Jackson Pollock","Jakob Pollack","Jason Polock","Jacksomville","Jack Willson"};
        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(title);
                ResultSet rs = pst.executeQuery()) {
            
            while (rs.next() && count<k) {
        
        //for(int i=0;i<k;i++){
                string=rs.getString(1);
                if(string.length()<3)
                    continue;
                int value=topklb.editDistance(query,string);
                maxheap.insert(value, string);
                count++;
        }

        //for(int i=k;i<strings.length;i++){
            while(rs.next()){
                string=rs.getString(1);
                
                if(string.length()<3)
                    continue;
                
                QGram qgramString=new QGram(string.length()-2);
                qgramString.constructqgram(string.toLowerCase());
                QGram[] newqgramString=topklb.compareqgrams(qgramQuery,qgramString);
            
            /*for(int n=0;n<newqgramString[0].getSize();n++){
                System.out.println(newqgramString[0].getQgram()[n].getStr()+" "+newqgramString[0].getQgram()[n].getDist());
                
            }
            
            for(int w=0;w<newqgramString[1].getSize();w++){
                System.out.println(newqgramString[1].getQgram()[w].getStr()+" "+newqgramString[1].getQgram()[w].getDist());
                
            }*/
            
            
            if(newqgramString[0].getSize()<1)
                continue;
            int lowerbound=topklb.lowerbound(newqgramString);
            if(maxheap.topelement()>lowerbound){
                editdistance=topklb.editDistance(query,string);
                if(maxheap.topelement()>editdistance){
                    maxheap.maxHeap();
                    maxheap.remove();
                    maxheap.insert(editdistance,string);
                }
                
            }
                
    
    }
        while(kChanged>0){
            maxheap.maxHeap();
            maxheap.remove();
            kChanged--;
            
        }     
        maxheap.print(); 
        System.out.println("No of edit distance calculation "+editdistanceCount);
    }catch (SQLException ex) {

            Logger lgr = Logger.getLogger(TopKLBNew.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    public QGram[] compareqgrams(QGram qgramQuery,QGram qgramString){
        
        QGram qg=new QGram(qgramQuery.getSize());
        QGram qs=new QGram(qgramString.getSize());
        
        Map<Integer,String> stringDict=new HashMap<>();
        TreeMap<Integer,String> stringTree = new TreeMap<>();
        
        Boolean found;
        
        Struct[] query=qgramQuery.getQgram();
        Struct[] string=qgramString.getQgram();
        
        ArrayList<Struct> stringTemp=new ArrayList<>(Arrays.asList(string));
        
        for (Struct query1 : query) {
            for (Struct string1 : stringTemp){
                if (string1 !=null && query1.getStr().equals(string1.getStr())){
                    qg.insert(query1.getStr(), query1.getDist());
                    stringTree.put(string1.getDist(), string1.getStr());
                    stringTemp.remove(string1);
                    break;
                }   
            }
        }
        
        stringTree.entrySet().forEach((entry) -> {
            qs.insert(entry.getValue(),entry.getKey());
        }); 

        
        QGram[] combined=new QGram[2];
        combined[0]=qg;
        combined[1]=qs;
        return combined;
    }
    
    public int lowerbound(QGram[] newqgramString){
        
        ArrayList<Struct> query=new ArrayList();
        ArrayList<Struct> stringTemp=new ArrayList();
        ArrayList<Struct> stringFinal=new ArrayList();
        
        Struct[] q=newqgramString[0].getQgram();
        Struct[] s=newqgramString[1].getQgram();
        
        int queryLength=newqgramString[0].getSize();
        int stringLength=newqgramString[1].getSize();
        
        int stringTempIndex=-1;
        
        for(int i=0;i<queryLength;i++){
            query.add(q[i]);
        }
        
        for(int i=0;i<stringLength;i++){
            stringTemp.add(s[i]);
            stringFinal.add(s[i]);
        }
        
        ArrayList<Struct[]> visitedQgram= new ArrayList<>();
        
        int m[][]=new int[queryLength][stringLength];
        int j,u=-1,v=-1,secondPart,firstPart;
        
        for(int i=0;i<queryLength;i++){
            Arrays.fill(m[i], -1);
        }
        
        
        for(int i=0;i<query.size();i++){
            j=-1;
            for (Struct e:stringTemp){
                if(stringTemp.size()>0)
                    if(e.getStr().equalsIgnoreCase(query.get(i).getStr())){
                        j=(int)stringFinal.indexOf(e);
                        stringTempIndex=(int)stringTemp.indexOf(e);
                        break;
                }
            }
            if(j != -1){
                if(visitedQgram.isEmpty()){
                    m[i][j]=(int)Math.ceil((query.get(i).getDist()-1)/3.0);
                }
                else{
                    firstPart=(int)Math.ceil((query.get(i).getDist()-1)/3.0);
                    secondPart=secondPart(visitedQgram,i,j,query,stringFinal,m);
                    m[i][j]=Math.min(firstPart, secondPart);
                    }
                
                Struct tempStruct[]=new Struct[2];
                tempStruct[0]=query.get(i);
                tempStruct[1]=stringFinal.get(j);
                visitedQgram.add(tempStruct);
                stringTemp.remove(stringTempIndex);
                }
            }
        
        int finalresult=finallowerbound(m,query,stringFinal);
        //System.out.println(finalresult);
        return finalresult;
    }
    
    
    public int secondPart(ArrayList<Struct[]> visitedQgram,int i,int j,ArrayList<Struct> query,ArrayList<Struct> string,int m[][]){
        int min=Integer.MAX_VALUE;
        int u,v,temp;
        boolean minCalculated=false;
        for (int k=0;k<visitedQgram.size();k++){
            if((visitedQgram.get(k)[0].getDist()<query.get(i).getDist()) && 
                    (visitedQgram.get(k)[1].getDist()<string.get(j).getDist())){
                minCalculated=true;
                
                u=query.indexOf(visitedQgram.get(k)[0]);
                v=string.indexOf(visitedQgram.get(k)[1]);
                temp=(int)(m[u][v]+(Math.max(Math.ceil((query.get(i).getDist()-query.get(u).getDist()-1)/3.0),
                                        Math.abs(query.get(i).getDist()-query.get(u).getDist()-
                                                (string.get(j).getDist()-string.get(v).getDist())))));
                if(temp<min) 
                    min=temp;
            }
        }
        return min;
    }

    
    public int finallowerbound(int[][]m,ArrayList<Struct> query,ArrayList<Struct> string){
        int queryLength=TopKLBNew.query.length();
        int firstResult=(int)Math.ceil((queryLength-3+1)/3.0);
        int min=Integer.MAX_VALUE;
        int temp;
        
        for(int i=0;i<query.size();i++){
            for(int j=0;j<string.size();j++){
                if(m[i][j] !=-1){
                    temp=m[i][j]+(int)Math.ceil((queryLength-query.get(i).getDist()-3+1)/3.0);
                    if(temp<min)
                        min=temp;
                    //System.out.println(min);
                }
            }
        }
        return Math.min(firstResult,min);
    }
    
    public int editDistance(String a,String b){
        editdistanceCount++;
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
}
