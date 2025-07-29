import java.util.Deque;
import java.util.ArrayDeque;
/*
    *를 포함한 문자열이 주어졌을 때,
    작업 하나 당: *를 하나 고르고, 그 별의 왼쪽에서 가장 가까운 위치에 있는 글자와 선택한 *를 삭제한다.
    모든 *를 삭제한 문자열을 리턴한다.
 */
class Solution {
    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        //문자열 처음부터 끝까지 스택에 저장
        for(char c : s.toCharArray()) {
            //현재 문자가 *이 아니면 저장 
            if(c != '*') stack.push(c);
            //현재 문자가 *이면 저장하지 않고 스택에 들어있는 글자를 꺼내 제거 (*에 가장 가까운 왼쪽 글자)
            else stack.pop();
        }
        
        //문자열 탐색이 종료되면(끝까지 확인) 스택에 들어있는 글자를 거꾸로 리턴 
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}