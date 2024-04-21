import java.util.*;

class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        
        StringBuilder reversed = new StringBuilder(String.valueOf(chars)).reverse();
        return reversed.toString();
    }
}