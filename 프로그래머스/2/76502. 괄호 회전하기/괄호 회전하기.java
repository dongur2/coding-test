import java.util.Deque;
import java.util.ArrayDeque;

//올바른 괄호 문자열이 되게 하는 회전 칸 수 - 불가능하면 0
class Solution {
    static int answer = 0;
    
    public int solution(String s) {
        int len = s.length();
        
        //똑같은 문자열을 뒤에 추가
        StringBuffer sb = new StringBuffer(s);
        sb.append(s);
                
        //슬라이딩 윈도우로 한칸씩 뒤로 밀면서 확인
        for(int start=0; start<len; start++) {
            String sub = sb.substring(start, start+len);

            //스택 활용해서 확인
            if(isValid(sub)) answer++;
        }
        
        return answer;
    }
    
    public static boolean isValid(String sub) {
        Deque<Character> st = new ArrayDeque<>();
        
        for(char c:sub.toCharArray()) {
            if(c == '(' || c == '{' || c == '[') st.push(c);
            else {
                if(st.isEmpty()) return false;
                
                char p = st.peek();
                if((c == ')' && p == '(') || (c == '}' && p == '{') || (c == ']' && p == '[')) st.pop();
            }            
        }
        return st.isEmpty();
    }
}