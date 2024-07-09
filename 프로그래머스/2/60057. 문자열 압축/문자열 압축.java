import java.util.*;
import java.util.stream.*;

class Solution {
    // 스택에 저장할 클래스: 문자열과 카운트로 구성
    private class Entry {
        private String word;
        private int cnt;
        
        public Entry(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
        
        // toString을 오버라이딩해서 스택에서 꺼내어 압축된 문자열로 변환할 때 사용
        @Override
        public String toString() {
            return (cnt > 1) ? word+cnt : word; 
        }
    }
    
    public int solution(String s) {
        //1. 리턴할 길이 변수 생성: 길이 최대값은 원래 문자열의 길이
        int answer = s.length();
        
        //2. 문자열을 압축할 범위를 최소 1개부터 원래 문자열 반까지 하나씩 늘려가며 순회 & 압축
        for(int i=1; i<=s.length()/2; i++) {
            //3. 문자열을 범위에 따라 나눠 리스트에 저장
            List<String> words = new ArrayList<>();
            for(int j=0; j<s.length(); j+=i) {
                words.add(s.substring(j, Math.min(j+i, s.length())));  // * 끝 인덱스가 문자열 길이를 넘지 않도록 최소 연산
            }
            
            //4. 스택을 생성하고, 맨 첫 번째 문자열 블록을 저장 
            Deque<Entry> stack = new ArrayDeque<>();
            stack.push(new Entry(words.get(0), 1));
            
            //5. 저장한 문자열 블록 다음부터 끝까지 자른 문자열을 스택에 저장한 최신 문자열값과 비교
            for(String word:words.subList(1, words.size())) {
                
                //6. 스택에 저장한 앞 글자와 현재 글자가 같으면: 스택에서 값을 꺼내어 카운트를 추가한 다음 다시 저장
                if(stack.peek().word.equals(word)) {
                    Entry preWord = stack.pop();
                    preWord.cnt++;                
                    stack.push(preWord);
                }
                
                //7. 앞 글자와 현재 글자가 다르면: 스택에 Entry 새 객체를 생성해 저장
                else stack.push(new Entry(word, 1));
            }
            //8. 스택에 저장된 값을 압축된 문자열로 변환
            String compressed = stack.stream()
                .map(Entry::toString).collect(Collectors.joining());
            
            //9. 압축 문자열의 길이와 전역 길이 변수의 최솟값을 비교해 최솟값을 저장
            answer = Math.min(answer, compressed.length());
        }
        //10. 길이 리턴
        return answer;
    }
}