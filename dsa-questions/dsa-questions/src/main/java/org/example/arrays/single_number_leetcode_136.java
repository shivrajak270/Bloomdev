package org.example.arrays;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.



Example 1:

Input: nums = [2,2,1]

Output: 1

Example 2:

Input: nums = [4,1,2,1,2]

Output: 4

Example 3:

Input: nums = [1]

Output: 1



Constraints:

1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
 */
public class single_number_leetcode_136 {

   /*
   this is optimal and interview friendly o(N) ans o(1)
    */
    public int bitwise_solution(int nums[]){
        int ans=nums[0];

        for(int i=1;i<nums.length;i++){
            ans ^=nums[i];
        }
        return ans;
    }

  /*
  this is okay solution this can be your 2 nd solution
  here the o(N) both time and  space
  learn the  working of hashmap here
   */
    public int using_map(int arr[]){
        int ans=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        for(int key:map.keySet()){
            if(map.get(key)==1){
                ans=key;
                break;
            }
        }
        return ans;
    }

    /*
    the lamest approch is always needed like the first approach
     */

    public int sort_and_search(int arr[]){
        int ans=0;
        Arrays.sort(arr);
        for(int i=0;i<arr.length-1;i+=2){
            if(arr[i]!=arr[i+1]){
                ans= arr[i];
                break;
            }
        }
        ans=arr[arr.length-1];
        return ans;
    }

    public static void main(String[] args) {

        int [] arr={1,1,2,2,4,4,6};

        single_number_leetcode_136  leetcode = new single_number_leetcode_136();
        System.out.println(leetcode.bitwise_solution(arr));
        System.out.println(leetcode.using_map(arr));
        System.out.println(leetcode.sort_and_search(arr));



    }
}
