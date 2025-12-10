package org.example.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
1. Two Sum
Solved
Easy
Topics
premium lock icon
Companies
Hint
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

 */
public class two_sum_problem {


    /*
    the lamest way here o(N2) is the time complexity so skip but tell
    in the interview
     */

    public int[] twoSum(int[] nums, int target) {
        int arr[]=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    arr[0]=i;
                    arr[1]=j;
                    break;
                }

            }
        }
        return arr;
    }

    /*
    this approach is only valid if you want to say that
    there is a possibility of 2 elemets
    in case you just have to say that pair present a
    or print that pair
    if index matters to you this won't work

    working is simple the greedy based algorithm like
    sort and than left and right pointer
    at both the ends and than start comparing

   O(nlogn)+O(n)=O(nlogn)
     */
    public int[] twoSumonlyforsorted(int[] nums, int target) {
        int arr[]=new int[2];
        int left=0;
        int right=nums.length-1;
        while(right<nums.length && left<right){
            int sum=nums[left]+nums[right];
            if(target>sum){
                left++;
            }
            else if(target <sum){
                right--;
            }
            else{
                arr[0]=left;
                arr[1]=right;
                break;
            }
        }
        return arr;
    }

    /*
    this is the best approch if you don't want to loose the index
    and if the index doesn' matter than this will be the second approach
     */

    public int[] twoSumbyhashmaptoprintindex(int[] nums, int target) {
        int arr[]=new int[2];

        Map<Integer,Integer> map=new HashMap<>();
        map.put(arr[0],0);
        for(int i=1;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                arr[0]=i;
                arr[1]=map.get(target-nums[i]);
                break;
            }
            else{
                map.put(nums[i],i);
            }

        }
        return arr;
    }



public static void main(String [] args){

        int nums[]={2,11,15,7};
        int target = 9;

    two_sum_problem obj=new two_sum_problem();
    int[] arr=obj.twoSum(nums,target);
    System.out.println(Arrays.toString(arr));





}



}
