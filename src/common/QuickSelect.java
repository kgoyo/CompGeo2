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

    private static int partition(List<Double> nums, int start, int end) {
        int pivot = start;
        double temp;
        while (start <= end) {
            while (start <= end && nums.get(start) <= nums.get(pivot)) start++;
            while (start <= end && nums.get(end) > nums.get(pivot)) end--;
            if (start > end) break;
            temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
        }
        temp = nums.get(end);
        nums.set(end, nums.get(pivot));
        nums.set(pivot, temp);
        return end;
    }
}

