
package TopKIndex;

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
public class topkindex {
public void invertedindex()
{
    
  HashMap<String,ArrayList<Struct>> invertedIndex=new HashMap<>();
  ArrayList<Struct> postinglist=new ArrayList<>();
  Struct first=new Struct(1,"1");
  Struct second=new Struct(1,"4");
  postinglist.add(first);
  postinglist.add(second);
  invertedIndex.put("jac", postinglist);
  
  ArrayList<Struct> postinglist1=new ArrayList<>();
  Struct first1=new Struct(2,"1");
  Struct second1=new Struct(2,"4");
  Struct third1=new Struct(2,"6");
  postinglist1.add(first1);
  postinglist1.add(second1);
  postinglist1.add(third1);
  invertedIndex.put("ack", postinglist1);
  
  ArrayList<Struct> postinglist2=new ArrayList<>();
  Struct first2=new Struct(3,"1");
  Struct second2=new Struct(3,"4");
  Struct third2=new Struct(3,"6");
  postinglist2.add(first2);
  postinglist2.add(second2);
  postinglist2.add(third2);
  invertedIndex.put("cks", postinglist2);
  
  ArrayList<Struct> postinglist3=new ArrayList<>();
  Struct first3=new Struct(4,"1");
  Struct second3=new Struct(4,"4");
  Struct third3=new Struct(3,"5");
  Struct fourth3=new Struct(4,"6");
  postinglist3.add(first3);
  postinglist3.add(second3);
  postinglist3.add(third3);
  postinglist3.add(fourth3);
  invertedIndex.put("kso", postinglist3);
  
  ArrayList<Struct> postinglist4=new ArrayList<>();
  Struct first4=new Struct(5,"1");
  Struct second4=new Struct(3,"3");
  Struct third4=new Struct(5,"4");
  Struct fourth4=new Struct(4,"5");
  Struct fifth4=new Struct(5,"6");
  postinglist4.add(first4);
  postinglist4.add(second4);
  postinglist4.add(third4);
  postinglist4.add(fourth4);
  postinglist4.add(fifth4);
  invertedIndex.put("son", postinglist4);
  
  ArrayList<Struct> postinglist5=new ArrayList<>();
  Struct first5=new Struct(6,"1");
  Struct second5=new Struct(4,"3");
  Struct fourth5=new Struct(5,"5");
  Struct fifth5=new Struct(6,"6");
  postinglist5.add(first5);
  postinglist5.add(second5);
  postinglist5.add(fourth5);
  postinglist5.add(fifth5);
  invertedIndex.put("on ", postinglist5);
  
  ArrayList<Struct> postinglist6=new ArrayList<>();
  Struct first6=new Struct(7,"1");
  Struct second6=new Struct(5,"3");
  Struct third6=new Struct(6,"5");
  Struct fourth6=new Struct(7,"6");
 postinglist.add(first6);
  postinglist6.add(second6);
  postinglist6.add(third6);
  postinglist6.add(fourth6);
  invertedIndex.put("n p ", postinglist6);
  
  ArrayList<Struct> postinglist7=new ArrayList<>();
  Struct first7=new Struct(8,"1");
  Struct second7=new Struct(6,"2");
  Struct third7=new Struct(6,"3");
  Struct fourth7=new Struct(7,"5");
  Struct fifth7=new Struct(8,"6");
  postinglist7.add(first7);
  postinglist7.add(second7);
  postinglist7.add(third7);
  postinglist7.add(fourth7);
  postinglist7.add(fifth7);
  invertedIndex.put(" po", postinglist7);
  
  ArrayList<Struct> postinglist8=new ArrayList<>();
  Struct first8=new Struct(9,"1");
  Struct second8=new Struct(7,"2");
  Struct third8=new Struct(7,"3");
  Struct fourth8=new Struct(8,"5");
  Struct fifth8=new Struct(9,"6");
  postinglist8.add(first8);
  postinglist8.add(second8);
  postinglist8.add(third8);
  postinglist8.add(fourth8);
  postinglist8.add(fifth8);
  invertedIndex.put("pol", postinglist8);
  
  ArrayList<Struct> postinglist9=new ArrayList<>();
  Struct first9=new Struct(10,"1");
  Struct second9=new Struct(8,"2");
  Struct third9=new Struct(9,"5");
  postinglist9.add(first9);
  postinglist9.add(second9);
  postinglist9.add(third9);
  invertedIndex.put("oll", postinglist9);
  
  ArrayList<Struct> postinglist10=new ArrayList<>();
  Struct first10=new Struct(8,"3");
  Struct second10=new Struct(10,"6");
  postinglist10.add(first10);
  postinglist10.add(second10);
  invertedIndex.put("olo", postinglist10);
  
  ArrayList<Struct> postinglist11=new ArrayList<>();
  Struct first11=new Struct(11,"1");
  postinglist11.add(first11);
  invertedIndex.put("llo",postinglist11);
  
  ArrayList<Struct> postinglist12=new ArrayList<>();
  Struct first12=new Struct(12,"1");
  Struct second12=new Struct(10,"3");
  Struct third12=new Struct(11,"6");
  postinglist12.add(first12);
  postinglist12.add(second12);
  postinglist12.add(third12);
  invertedIndex.put("loc", postinglist12);
  
  ArrayList<Struct> postinglist13=new ArrayList<>();
  Struct first13=new Struct(13,"1");
  Struct second13=new Struct(11,"3");
  Struct third13=new Struct(12,"6");
  postinglist13.add(first13);
  postinglist13.add(second13);
  postinglist13.add(third13);
  invertedIndex.put("ock", postinglist13);
  
  ArrayList<Struct> postinglist14=new ArrayList<>();
  Struct first14=new Struct(1,"2");
  Struct second14=new Struct(1,"5");
  postinglist14.add(first14);
  postinglist14.add(second14);
  invertedIndex.put("jak", postinglist14);
  
  ArrayList<Struct> postinglist15=new ArrayList<>();
  Struct first15=new Struct(2,"2");
  postinglist15.add(first15);
  invertedIndex.put("ako", postinglist15);
  
  ArrayList<Struct> postinglist16=new ArrayList<>();
  Struct first16=new Struct(3,"2");
  postinglist16.add(first16);
  invertedIndex.put("kob", postinglist16);
  
  
  ArrayList<Struct> postinglist17=new ArrayList<>();
  Struct first17=new Struct(4,"2");
  postinglist17.add(first17);
  invertedIndex.put("ob ", postinglist17);
  
  ArrayList<Struct> postinglist18=new ArrayList<>();
  Struct first18=new Struct(5,"2");
  postinglist18.add(first18);
  invertedIndex.put("b p", postinglist18);
  
  ArrayList<Struct> postinglist19=new ArrayList<>();
  Struct first19=new Struct(9,"2");
  Struct second19=new Struct(10,"5");
  postinglist19.add(first19);
  postinglist19.add(second19);
  invertedIndex.put("lla", postinglist19);
  
  ArrayList<Struct> postinglist20=new ArrayList<>();
  Struct first20=new Struct(10,"2");
  Struct second20=new Struct(11,"5");
  postinglist20.add(first20);
  postinglist20.add(second20);
  invertedIndex.put("lac", postinglist20);
  
  ArrayList<Struct> postinglist21=new ArrayList<>();
  Struct first21=new Struct(1,"6");
  postinglist21.add(first21);
  invertedIndex.put("mac", postinglist21);
  
  ArrayList<Struct> postinglist22=new ArrayList<>();
  Struct first22=new Struct(11,"2");
  Struct second22=new Struct(12,"5");
  postinglist22.add(first20);
  postinglist22.add(second20);
  invertedIndex.put("ack", postinglist22);
  
  ArrayList<Struct> postinglist23=new ArrayList<>();
  Struct first23=new Struct(1,"3");
  postinglist23.add(first23);
  invertedIndex.put("jas", postinglist23);
  
  ArrayList<Struct> postinglist24=new ArrayList<>();
  Struct first24=new Struct(2,"3");
  postinglist24.add(first24);
  invertedIndex.put("aso", postinglist24);
  
  ArrayList<Struct> postinglist25=new ArrayList<>();
  Struct first25=new Struct(6,"4");
  postinglist25.add(first25);
  invertedIndex.put("omv", postinglist25);
  
  ArrayList<Struct> postinglist26=new ArrayList<>();
  Struct first26=new Struct(7,"4");
  postinglist26.add(first26);
  invertedIndex.put("mvi", postinglist26);
  
  ArrayList<Struct> postinglist27=new ArrayList<>();
  Struct first27=new Struct(8,"4");
  postinglist27.add(first27);
  invertedIndex.put("vil", postinglist27);
  
  ArrayList<Struct> postinglist28=new ArrayList<>();
  Struct first28=new Struct(9,"4");
  postinglist28.add(first28);
  invertedIndex.put("ill", postinglist28);
  
  ArrayList<Struct> postinglist29=new ArrayList<>();
  Struct first29=new Struct(10,"4");
  postinglist29.add(first29);
  invertedIndex.put("lle", postinglist29);
  
  ArrayList<Struct> postinglist30=new ArrayList<>();
  Struct first30=new Struct(2,"5");
  postinglist30.add(first30);
  invertedIndex.put("aks", postinglist30);
  
  ArrayList<Struct> postinglist31=new ArrayList<>();
  Struct first31=new Struct(3,"5");
  postinglist31.add(first31);
  invertedIndex.put("kso", postinglist31);
  
  ArrayList<Struct> postinglist32=new ArrayList<>();
  Struct first32=new Struct(6,"4");
  postinglist32.add(first32);
  invertedIndex.put("som", postinglist32);

  for (String name: invertedIndex.keySet()) {
  	String key=name.toString();
  	ArrayList <Struct> value=invertedIndex.get(name);
  	System.out.println(key);
  	for(Struct e: value){
  		System.out.print("(s"+e.getStr()+", "+e.getDist()+") ");
  	}
        System.out.println();
  }
  	
  }

public static void main(String[] args){
    topkindex t=new topkindex();
    t.invertedindex();
}
  
}