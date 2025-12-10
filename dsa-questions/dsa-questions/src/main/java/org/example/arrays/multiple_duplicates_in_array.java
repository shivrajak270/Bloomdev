package org.example.arrays;

import java.util.*;

/*
Date:1 december 2025
finding the duplicates in the array

 */

public class multiple_duplicates_in_array {

    public void printarray(int arr[]){

        int n = arr.length;
        for(int i=0;i<n;i++){
           System.out.print(arr[i]+" ");
        }

    }

    public void printarraylist(ArrayList<Integer> arr){
        int n = arr.size();
        for(int i=0;i<n;i++){
            System.out.print(arr.get(i)+" ");
        }

    }

    //two loops are used and than
    //two loops are used so O(n2)
    public void lamer_than_the_present(int arr[]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i]==arr[j]){
                    System.out.print("the duplicates are"+arr[i]);
                }
            }
        }
    }

  //some sort of lame approach where we sort and than check
    //this approch takes o(n log n) without extra space
    public void lameapproach(int arr[]){
        Arrays.sort(arr);

        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]==arr[i]){
                System.out.print("the duplicates are "+arr[i+1]);
            }
        }
        printarray(arr);

    }

    //this approch can only be used if all the numbers in an array are posituve
    //for negative it won't work
    /*
    so what we do is we use the array itself as a hash array

    O(n)time and O(1)space

    example 4 3 2 7 8 2 3 1
    index   0 1 2 3 4 5 6 7
    first loop
    i=0 index=3 arr[3]=-arr[3] == 7 =-7
    example 4 3 2 -7 8 2 3 1
    index   0 1 2 3 4 5 6 7

    i=1

    nums[i] = 3
    index = 3 - 1 = 2
    arr[2] = -arr[2]  ==  2 → -2

    example 4 3 -2 -7 8 2 3 1
    index   0 1 2 3 4 5 6 7

    i=2
    nums[i] = -2 → abs = 2
index = 2 - 1 = 1
arr[1] = -arr[1]  ==  3 → -3

example 4 -3 -2 -7 8 2 3 1
index   0 1 2 3 4 5 6 7
i=3
nums[i] = -7 → abs = 7
index = 7 - 1 = 6
arr[6] = -arr[6]  ==  3 → -3

example 4 -3 -2 -7 8 2 -3 1
index   0 1 2 3 4 5 6 7


i=4
nums[i] = 8
index = 8 - 1 = 7
arr[7] = -arr[7]  == 1 → -1

example 4 -3 -2 -7 8 2 -3 -1
index   0 1 2 3 4 5 6 7

i=5
nums[i] = 2
index = 2 - 1 = 1
arr[1] = -3 → already negative
→ duplicate = 2

example 4 -3 -2 -7 8 2 -3 -1
index   0 1 2 3 4 5 6 7

i=6
nums[i] = -3 → abs = 3
index = 3 - 1 = 2
arr[2] = -2 → already negative
→ duplicate = 3

example 4 -3 -2 -7 8 2 -3 -1
index   0 1 2 3 4 5 6 7

i=7
nums[i] = -1 → abs = 1
index = 1 - 1 = 0
arr[0] = -arr[0]  == 4 → -4

example -4 -3 -2 -7 8 2 -3 -1
index     0  1  2  3 4 5 6  7








     */



    public void positiveduplicates(int arr[]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            int index=Math.abs(arr[i])-1;
            if(arr[index]<0){
                System.out.print(index+1+"is the duplicate ");
            }
            else{
                arr[index]=-arr[index];
            }
        }
    }




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n=5;
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        multiple_duplicates_in_array obj=new multiple_duplicates_in_array();
       // obj.lamer_than_the_present(arr);
        //obj.lameapproach(arr);
        obj.positiveduplicates(arr);



    }

}
