import java.util.*;

class Solution {
    /*
    '그래프의 노드'가 독립적인 2개 세트로 나눠지는지 여부를 반환
    - 서로 연결된(인접한) 노드끼리는 서로 다른 세트에 포함
    - 연결되지 않은 노드가 존재 -> 그래프에 포함되지 않음 
    */
    
    boolean answer = true;
    Map<Integer, Character> map; //방문했던 노드에 부여한 세트 확인 목적
    
    //int[][] graph: 인덱스에 해당하는 노드와 연결된 노드 목록
    public boolean isBipartite(int[][] graph) {
        map = new HashMap<>();
        bfs(graph);
        
        return answer;
    }
    
    void bfs(int[][] graph) {
        for(int i=0; i<graph.length; i++) {
            //시작점
            if(map.get(i) != null) continue;
            
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(i);
            map.put(i, 'A');
                    
            //대기열 방문
            while(!q.isEmpty()) {
                int cur = q.poll();
                
                System.out.println("현재 노드: "+cur);
                
                //다음 노드 탐색
                for(int nextNode : graph[cur]) {
                    //다음 노드가 방문했던 적이 있고, 그 노드와 현재 노드의 세트가 다르면 통과, 같으면 이분되지 않으므로 즉시 리턴
                    if(map.get(nextNode) != null) {
                        if(map.get(cur) == map.get(nextNode)) {
                            System.out.println("cur set:: "+map.get(cur));
                            System.out.println("next set:: "+map.get(nextNode));
                            answer = false; return;
                        } 
                        else continue; //방문한 적 있으나 서로 세트가 다르면 무시
                    }
                    
                    System.out.println("대기열에 추가:"+nextNode);
                    //다음 노드가 방문한 적 없으면, 세트 부여 & 대기열에 추가
                    q.offer(nextNode);
                    if(map.get(cur) == 'A') map.put(nextNode, 'B');
                    else map.put(nextNode, 'A');
                }
            }
        }
    }
}