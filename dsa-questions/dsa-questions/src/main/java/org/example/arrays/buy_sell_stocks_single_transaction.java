package org.example.arrays;

public class buy_sell_stocks_single_transaction {



   //O(N2) time which is the worst time complexity i could ever use
    public void lammest_thought(int arr[]){
        int ans=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                ans=Math.max(ans,Math.abs(arr[j]-arr[i]));
            }
        }
        System.out.println(ans);
    }

    //O(N) the interviewer will feel just okay
    public void i_could_think(int arr[]){
        int min=Integer.MAX_VALUE;
        int ans=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<min){
                min=arr[i];
            }
            ans=Math.max(ans,arr[i]-min);

        }
        System.out.println("the thinkable"+ans);
    }

    //Two Pointer Approach Left and right
    //right will be at 1 index and left at 0
    //the right will have the loop
    //if left is right is less than left than left=right
    //if right is greater than  left than take the differnece and compare
    public void two_poiter_approach(int arr[]){
        int n=arr.length;
        int ans=Integer.MIN_VALUE;
        int left=0;
        int right=1;
        while(right <n){
            if(arr[left]<arr[right]){
                ans=Math.max(ans,arr[right]-arr[left]);
            }
            else {
                left=right;
            }
            right++;
        }
        System.out.println(ans);

    }

    /*
    this is a prefix sum wala solution like will use 2 array to
    store the minimum till end and than another array from last to first
    this itself you can call as dp
     */
    public void i_never_thpught_of_it(int arr[]){
        int n=arr.length;
        int []prefix=new int[n];
        prefix[0]=arr[0];

        for(int i=1;i<n;i++){
                prefix[i]=Math.min(arr[i],prefix[i-1]);

        }

        int []suffix=new int[n];
        suffix[n-1]=arr[n-1];

        for(int i=n-2;i>=0;i--){
                suffix[i]=Math.max(suffix[i+1],arr[i]);

        }
        int ans=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            ans=Math.max(ans,suffix[i]-prefix[i]);
        }
        System.out.println(ans);
    }





    public static void main(String[] args) {

        int arr[]={8,2,1,4,5,6,7,9,20};

        buy_sell_stocks_single_transaction gh =new buy_sell_stocks_single_transaction();
        //gh.lammest_thought(arr);
       // gh.i_could_think(arr);
       // gh.two_poiter_approach(arr);
        gh.i_never_thpught_of_it(arr);





    }
}
