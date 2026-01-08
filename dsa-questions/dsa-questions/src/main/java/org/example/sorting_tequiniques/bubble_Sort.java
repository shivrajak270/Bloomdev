package org.example.sorting_tequiniques;

import java.util.Arrays;

public class bubble_Sort {


    /*
    this is good sorting technique
    i mean its easy thats all
    a loop runs from  i =n-1 to greater than 0
    and than a loop inside runs from 0 to that i

    arr=1,54,23,76,13,54,89
   index=0,1, 2, 3, 4, 5, 6

   the adjacent is comparred and if the left is greaterit is swaped to right
   after the first itraatio
   compare j and j+1 if j is greater than swap
   1,23,54,13,54,76,89
   --unsorted------- sorted

   second loop
   1,23,13,54,76,89
  ----unsorted-sorted--



bubble sort is betterr than selection because oce the array is sorted the bubble sort will exit if no swaps are dione but for the selection
will have the problem

     */
    private int[] bubbleSort(int[] arr) {
        int n=arr.length;
        for(int i=n-1;i>0;i--){
            boolean swap=false;
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    swap=true;
                }
            }
            if(!swap){
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int arr[]={1,54,23,76,13,54,89};
        bubble_Sort bubbleSort=new bubble_Sort();
       System.out.println(Arrays.toString(bubbleSort.bubbleSort(arr)));

    }
}
