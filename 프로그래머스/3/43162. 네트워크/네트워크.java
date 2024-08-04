import java.util.*;

class Solution {
    /*
    ** 네트워크 개수 반환
    - 주어진 연결관계 computers에서 서로 연결되어있는 그래프 개수를 반환
    - 연결이 끊기면 네트워크 카운트: BFS, DFS
    */
    
    int cnt = 0;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        // return bfs(n, computers);
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) cnt++; //그래프 연결이 끊기고 dfs새로 시작할 때 그래프 카운트
            dfs(n, computers, i);
        }
        
        return cnt;
    }
    
    int bfs(int n, int[][] computers) {
        for(int i=0; i<n; i++) {
            //시작점
            Queue<Integer> q = new ArrayDeque<>();
            if(visited[i]) continue;
            
            q.offer(i); visited[i] = true;
            cnt++; // 새롭게 처음부터 시작할 때마다 새로운 그래프라는 의미이므로 카운트

            //대기열
            while(!q.isEmpty()) {
                int cur = q.poll();
                
                //연결노드 탐색
                for(int j=0; j<n; j++) {
                    if(computers[cur][j] == 1 && !visited[j]) {
                        q.offer(j); visited[j] = true;
                    }
                }
            }
        }
        return cnt;
    }
    
    void dfs(int n, int[][] computers, int cur) {
        if(visited[cur]) return; //방문했던 노드면 중지
        
        visited[cur] = true;
        for(int i=0; i<n; i++) {
            if(computers[cur][i] == 1) dfs(n, computers, i); // 연결된 노드 방문
        } 
    }
}