import java.util.Map;
import java.util.HashMap;
//문자열을 숫자로 변환
class Solution {
    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1); map.put("1", 1);
        map.put("two", 2); map.put("2", 2);
        map.put("three", 3); map.put("3", 3);
        map.put("four", 4); map.put("4", 4);
        map.put("five", 5); map.put("5", 5);
        map.put("six", 6); map.put("6", 6);
        map.put("seven", 7); map.put("7", 7);
        map.put("eight", 8); map.put("8", 8);
        map.put("nine", 9); map.put("9", 9);
        map.put("zero", 0); map.put("0", 0);
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(!map.containsKey(c+"")) {
                sb.append(c+"");
                if(map.containsKey(sb.toString())) {
                    answer.append(map.get(sb.toString())+"");
                    sb = new StringBuilder();
                }
            } else answer.append(map.get(c+""));
        }
        
        return Integer.parseInt(answer.toString());
    }
}