import java.util.*;

class Solution {
    Map<String, Integer> cntMap;
    
    public int solution(String begin, String target, String[] words) {
        cntMap = new HashMap<>();
        return countPhase(begin, target, words);
    }
    
    private int countPhase(String begin, String target, String[] words) {
        //1. 완전 탐색 반복: 현재 문자열을 꺼내 방문
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin); cntMap.put(begin, 0);
        
        while(!q.isEmpty()) {
            String cur = q.poll();
            
            //2. words배열을 탐색하여 현재 문자열과 문자 1개만 다른 문자열을 찾아 예약, 카운트 저장
            for(String word:words) {
                int cnt = 0;
                for(int i=0; i<word.length(); i++) {
                    if(cntMap.get(word) == null && cur.charAt(i) != word.charAt(i)) {
                        cnt++;
                        if(cnt > 1) break;
                    }
                }
                
                if(cnt == 1) {
                    q.offer(word); cntMap.put(word, cntMap.get(cur)+1);

                    //3. 다음 문자열이 target과 같으면 카운트 리턴
                    if(word.equals(target)) return cntMap.get(word);
                }
            }
        }

        //4. 반복문이 끝날 때까지 target 문자열로 변환하지 못했다면 0 리턴
        return 0;
    }
}