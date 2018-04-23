/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TopKIndex;

/**
 *
 * @author apurvakatti
 */
public class QGram {
    private Struct[] qgram;
    private int size;
    private int maxsize;
    public QGram(int maxsize){
        this.size=0;
        this.maxsize=maxsize;
        this.qgram=new Struct[maxsize];
    }

    public Struct[] getQgram() {
        return qgram;
    }

    public void setQgram(Struct[] qgram) {
        this.qgram = qgram;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void insert(String tobeinserted,int position){
        qgram[this.size]=new Struct(position,tobeinserted);
        this.size=this.size+1;
    }
    
    public void constructqgram(String input){
        for(int i=0;i<input.length()-2;i++){
            this.insert(input.substring(i, i+3),i+1);
        }
    }
    
    
    
    
    
}
