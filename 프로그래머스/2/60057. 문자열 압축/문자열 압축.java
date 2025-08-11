/*
    연속 중복 값 => 중복 횟수 + 값 (1은 생략)
    가장 짧게 압축한 문자열의 길이 리턴 
*/
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    public int solution(String s) {
        if(s.length() == 1) return 1;
        
        int answer = Integer.MAX_VALUE;
                
        //중복 확인할 문자열 길이를 1~s.length-1 마다 확인해서 최소 길이 탐색
        for(int len=1; len<s.length(); len++) {
            answer = Math.min (compress(s, len), answer);
        }
        
        return answer;
    }
    
    int compress(String s, int l) {
        Deque<Entry> stack = new ArrayDeque<>();
        
        StringBuilder sb = new StringBuilder();
        
        int idx=0;
        
        while(idx+l <= s.length()) {
            String curr = s.substring(idx, idx+l);
            
            if(stack.isEmpty()) stack.push(new Entry(curr, 1));
            else {
                //들어있는 값과 비교
                Entry top = stack.pop();
                //같으면 카운트 추가
                if(top.word.equals(curr)) {
                    top.cnt++;
                    stack.push(top);
                }
                //다르면 꺼낸 값은 문자열에 추가하고 들고있던 값은 스택에 저장
                else {
                    if(top.cnt != 1) sb.append(top.cnt);
                    sb.append(top.word);
                    stack.push(new Entry(curr, 1));
                }
            }
            
            idx += l;
        }
        
        //스택에 남아있는 값 붙이기
        while(!stack.isEmpty()) {
            Entry top = stack.pop();
            if(top.cnt != 1) sb.append(top.cnt);
            sb.append(top.word);
        }
        
        //자르는 문자열 길이를 충족하지 못하고 남겨진 꼬리 붙이기 
        while(idx < s.length()) {
            sb.append(s.charAt(idx++));
        }
        
        return sb.length();
    }
    
    class Entry {
        String word; int cnt;
        public Entry(String word, int cnt) { this.word = word; this.cnt = cnt; }
    }
}