package org.example.arrays;

import java.util.HashMap;
import java.util.Map;

public class pos_andNegative_maxmum_length_subarray {
    /*
    Given an array arr[] containing integers and an integer k, your task is to find the length of the longest subarray where the sum of its elements is equal to the given value k. If there is no subarray with sum equal to k, return 0.
    Input: arr[] = [10, 5, 2, 7, 1, -10], k = 15
Output: 6
Explanation: Subarrays with sum = 15 are [5, 2, 7, 1], [10, 5] and [10, 5, 2, 7, 1, -10]. The length of the longest subarray with a sum of 15 is 6.
https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1

     */

    /*
    this is the first approch the not say in interview one
    this takes o(n3) and remeber the j=i and the cndition to check will be atafter the third loop
     */

    public int threeloops(int arr[],int l){
        int n=arr.length;
        int ans=-1;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum=0;
                for(int k=i;k<=j;k++){
                    sum+=arr[k];
                    if(l==sum){
                        ans=Math.max(ans,j-i+1);
                    }

                }
            }
        }
        return ans;
    }

    /*
    the second solution is o(n2)
    this can be actually the brute force
     */
    public int twoloops(int arr[],int l){
        int n=arr.length;
        int ans=0;
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(l==sum){
                    ans=Math.max(ans,j-i+1);
                }
                }
            }
        return ans;
    }
    /*
    the third one is actually hashmap and the prefix sum
    👉 If somewhere earlier there was a prefix sum = prefix - k, then the subarray from after that point to r has sum k. this one by diagram
    1,2,3,1,6,1,7
    1,3,6,7,13,14,21

    see if k=3
    see the prefix sum if the sum till 2nd index is 6
    and k-index value that is 6-3 is present in the
    previous sums than we have the sum=k in i+1 and till that point
    so i+1 is 2nd index there till 2nd index itself so
    single digit.

    remember two things why we put the 0,-1 in map
    and the both conditions will be if and not else if
     */

    public int bymapandprefixsum(int arr[],int k){
        int n=arr.length;
        Map<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int ans=0;
        map.put(0,-1);
        for(int i=0;i<n;i++){
            sum+=arr[i];

            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
            if(map.containsKey(sum-k)){
                ans=Math.max(ans,i-map.get(sum-k));
            }

        }
   return ans;


    }

    /*
    another apprach but it works when all are positive we have to use the sliding window


     */
    public int slidingforpositivesonly(int arr[],int k){
        int n=arr.length;
        int left=0;
        int right=0;
        int sum=0;
        int ans=0;
        while(right<n){
            sum+=arr[right];

            while(sum>k){
                sum-=arr[left];
                left++;
            }

            if(sum==k){
                ans=Math.max(ans,right-left+1);
            }
            right++;
        }
        return ans;
    }


    public static void main(String[] args){

        int arr[]={10, 5, 2, 7, 1, -10};
        int k=15;

        pos_andNegative_maxmum_length_subarray obj=new pos_andNegative_maxmum_length_subarray();
        System.out.println(obj.threeloops(arr,k));
        System.out.println(obj.twoloops(arr,k));
        System.out.println(obj.bymapandprefixsum(arr,k));
        int arr2[]={0,0,0,1,1,1,1,0,0,0,0,0,1,3};
        System.out.println(obj.slidingforpositivesonly(arr2,3));





    }
}
