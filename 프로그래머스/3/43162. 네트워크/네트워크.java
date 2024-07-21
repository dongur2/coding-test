import java.util.*;

class Solution {
    Map<Integer, Boolean> visited;
    int cnt;
    
    public int solution(int n, int[][] computers) {
        visited = new HashMap<>();
        
        countNetworks(n, computers);    
        return cnt;
    }
    
    private void countNetworks(int n, int[][] computers) {
        Queue<Integer> q = new ArrayDeque<>();
        
        //1. 주어진 노드를 0부터 ~ 끝까지 순회
        for(int cur=0; cur<n; cur++) { //0
            //2. 새로운 그래프(BFS 새로 시작): 방문하지 않은 시작 노드라면 방문 체크 & 대기열 추가 & 네트워크 카운트
            if(!visited.containsKey(cur)) {
                q.offer(cur); visited.put(cur, true);
                cnt++;
            } else continue; //방문했던 시작 노드라면 아래는 똑같은 작업이 이어지므로 중지

            //3. 대기열이 빌 때까지 BFS 너비 탐색(똑같은 그래프 내에서 순회하므로 네트워크 카운트는 이뤄지지 않음)
            while(!q.isEmpty()) {
                int curVertex = q.poll();
                
                //자기 자신 연결은 제외하고, 다른 연결 노드를 방문하지 않았다면 대기열 추가 & 방문 처리
                for(int nxt=0; nxt<n; nxt++) {
                    if(nxt == curVertex) continue;
                    else if(computers[curVertex][nxt] == 1 && !visited.containsKey(nxt)) {
                        q.offer(nxt); visited.put(nxt, true);
                    }
                }
            }
        }
    }
    
}