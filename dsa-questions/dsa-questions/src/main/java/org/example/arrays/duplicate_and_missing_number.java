package org.example.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class duplicate_and_missing_number {
   /*
   this is the lamest approch i could think off
   using a hash array can help
   the time complexity is O(n)
   the space complexity is also O(n)
   the space complexity
    */
    public void lamestapproach(int arr[]){
        int n = arr.length;
        int []hash=new int[n+1];
        for(int i=0;i<n;i++){
            hash[arr[i]]++;
        }

        for(int i=1;i<hash.length;i++){
            if(hash[i]==0){
                System.out.println("this is the missing number"+i);
            }
            if(hash[i]==2){
                System.out.println("this is the duplicate number"+i);
            }
        }

    }
    /*
    this is also lame but will work here the sorting in used so
    the imecoplexity here now is nlog n and O(1)
    snd thsn for thr missing
    two conditioned also required
    one for the first element and one for the last elemet
     */

    public void another_lame_approch(int arr[]){
        int n = arr.length;
        Arrays.sort(arr);
        int duplicate=-1;
        int missing=-1;
        for(int i=0;i<n-1;i++){
            if(arr[i+1]==arr[i]){
                duplicate=arr[i];
            }
            else if(arr[i+1]-arr[i]>1){
                missing=arr[i]+1;
            }
        }

        if(missing==-1 && arr[0]!=1){
            missing=arr[0]-1;

        }
        if(missing==-1){
            missing=n;
        }
        System.out.println("this is the missing number"+missing);
        System.out.println("this is the duplicate number"+duplicate);
    }


    /*
     same as the multiple_duplicates in array
     after the process whichever index has the positive nuber index+1 will be the misssig number
     because arr[n] should cotain arr[n-1]
     "Sign Marking" (Negative Marking) Technique.
     O(n) and O(1)

     */

    public void dupplicate_missing_with_slow_and_fast(int arr[]){
        int missing=-1;
        int duplicates=-1;

        int n=arr.length;
        for(int i=0;i<n;i++){
            int index=Math.abs(arr[i])-1;

            if(arr[index]>0){
                arr[index]=-arr[index];
            }
            else{
                duplicates=index+1;
            }
        }
        for(int i=0;i<n;i++){
            if(arr[i]>0){
                missing=i+1;
            }
        }

        System.out.println("this is the missing number"+missing);
        System.out.println("this is the duplicate number"+duplicates);

    }

    /*
    this is the actual approach which is required to impress the ineterviewers
    first assume the array 1,2,2,3,4,5

    so take the xor of of 1^2^2^3^4^5 and also xor of 1^2^3^4^5^6(array if no miising and duplicate)
    you will get a number here its 4 so its 0100
    *first right differentiating bit is required
    * than we devide into set and non set
    now

     */

    public void bestapproach(int arr[]){
        System.out.println("this is the best approch");
        int n=arr.length;

        int xor=0;
        for(int i=0;i<n;i++){
            xor=xor^arr[i];
            xor=xor^(i+1);
        }
        int bitindex=0;
        while(true){
            if(((1<<bitindex) & xor)>0)
                break;
            bitindex++;
        }

        int ones=0;
        int zeros=0;
        for(int i=0;i<n;i++){
            if((arr[i] & (1<<bitindex))!=0){
                ones^=arr[i];
            }
            else{
                zeros^=arr[i];
            }

        }

        for(int i=1;i<=n;i++){
            if((i & (1<<bitindex))!=0){
                ones^=i;
            }
            else{
                zeros^=i;
            }
        }
        int count1=0;
        for(int i=0;i<n;i++){
            if(arr[i]==ones){
                count1++;
            }
        }
        if(count1==2){
            System.out.println("this is the repeating number"+ones+"and the missing is "+zeros);
        }
        else{
            System.out.println("this is the repeating number"+zeros);
            System.out.println("this is the missing number"+ones);
        }


    }






    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int size;

        System.out.println("enter the size of the array");
        size=sc.nextInt();

        int []arr=new int[size];
        // sc=new Scanner(System.in);

        for(int i=0;i<arr.length;i++){
            arr[i]=sc.nextInt();
        }

        duplicate_and_missing_number dp=new duplicate_and_missing_number();
       // dp.lamestapproach(arr);
        //dp.dupplicate_missing_with_slow_and_fast(arr);
        //dp.another_lame_approch(arr);
        dp.bestapproach(arr);
    }
}
