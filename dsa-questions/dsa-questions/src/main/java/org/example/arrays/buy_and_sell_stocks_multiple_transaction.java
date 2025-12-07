package org.example.arrays;

public class buy_and_sell_stocks_multiple_transaction {


    /*
    i know the code which i have written worrks
    but  is the worst code you will ever see
    it is also O(n)
   but i  have cooded it by my own so no problem
     */

    public int maximumProfit(int prices[]) {


        int buy=Integer.MAX_VALUE;
        int profit=0;


        int l=0;
        int r=1;
        while(r<prices.length){

            if(prices[r-1]>prices[l] && prices[r]<prices[r-1]){
                profit+=prices[r-1]-prices[l];
                l=r;
            }
            else if(prices[r]<prices[l]){
                l=r;
            }
            r++;

        }
        if(r==prices.length){
            profit+=prices[r-1]-prices[l];
        }
        return profit;


    }
  /*
  adding all positive differences is equivalent to buying at the bottom and selling at the top.

   */
    public int another_approch(int arr[]){
        int n=arr.length;

        int ans=0;


        for(int i=1;i<n;i++){
            if(arr[i-1]<arr[i])
                ans+=arr[i]-arr[i-1];
        }

        return ans;

    }

    public static void main(String[] args) {

        buy_and_sell_stocks_multiple_transaction obj=new buy_and_sell_stocks_multiple_transaction();
        int ans=obj.maximumProfit(new int[]{7,1,5,3,6,4});

        int ans2=obj.another_approch(new int[]{7,1,5,3,6,4});
        System.out.println(ans2);
        System.out.println(ans);


    }
}
