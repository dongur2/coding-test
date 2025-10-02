import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    class Entry {
        String str; int cnt;
        public Entry(String str, int cnt) { this.str=str; this.cnt=cnt; }
    }
    
    int bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        
        Deque<Entry> q = new ArrayDeque<>();
        q.offer(new Entry(begin, 0));
        
        while(!q.isEmpty()) {
            Entry curr = q.poll();
            String currStr = curr.str;
            int currCnt = curr.cnt;
            
            //타겟단어 완성 - 리턴
            if(currStr.equals(target)) return currCnt;
            
            for(int i=0; i<words.length; i++) {
                if(visited[i]) continue;
                
                //한글자만 다른 글자만 탐색
                if(isValid(words[i], currStr)) {
                    q.offer(new Entry(words[i], currCnt+1)); visited[i] = true; 
                }
            }
        }
        
        //타겟 단어 실패
        return 0;
    }
    
    boolean isValid(String word, String curr) {
        int cnt = 0;
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) != curr.charAt(i)) cnt++;
            if(cnt > 1) return false;
        }
        return true;
    }
}