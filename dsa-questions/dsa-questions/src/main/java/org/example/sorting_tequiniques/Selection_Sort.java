package org.example.sorting_tequiniques;

import java.util.Arrays;

public class Selection_Sort {


    /*
    selection sort

    In every pass, select the smallest element from the unsorted part and place it at the correct position.
    Loops Structure (VERY IMPORTANT)

Outer loop → runs from i = 0 to n - 2

Inner loop → runs from j = i + 1 to n - 1

min → stores index of minimum element
    first loop till the i=n-2
    second loop from i+1 to n-1
    then a min variable to sort the minimum
    now by travesal ypu will understand

    arr = 9,12,54,30,20,78,43,5
    i=0 j= i+1
    min =i;
    i=0;
    9 =arr[i] now j from j=i+1 till end
    if(arr[j]<arr[min])
      min=j;
    than after the for loop wil find the smallest that will swap with the i
    so at the end will have min=7 will swap the i and min
    i++ will happen so from 0 to i its sorted


    after the first loop

     arr = 5,12,54,30,20,78,43,9
     index=0, 1, 2, 3, 4, 5, 6, 7
              i
         sorted unsooooooooooorted
    i=1 j= i+1
    min =i;
    now i+i to n so min will be 7
    than swap and it goes on
     */

    public int[]selection_Sort(int arr[]){

        int n=arr.length;
        for(int i=0;i<n-1;i++){
            int min=i;
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            int temp=arr[min];
            arr[min]=arr[i];
            arr[i]=temp;
        }
        return arr;
    }


    public static void main(String[] args) {
        Selection_Sort ss=new Selection_Sort();
        int arr[]={9,12,54,30,20,78,43,5};
        int ans[]=ss.selection_Sort(arr);
        System.out.println(Arrays.toString(ans));
    }
}
