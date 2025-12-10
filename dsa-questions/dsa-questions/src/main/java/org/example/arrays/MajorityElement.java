package org.example.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

/*
map this will also have the o(n) but than its the space
coplexity but will do it beacuse we hate maps
 */
public int bymaps(int []nums){
    Map<Integer,Integer> map=new HashMap<>();
    for(int i=0;i<nums.length;i++){
        int ans=map.getOrDefault(nums[i],1);
        map.put(nums[i],map.getOrDefault(nums[i],1)+1);
    }
    for(Map.Entry<Integer,Integer> entry:map.entrySet()){
        if(entry.getValue()>nums.length/2){
            return entry.getKey();
        }
    }
   return -1;
}



    /*

    o(N)
    i rememeber this one i don't know how
    the sequence should be same
    first check if the count=0
    than if the element in the array matches the num
    than if not
     */
    public int majorityElement(int[] nums) {
        int n=nums.length;
        int count=0;
        int num=nums[0];
        for(int i=0;i<n;i++){
            if(count==0){
                num=nums[i];
                count++;
            }
            else if(num==nums[i]){
                count++;
            }
            else{
                count--;
            }
        }
        int reverifycount=0;
        for(int j=0;j<n;j++){
            if(num==nums[j]){
                reverifycount++;

            }
        }
        if(reverifycount>n/2){
            return num;
        }
        return -1;
    }
    public static void main(String args[]){
        int arr[]={1, 1, 2, 1, 3, 5, 1};
        MajorityElement obj=new MajorityElement();
        System.out.println(obj.majorityElement(arr));
        System.out.println(obj.bymaps(arr));





    }
}
