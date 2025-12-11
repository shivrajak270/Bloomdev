package org.example.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Longest_Consecutive_Sequence {

    /*
    okay the bruteforec approach which came in my mind is the
    puttung everything in the treeset this will remove the duplicates a
    and order them in ascending order.

    the insertion takes logn and n number of elemensts so nlogn is the time complexity
    the space complexity here is o(N)
     */
    public int byTreeSet(int arr[]){
        TreeSet<Integer> set=new TreeSet<>();
        int ans=0;
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int prev=0;
        int count=1;
        for(int key:set){
            prev=key;
            if(set.contains(prev+1)){
                count++;
            }
            else{
                count=1;
            }
            ans=Math.max(ans,count);

        }
        return ans;
    }
    /*
    the secod approach is simple will use the less space complexity maybe the time complexity remain same
    this is o(nlogn)
    sort
    than check if the given is +1 the prev
    if same than skip
    else reset the prev

    if(nums.length<1){
            return 0;
        } this add if edge case is not covered
     */

    public int justsortingf(int arr[]){
        Arrays.sort(arr);
        int n=arr.length;
        int prev=arr[0];

        int ans=0;
        int count=1;
        for(int i=1;i<n;i++){
            if(arr[i]==prev+1){
                count++;
                prev=arr[i];
            }
            else if(arr[i]==prev){
                continue;
            }
            else{
                prev=arr[i];
                count=1;
            }
            ans=Math.max(ans,count);
        }
        return ans;
    }
    /*
    this seems to be the best approach with o(n) but the space increases to o(n)
    simple steps
    put everything in the set
    than see the smallest element
    if found the smallet elememt than see is one more than this is presnt if yes ++ the number and the count
    add the edeg cases if possible
     */
    public int byhashset(int arr[]){

            Set<Integer> set=new HashSet<>();
            for(int i=0;i<arr.length;i++){
                set.add(arr[i]);
            }
            int ans=1;
            int count=1;
            int num=0;
            for(int key:set){
                if(!set.contains(key-1)){
                    num=key;
                    while(set.contains(num+1)){
                        count++;
                        num++;
                    }
                    ans=Math.max(ans,count);
                }
            }
            return ans;

    }


    public static void main(String []args){
        int []arr={100,4,200,1,3,2};
        Longest_Consecutive_Sequence gh=new Longest_Consecutive_Sequence();
        System.out.println(gh.byTreeSet(arr));
        System.out.println(gh.justsortingf(arr));
        System.out.println(gh.byhashset(arr));



    }
}
