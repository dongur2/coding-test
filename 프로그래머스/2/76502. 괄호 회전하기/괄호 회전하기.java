import java.util.*;

class Solution {
    public int solution(String s) {
        //1. 문자열 두 개를 이어붙인 새로운 문자열 생성
        String line = s + s;
        
        //2. 배열 크기를 유지한 윈도우를 끝까지 이동하며 순회
        int cnt = 0;
        for(int i=0; i<s.length(); i++) {
            //3. 변경한 문자열, 시작인덱스와 끝인덱스를 올바른 괄호 문자열 판별 메서드로 넘김
            if(isValid(line, i, i+s.length())) cnt++;
        }
        return cnt;
    }
    
    private boolean isValid(String line, int start, int end) {
        Deque<Character> stack = new ArrayDeque<>();
        
        String t = "({[";
        //시작~끝인덱스 사이 순회
        for(int i=start; i<end; i++) {
            //열린 괄호는 스택에 저장
            if(t.contains(line.charAt(i)+"")) stack.push(line.charAt(i));
            else {
                //닫힌 괄호가 빈 스택에 들어가면 즉시 false 리턴
                if(stack.isEmpty()) return false;
                
                if(line.charAt(i) == ')' && stack.peek() == '('
                  || line.charAt(i) == '}' && stack.peek() == '{'
                  || line.charAt(i) == ']' && stack.peek() == '[') stack.pop();
            }
        }
        return stack.isEmpty();
    }
}