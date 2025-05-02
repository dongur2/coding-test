import java.util.Deque;
import java.util.ArrayDeque;

//시작 단어를 타겟으로 바꾸는데 필요한 최소 작업 수 (불가능하면 0)
class Solution {
    static boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        return bfs(begin, target, words);
    }
    
    public int bfs(String begin, String target, String[] words) {
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(begin, 0));
        
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            //타겟 단어를 만들었을 경우 리턴
            if(curr.word.equals(target)) return curr.cnt;
            
            //방문한 적 없는 단어 중에 한글자만 다른 단어 찾아서 진행
            for(int i=0; i<words.length; i++) {
                if(visited[i] || !isValid(curr.word, words[i])) continue;
                
                visited[i] = true;
                q.offer(new Node(words[i], curr.cnt+1));
            }
        }
        
        return 0;
    }
    
    public boolean isValid(String word, String target) {
        int cnt = 0;
        
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) != target.charAt(i)) cnt++;
            
            //두글자 이상 다를 경우 중지
            if(cnt > 1) return false;
        }
        
        return true;
    }
    
    private class Node {
        private String word;
        private int cnt;
        
        public Node(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}