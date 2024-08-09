import java.util.*;

/*
N개 노드(1~N-1번) 중에 1번 노드에서 가장 멀리 떨어진 노드의 개수 리턴
- edge[0] == [3,6] 3번 노드와 6번 노드가 연결됨

그래프화: 해시테이블 - {노드:연결노드목록(List)}
DFS끝까지 순회
최대 간선 업데이트
최대 간선 가진 노드 개수 카운트
*/
class Solution {
    Map<Integer, List<Integer>> graph;
    boolean[] visited;
    
    public int solution(int n, int[][] edge) {
        graph = new HashMap<>();
        visited = new boolean[n+1];
        
        //그래프화
        makeGraph(edge);
        
        //bfs
        return bfs();
    }
    
    void makeGraph(int[][] edge) {
        for(int[] connection : edge) {
            addConnection(connection[0], connection[1]);
            addConnection(connection[1], connection[0]);
        }
    }
    
    void addConnection(int baseNode, int addNode) {
        List<Integer> connects = graph.getOrDefault(baseNode, new ArrayList<>());
        connects.add(addNode);
        graph.put(baseNode, connects);
    }
    
    int bfs() {
        int maxFar = 0, answer = 0;
        
        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0});
        visited[1] = true;
        
        while(!q.isEmpty()) {
            //cur
            int[] cur = q.poll();
            int curNode = cur[0];
            int curEdge = cur[1];
            
            if(maxFar < curEdge) {
                maxFar = curEdge;
                answer = 1;
            }
            else if (maxFar == curEdge) answer++;
            
            //near
            for(int nxt : graph.get(curNode)) {
                if(!visited[nxt]) {
                    q.offer(new int[]{nxt, curEdge+1});
                    visited[nxt] = true;
                }
            }
        }
        return answer;
    }            
}