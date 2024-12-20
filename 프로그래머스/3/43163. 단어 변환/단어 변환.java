import java.util.*;

/*
Q. begin -> target으로 변환할 수 있는 "최소 단계 수"
- 변환할 수 없으면 0 리턴
- bfs로 탐색하면서 target으로 변환 완료되면 즉시 그 시점의 단계 수를 리턴

- 다음 단어로 변환하기 전 확인
(1) 이미 변환한 적 있는 단어는 무시
(2) 다른 글자 개수가 1글자 초과할 경우 무시
*/
class Solution {
    public int solution(String begin, String target, String[] words) {        
        return getMinimumForTarget(begin, target, words);
    }
    
    //최소 단계 구하기
    private int getMinimumForTarget(String word, String target, String[] words) {
        //주어진 단어 배열에 타겟 단어가 없으면 이미 불가능하므로 즉시 리턴
        if(!isTargetExist(target, words)) return 0; 
        
        //bfs
        return bfs(word, target, words);
    }
    
    //주어진 배열의 타겟 단어 포함 여부
    private boolean isTargetExist(String target, String[] words) {
        for(String word:words) {
            if(word.equals(target)) return true;
        }
        return false;
    }
    
    private int bfs(String word, String target, String[] words) {
        //세팅
        Queue<Word> q = new ArrayDeque<>();
        q.offer(new Word(0, word));
        
        List<String> log = new ArrayList<>();
        
        //큐 기반 탐색
        while(!q.isEmpty()) {
            //now
            Word cur = q.poll();
            String curWord = cur.word;
            int curPhase = cur.phase;
            
            //현재 단어가 target단어로 변환 완료된 상태라면 리턴
            if(curWord.equals(target)) return curPhase;
            
            //next: 변환한 적 없고 & 다른 글자가 1글자인 단어로 변환 가능
            for(int i=0; i<words.length; i++) {
                if(!isValid(log, curWord, words[i], i)) continue;
                
                q.offer(new Word(curPhase+1, words[i]));
                log.add(words[i]); //같은 단어로 또 변환하는 것을 방지하기 위한 로그
            }
        }
        return 0;
    }
    
    //단어 변환 전 유효 조건인지 확인
    private boolean isValid(List<String> log, String now, String nxt, int idx) {
        if(log.contains(nxt)) return false; //변환한 적 있는지 확인
        return isValidSameCnt(now.toCharArray(), nxt.toCharArray());
    }
    
    //다른 단어 1개 초과 확인
    private boolean isValidSameCnt(char[] now, char[] nxt) {
        int cnt = 0;
        for(int i=0; i<now.length; i++) {
            if(now[i] != nxt[i]) cnt++;
            if(cnt > 1) return false;
        }
        return cnt == 1 ? true : false;
    }
    
    class Word {
        int phase;
        String word;
        
        public Word(int phase, String word) {
            this.phase = phase;
            this.word = word;
        }
    }
}