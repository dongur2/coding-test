import java.util.Deque;
import java.util.ArrayDeque;
/*
    암호화된 문자열을 복호화하여 리턴 
    k[암호화된 문자열] -> 암호화된 문자열을 k번 반복하여 조합 (k > 0)
    공백 없음, 형식에 필요한 괄호 포함, k 외 숫자는 포함되지 않음.
 */
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            if(c != ']') stack.push(c);

            //현재값이 ]면 복호화 작업 시작 
            else {
                StringBuilder part = new StringBuilder();
                while(stack.peek() != '[') {
                    part.append(stack.pop());
                }
                stack.pop(); //[ 없애고
                
                //k 꺼내기 
                StringBuilder k = new StringBuilder();
                while(!stack.isEmpty() && (stack.peek() >= '0' && stack.peek() <= '9')) {
                    k.append(stack.pop());
                }

                String multipled = part.reverse().toString().repeat(Integer.parseInt(k.reverse().toString())); //뒤집어서 k번 반복해서 붙이고 
                
                //다시 스택에 저장 
                for(char m:multipled.toCharArray()) {
                    stack.push(m);
                }
            }
        }

        //모두 종료됐으면 스택에서 꺼내 뒤집어 리턴 
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.reverse().toString();
    }

}