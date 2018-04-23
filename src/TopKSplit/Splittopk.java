/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TopKSplit;

/**
 *
 * @author apurvakatti
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apurvakatti
 */
public class Splittopk {
    public static String query;
    public static int editdistanceCount=0;
    public static void main(String[] args){
        Splittopk splitopk=new Splittopk();
        MaxHeap maxheap; 
        int k=2;
        
        
        
        maxheap= new MaxHeap(k);
        int editdistance;
        int count=0;
        Splittopk.query="jacksonv".toLowerCase();
        String string;
        Boolean cond=false;
        String part="";
        int t=(int)Math.floor(Splittopk.query.length()/3.0);
        
        QGram qgramQuery=new QGram(query.length()-2);
        qgramQuery.constructqgram(query);
        QGram bestG=null;
        

        
        String[] strings=new String[]{"Jakson Pollack","Mackson Polock","Jakob Pollack","Jackson Pollock","Jacksomville","Jason Polock","Mason Polock"};
           
        //HashMap<String,ArrayList<Struct>> invertedIndex=new HashMap<>();
        
        
        for(int i=0;i<k;i++){
            string=strings[i];
            if(string.length()<3)
                continue;
            int value=splitopk.editDistance(query,string);
            maxheap.insert(value, string);
        }

        for(int i=k;i<strings.length;i++){
            string=strings[i];

            if(string.length()<3)
                continue;
            
            QGram qgramString=new QGram(string.length()-2);
            qgramString.constructqgram(string.toLowerCase());
            QGram[] newqgramString = splitopk.compareqgrams(qgramQuery,qgramString);

            if(newqgramString[0].getSize()<1)
                continue;
            
            if(cond==true){
                part="-";
                for(int z=0;z<newqgramString[1].getSize();z++){
                    if(bestG !=null){
                        for(Struct b: bestG.getQgram()){
                            if(newqgramString[1].getQgram()[z].getStr().equals(b.getStr())){
                                part="+";
                                break;
                            }
                            
                        }
                        if(part=="+")
                            break;
                    }
                }
            }
            
            
            if(true){
            //if(cond==false || part=="+"){
                int lowerbound=splitopk.lowerbound(newqgramString);
                if(maxheap.topelement()>lowerbound){
                    editdistance=splitopk.editDistance(query,string);
                    if(maxheap.topelement()>editdistance){
                        maxheap.maxHeap();
                        maxheap.remove();
                        maxheap.insert(editdistance,string);
                    }
                    if(maxheap.topelement()<=t){
                        cond=true;
                        t=maxheap.topelement();
                        bestG=splitopk.bestG(qgramQuery, t);
                        
                    }

                }
                if(cond==true && maxheap.topelement()<=t){
                    t=maxheap.topelement();
                    bestG=splitopk.bestG(qgramQuery, t);
                }
            }
                
    
    }    
        maxheap.print(); 
        System.out.println("No of lower bound calculation "+editdistanceCount);
        
        
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
        editdistanceCount++;
        int queryLength=Splittopk.query.length();
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


    public ArrayList<QGram> nonoverlappingqgram(String input){
        ArrayList<QGram> nonOverlappingQgramArray=new ArrayList<>();
        QGram qgram;
            for(int j=0;j<input.length()-2;j++){
                qgram=new QGram(input.length());
                qgram.constructNonOverlappingqgram(input, j);
                nonOverlappingQgramArray.add(qgram);
            }
            return nonOverlappingQgramArray;

        }

    
    public QGram bestG(QGram qgram,int p){
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> qGramL=new ArrayList<>();
        ArrayList<Struct> query=new ArrayList();
        
        for(int i=0;i<qgram.getSize();i++){
            query.add(qgram.getQgram()[i]);
        }
        
        map.put("jac", 100);
        map.put("ack", 32);
        map.put("cks", 16);
        map.put("kso", 10);
        map.put("son", 120);
        map.put("onv", 40);
        
        int minI=-1;
        int minJ=-1;
        int min=Integer.MAX_VALUE;
        int temp;
        
        for(int i=0;i<query.size()-1;i++){
            for(int j=i;j<query.size();j++){
                if(query.get(i).getDist()+3<query.get(j).getDist()){
                    temp=map.get(query.get(i).getStr())+map.get(query.get(j).getStr());
                    if(temp<min){
                        min=temp;
                        minI=i;
                        minJ=j;
                    }
                    break;
                }
            }
        }
        
        QGram returnQGram=new QGram(2);
        returnQGram.insert(query.get(minI).getStr(), query.get(minI).getDist());
        returnQGram.insert(query.get(minJ).getStr(), query.get(minJ).getDist());
        return returnQGram;

        }
}