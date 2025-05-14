import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

//n개 노드 그래프 - 1번~n번: 1번에서 가장 멀리 떨어진 노드의 개수
//최단경로로 이동했을 떄 간선 개수가 가장 많은 노드
class Solution {
    static Map<Integer, Integer> distMap = new HashMap<>(); //거리:노드개수
    static int dist = Integer.MIN_VALUE; //가장 먼 노드 거리
    
    public int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        makeGraph(edge, graph);     
        bfs(n, graph);
        return distMap.get(dist);
    }
    
    //노드:{자식노드}
    public static void makeGraph(int[][] edge, Map<Integer, List<Integer>> g) {
        for(int[] e:edge) {
            g.computeIfAbsent(e[0], k->new ArrayList<>()).add(e[1]);
            g.computeIfAbsent(e[1], k->new ArrayList<>()).add(e[0]);
        }
    }
    
    //최단거리 탐색
    public static void bfs(int n, Map<Integer, List<Integer>> graph) {
        boolean[] visited = new boolean[n+1]; //1번~n번
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0}); //[노드번호, 현재까지의 거리]
        visited[1] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int curNode = curr[0];
            int curDist = curr[1];
            
            dist = Math.max(dist, curDist); //계속 최대거리 업데이트
            distMap.compute(curDist, (k, v) -> (v==null) ? 1:v+1); //거리당 개수 업데이트
            
            //자식노드가 없으면 중지
            if(graph.get(curNode) == null) continue;
            
            //자식노드가 있으면 이동
            for(int nextNode:graph.get(curNode)) {
                if(visited[nextNode]) continue; //방문했던 노드는 무시

                q.offer(new int[]{nextNode, curDist+1});
                visited[nextNode] = true;
            }
        }
    }
}