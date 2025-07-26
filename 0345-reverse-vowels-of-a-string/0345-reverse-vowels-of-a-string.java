import java.util.Deque;
import java.util.ArrayDeque;

/*
    주어진 문자열에서 모음만 위치를 거꾸로 변경해서 리턴 
 */
class Solution {
    public String reverseVowels(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        //모음만 순서대로 저장 
        for(char c:s.toCharArray()) {
            if(isVowel(c)) stack.push(c);
        }

        //새로운 문자열 생성 
        StringBuilder sb = new StringBuilder();
        for(char c:s.toCharArray()) {
            if(isVowel(c)) sb.append(stack.pop()); //모음 만나면 거꾸로 붙이기
            else sb.append(c);
        }

        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' 
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}