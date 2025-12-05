package org.example.arrays;

public class find_maximum_consecutive_number_of_ones_or_zeros {


    /*
    this is the aproch whwere i take 2 variables zeros and ones
    if zeros is there than increase the count for zeros and keep the count for ones at 0
    if the ones is there than increase the count of one and make the count of zeros as 0
    in  each loop get the max;
     */

    public void lamebutbest(int arr[]){
        int zeros=0;
        int ones=0;
        int ans=Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                zeros++;
                ones=0;
            }
            else{
                ones++;
                zeros=0;
            }
            ans=Math.max(ans,Math.max(ones,zeros));
        }
        System.out.println(ans);
    }

    /*
    the left and the right pointer sliding window type
    right at 1 and left at 0 if right == left value than move right else left=right
     */

    public void left_and_right_pointer(int arr[]){
        int left=0;
        int right=1;
        int ans=Integer.MIN_VALUE;
        while(right<arr.length){
            if(arr[right]==arr[left]){

                ans=Math.max(right-left+1,ans);
                right++;
            }
            else{
                left=right;
            }
        }
        System.out.println(ans);
    }




    public static void main(String[] args) {
        int []arr={1,1,1,1,0,1,1,0,0,0,0,0,0,0,1};

        find_maximum_consecutive_number_of_ones_or_zeros gh=new find_maximum_consecutive_number_of_ones_or_zeros();
        gh.lamebutbest(arr);
        gh.left_and_right_pointer(arr);



    }
}
