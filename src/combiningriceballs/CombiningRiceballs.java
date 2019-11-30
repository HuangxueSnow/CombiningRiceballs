/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combiningriceballs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class CombiningRiceballs {

    /**
     * @param args the command line arguments
     */
    static Scanner in = new Scanner(System.in);
    
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static boolean isnotEqua = true;
    public static void main(String[] args) {
        // TODO code application logic here
        readIn();
        ArrayList<Integer> alist = list;
        ArrayList<Integer> a = combineRice(alist);
        int max = 0;
        for(int i=0;i<a.size();i++){
            if(a.get(i)>max)
                max = a.get(i);
        }
        System.out.print(max);
        
        maxRiceBall(alist,0,alist.size()-1);
    }

    private static void readIn() {
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            list.add(a);
        }
    }
    
    static int maxRiceBall(ArrayList<Integer> list, int start,int end) 
    {
        if (start==end) return list.get(start);
        else if (start==end-1) return list.get(start)*2;
        else if (start==end-2) return list.get(start)*2+list.get(start+1);
        
        if (start==end) return list.get(start);
        if (!combinable(list,start,end)) {
            int w1=maxRiceBall(list,start,end-1);
            int w2=maxRiceBall(list,start+1,end);
            return Math.max(w2, w1);
        }
        // for combined by 2
        for (int i=start+1;i<=end-1;i++) {
            if (!combinable(list,start,i) || !combinable(list,i+1,end)) continue;
            if (maxRiceBall(list,start,i) != maxRiceBall(list,i+1,end)) continue;
            return maxRiceBall(list,start,i)*2;
        }
        
        // for combined by 3
        for (int i=start+1;i<=end-2;i++) {
            for (int j=i+1;j<=end-1;j++) {
                if (!combinable(list,start,i) || !combinable(list,i+1,j) || !combinable(list,j+1,end))
                    continue;
                if (maxRiceBall(list,start,i)!=maxRiceBall(list,j+1,end))
                    continue;
                return maxRiceBall(list,start,i)*2+maxRiceBall(list,i+1,j);
            }
        }
        //throw new Exception("Should not be here");
        return 0;
    }

    private static ArrayList<Integer> combineRice(ArrayList<Integer> list1) {
        if(!isnotEqua)
            return list1;
        isnotEqua = false;
        ArrayList<Integer> newl = new ArrayList<Integer>();
        for(int i=0;i<list1.size();i++){
            int a = list1.get(i);
            if(i<list1.size()-1 && list1.get(i+1)==a){
                a += list1.get(i+1);
                i++;
                isnotEqua = true;
            }
            newl.add(a);
        }
        list1 = newl;
        newl = new ArrayList<Integer>();
        for(int i=0;i<list1.size();i++){
            int a = list1.get(i);
            if(i<list1.size()-2 && list1.get(i+2)==a){
                a = a+list1.get(i+1)+list1.get(i+2);
                i+=2;
                isnotEqua = true;
            }
            newl.add(a);
        }
        return newl = combineRice(newl);
    }

    private static boolean combinable(ArrayList<Integer> list, int start, int end) {
        if (start==end) return true;
        else if (start==end-1) return list.get(start)==list.get(end);
        else if (start==end-2) return list.get(start)==list.get(end);
        
         // for combined by 2
        for (int i=start+1;i<=end-1;i++) {
            if (!combinable(list,start,i) || !combinable(list,i+1,end)) continue;
            if (maxRiceBall(list,start,i) != maxRiceBall(list,i+1,end)) continue;
            return true;
        }
        
        // for combined by 3
        for (int i=start+1;i<=end-2;i++) {
            for (int j=i+1;j<=end-1;j++) {
                if (!combinable(list,start,i) || !combinable(list,i+1,j) || !combinable(list,j+1,end))
                    continue;
                if (maxRiceBall(list,start,i)!=maxRiceBall(list,j+1,end))
                    continue;
                return true;
            }
        }
        return false;
    }


    
}
