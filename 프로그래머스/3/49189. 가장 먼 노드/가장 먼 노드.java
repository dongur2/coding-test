import java.util.*;

class Solution {
    /*
    Q. 1번 노드에서 가장 멀리 떨어진 "노드의 개수"를 리턴
    - 노드 N개(1번~N번)
    - 1번에서 N번까지 '최단경로'로 이동했을 때 간선의 개수가 기준
    */
    Map<Integer, List<Integer>> graph;
    boolean[] visited;
    int dist = 0;
    Map<Integer, Integer> distMap;
    
    public int solution(int n, int[][] edge) {
        graph = new HashMap<>();
        visited = new boolean[n+1];
        distMap = new HashMap<>();
        
        //{노드 : [인접노드목록]}
        makeGraph(edge);
        
        //dfs: 1번 노드에서 시작
        dfs(1);
        
        //최대 간선을 가지는 노드 개수를 리턴
        return (int)distMap.values().stream().filter(d -> d == dist).count();
    }
    
    void makeGraph(int[][] edge) {
        for(int[] e : edge) {
            List<Integer> front = graph.getOrDefault(e[0], new ArrayList<>());
            front.add(e[1]);
            graph.put(e[0], new ArrayList<>(front));
            
            List<Integer> back = graph.getOrDefault(e[1], new ArrayList<>());
            back.add(e[0]);
            graph.put(e[1], new ArrayList<>(back));
        }
    }
    
    void dfs(int node) {
        Queue<int[]> q = new ArrayDeque<>();
        
        //start
        q.offer(new int[]{node, 0});
        visited[node] = true;
        distMap.put(node, 0);
        
        //queue
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curNode = cur[0];
            int curCnt = cur[1];
            
            dist = Math.max(dist, curCnt);
            distMap.put(curNode, curCnt);
            
            for(int nxtNode : graph.get(curNode)) {
                if(!visited[nxtNode]) {
                    q.offer(new int[]{nxtNode, curCnt+1});
                    visited[nxtNode] = true;
                }
            }
        }
    }
    
}