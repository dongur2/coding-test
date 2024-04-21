import java.util.*;

class Solution {
    public long solution(long n) {
        char[] num = String.valueOf(n).toCharArray();
        Arrays.sort(num);
        StringBuilder reversed = new StringBuilder(String.valueOf(num)).reverse();
        
        return Long.parseLong(reversed.toString());
    }
}