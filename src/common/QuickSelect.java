package common;

import java.util.List;

public class QuickSelect {
    public static int findMedian(List<Double> a,int left,int right){
        int index = 0;
        int mid = (int) Math.floor((left+right)/2);
        index = partition(a,left,right);
        while( index != mid){
            if(index < mid)
                index = partition(a,mid,right);
            else index = partition(a,left,mid);
        }
        return index;
    }
    public static int partition(List<Double> a, int i, int j ){
        int pivot = (i+j)/2;
        double temp;
        while(i <= j){
            while (a.get(i).compareTo(a.get(pivot)) == 1)
                i++;
            while (a.get(j).compareTo(a.get(pivot)) == -1)
                j--;
            if(i <= j){
                temp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, temp);
                i++;j--;
            }
        }
        return pivot;
    }
}

