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
public class MaxHeap {
    private Struct[] Heap;
    private int size;
    private final int maxsize;
 
    private static final int FRONT = 0;
 
public MaxHeap(int maxsize)
    {
        
        
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Struct[this.maxsize+1];
        Struct struct=new Struct(Integer.MAX_VALUE,null);
        Heap[0]=struct;
        //System.out.println(this.maxsize);
        //System.out.println(this.Heap.length);
    }
    
    private int findTheMaxsize(int maxsize){
        int height=(int)logb(maxsize+1,2);
        //System.out.println(height);
        
        return (int)(Math.pow(2, height+1)-1);
    }
    
    public static double logb( double a, double b )
    {
        return Math.log(a) / Math.log(b);
    }
 
    private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        return pos >=  (size / 2)  &&  pos <= size;
    }
 
    private void swap(int fpos,int spos)
    {
        Struct temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos]=temp;
    }
 
    private void maxHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            if ( Heap[pos].getDist() < Heap[leftChild(pos)].getDist()  || Heap[pos].getDist() < Heap[rightChild(pos)].getDist())
            {
                if (Heap[leftChild(pos)].getDist() > Heap[rightChild(pos)].getDist())
                {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }
 
    public void insert(int element,String str)
    {
        Struct struct=new Struct(element,str);
        Heap[++size]=struct;
        int current = size;
 
        while(Heap[current].getDist() > Heap[parent(current)].getDist())
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    }
 
    public void print()
    {
        for (int i = 1; i <= size; i++ )
        {
            System.out.println("String:"+Heap[i].getStr()+", editdistance:"+Heap[i].getDist());
            
            /*try{
            System.out.print(" PARENT : ("+Heap[i].getStr()+", "+ Heap[i].getDist() +")"
                    +" LEFT CHILD : (" + Heap[2*i].getStr()+", "+Heap[2*i].getDist()+")"
                  + " RIGHT CHILD : (" +Heap[2 * i  + 1].getStr()+ ", "+Heap[2 * i  + 1].getDist()+")");
            System.out.println();
        
            }catch(NullPointerException e){
            e.printStackTrace();
        }*/
            
    }
    }
 
    public void maxHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--)
        {
            maxHeapify(pos);
        }
    }
    public int getSize(){
        return this.size;
    }
    public int topelement(){
        Struct popped=Heap[FRONT];
        return popped.getDist();
    }
    
    public Struct remove()
    {
        Struct popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        if(size>1)
            maxHeapify(FRONT);
        return popped;
    }
    
}
