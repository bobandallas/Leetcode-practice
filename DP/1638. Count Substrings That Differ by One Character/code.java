class Solution {
    public int countSubstrings(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp_l = new int[n + 1][m + 1];
        int[][] dp_r = new int[n + 1][m + 1];
        
        // two dp: dp_l means the left part longest substring ending in s[i],t[j]
        for(int i = n - 1 ; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                if(s.charAt(i) == t.charAt(j)){
                    dp_r[i][j] = dp_r[i + 1][j + 1] + 1;
                }else{
                    dp_r[i][j] = 0;
                }
            }
        }
        
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp_l[i][j] = dp_l[i - 1][j - 1] + 1;
                }else{
                    dp_l[i][j] = 0;
                }
            }
        }
        
        // iterate the combination of different each character in two string as replaced part, 
        // caculate how many left part combination and how many right part combination
      
        // becasue i,j as the replaced part, we can define i, j as mid point to find i-1 j-1 same longest subtring and i + 1 j + 1 same longest substirng
        // so we multiple them, we can get the result that if we change the i - > j, the number of substrings in s that differ from some substring in t by exactly one character on i, j positon.
        int res = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(s.charAt(i) != t.charAt(j)){
                    
                    int left = dp_l[i][j];
                    int right = dp_r[i + 1][j + 1];
                    res += (left + 1)*(right + 1);
                }
            }
        }
        
        return res;
    }
}
