import java.util.*;

class Solution {
    public int solution(String before, String after) {
        char[] beforeChar = before.toCharArray();
        char[] afterChar = after.toCharArray();
        
        Arrays.sort(beforeChar);
        Arrays.sort(afterChar);
        
        return (Arrays.compare(beforeChar, afterChar) == 0)? 1:0;
    }
}