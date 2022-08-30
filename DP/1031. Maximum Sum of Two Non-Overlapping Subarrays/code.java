class Solution {
    
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[n + 2][4];
        
        int cur_0 = 0;
        int cur_1 = 0;
        int cur_2 = 0;
        int cur_3 = 0;
        for(int i = 1; i <= n; i++){
            cur_0 += nums[i - 1];
            cur_1 += nums[i - 1];
            if(i >= firstLen){
                
                dp[i][0] = i - 1 < 0 ? 0 : Math.max(dp[i - 1][0], cur_0);
                cur_0 -= nums[(i - firstLen)];
            }
            if(i >= secondLen){
                dp[i][1] = i - 1 < 0 ? 0 : Math.max(dp[i - 1][1], cur_1);
                cur_1 -= nums[(i - secondLen)];
            }
        }
        
        for(int i = n ; i >= 1; i--){
            cur_2 += nums[i - 1];
            cur_3 += nums[i - 1];
            if(n - i + 1 >= firstLen){
                
                dp[i][2] = i >= n + 1 ? 0 : Math.max(dp[i + 1][2], cur_2);
                cur_2 -= nums[(i + firstLen - 2)];
            }
            if(n - i + 1 >= secondLen){
                dp[i][3] = i >= n + 1 ? 0 : Math.max(dp[i + 1][3], cur_3);
                cur_3 -= nums[(i + secondLen - 2)];
            }
        }
        
        for(int i = 1; i <= n; i++){
            // index i as L 
            int ans_0 = dp[i][0] + dp[i + 1][3]; 
            
            // index i as M
            int ans_1 = dp[i - 1][0] + dp[i][3];
            
            int ans_2 = dp[i][1] + dp[i + 1][2];
            int ans_3 = dp[i - 1][1] + dp[i][2];
            
            max = Math.max(max, Math.max(ans_0, 
                            Math.max(ans_1, Math.max(ans_2, ans_3))));
                           
        }
        // for(int i = 0; i < n + 2; i++){
        //     System.out.println(dp[i][0]);
        // }
        return max;
    }
}

// dp[i][0]  the sum of the largest elements of consecutive L-length arrays from 0 - i
// dp[i][1]  the sum of the largest elements of consecutive M-length arrays from 0 - i

// dp[i][2]: the sum of the largest elements of consecutive L-length arrays from i - (n - 1)
// dp[i][3]: the sum of the largest elements of consecutive M-length arrays from i - (n - 1)

// Some out-of-range subscripts, value set to 0 (default)
