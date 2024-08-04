import java.util.*;

class Solution {
    /*
    ** 타겟 넘버를 만들 수 있는 방법의 개수를 반환
    - 주어진 숫자들의 순서는 그대로 하되 +,-만 바꿔서 타겟 넘버를 만들기
    
    개수니까 BFS, DFS 모두 가능할 듯?
    */
    
    int answer = 0;

    class Node {
        int num; int level;
        public Node(int num, int level) {
            this.num = num; this.level = level;
        }
    }
    
    public int solution(int[] numbers, int target) {
        // bfs(numbers, target);
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    void bfs(int[] numbers, int target) {
        //시작점: 0
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0)); 
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.level == numbers.length) break; //주어진 숫자 다 돌았으면 종료
      
            //인접노드
            for(int n : new int[] {-1, 1}) {
                int nxt = cur.num + (numbers[cur.level] * n); // -, +
                q.offer(new Node(nxt, cur.level+1));
                
                if((cur.level + 1) == numbers.length && nxt == target) answer++;
            }
        }
    }
    
    void dfs(int[] numbers, int target, int cur, int level) {
        if(level == numbers.length) {
            if(cur == target) answer++;
            return;
        }
        
        for(int n:new int[]{-1, 1}) {
            int nxt = cur + (numbers[level] * n);
            dfs(numbers, target, nxt, level+1);
        }
    }
}