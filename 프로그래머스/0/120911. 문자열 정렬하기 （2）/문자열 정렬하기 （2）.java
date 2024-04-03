import java.util.*;

class Solution {
    public String solution(String my_string) {
        char[] answer = my_string.toLowerCase().toCharArray();
        Arrays.sort(answer);
        return String.valueOf(answer);
    }
}