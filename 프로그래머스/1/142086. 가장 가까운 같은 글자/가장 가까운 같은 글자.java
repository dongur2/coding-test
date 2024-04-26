import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> map = new HashMap<>(); // 글자:인덱스
        
        for(int i=0; i<s.length(); i++) {
            if(!map.containsKey(s.charAt(i))) answer[i] = -1;
            else answer[i] = i - map.get(s.charAt(i));
            map.put(s.charAt(i), i);
        }
        
        return answer;
    }
}