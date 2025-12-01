package org.example.arrays;

import java.util.Scanner;

/*
Date:1 december 2025
finding the duplicates in the array

 */

public class duplicates_in_array {

    public void printarray(int arr[]){

        int n = arr.length;
        for(int i=0;i<n;i++){
           System.out.print(arr[i]+" ");
        }

    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n=5;
        int []arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        duplicates_in_array obj=new duplicates_in_array();
        obj.printarray(arr);
    }

}
