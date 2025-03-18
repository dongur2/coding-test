import java.util.*;

/*
- 최소 변환 횟수 [bfs]
- 한번에 글자 하나 
*/
class Solution {
    String[] wordsTable;
    String targetWord;
    boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        wordsTable = words; targetWord = target;
        visited = new boolean[words.length];
        
        return bfs(begin);
    }
    
    int bfs(String start) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 0)); //현재 단어, 변환 횟수
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            String nowWord = cur.word;
            int nowCnt = cur.cnt;
            
            //단어 변환 완료했을 경우 리턴
            if(nowWord.equals(targetWord)) return nowCnt;
            
            //새로운 단어 중에 글자 1개만 다른 단어로 변환
            for(int i=0; i<wordsTable.length; i++) {
                if(visited[i]) continue;
                
                //1개만 다른 글자 찾기
                int diffCnt = 0, diffIdx = -1;
                for(int idx=0; idx<start.length(); idx++) {
                    if(nowWord.charAt(idx) != wordsTable[i].charAt(idx)) {
                        diffCnt++; 
                        diffIdx = idx;
                    }
                }
                
                //변환
                if(diffCnt == 1) {
                    StringBuilder tempWord = new StringBuilder(nowWord);
                    tempWord.setCharAt(diffIdx, wordsTable[i].charAt(diffIdx));
                    q.offer(new Node(tempWord.toString(), cur.cnt+1));
                    visited[i] = true;                    
                }
            }
        }
        
        return 0;
    }
    
    class Node {
        String word;
        int cnt;
        
        public Node(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}