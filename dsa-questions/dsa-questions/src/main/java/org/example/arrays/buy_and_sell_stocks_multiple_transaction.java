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

    public static void main(String[] args) {

        buy_and_sell_stocks_multiple_transaction obj=new buy_and_sell_stocks_multiple_transaction();
        int ans=obj.maximumProfit(new int[]{7,1,5,3,6,4});
        System.out.println(ans);


    }
}
