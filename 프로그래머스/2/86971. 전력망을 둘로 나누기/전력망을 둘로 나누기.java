import java.util.*;
//n개 노드가 하나로 연결
//간선 하나를 없애서 그래프 2개로 분할 - 각 그래프의 개수 최대한 비슷하게 
//두 그래프의 노드 개수 차이 리턴
class Solution {
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {        
        boolean[] visited;
        
        //간선 하나씩 없앤 모든 경우의 수
        for(int i=0; i<wires.length; i++) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            makeGraph(wires, graph, i);
            
            //각 그래프의 노드 개수를 dfs로 탐색
            visited = new boolean[n+1];
            for(int node=1; node<=n; node++) {
                // if(visited[node]) continue; 
                int cnt = dfs(graph, visited, node, 1); //현재 노드가 속한 그래프의 노드 개수 카운트
                int other = Math.abs(n-cnt); //다른 그래프의 노드 개수
                answer = Math.min(answer, Math.abs(other-cnt)); //현재 노드 차이와 기존 노드 차이 중 작은 값 저장
                break;
            }
        }
        return answer;
    }
    
    public static void makeGraph(int[][] wires, Map<Integer, List<Integer>> g, int idx) {
        for(int i=0; i<wires.length; i++) {
            if(i == idx) continue; //간선 제거
            g.computeIfAbsent(wires[i][0], k->new ArrayList<>()).add(wires[i][1]);
            g.computeIfAbsent(wires[i][1], k->new ArrayList<>()).add(wires[i][0]);
        }
    }
    
    public static int dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int node, int cnt) {
        int count = 1;
        visited[node] = true;
        
        if(graph.get(node) == null) return cnt;
        
        for(int next:graph.get(node)) {
            if(visited[next]) continue;
            count += dfs(graph, visited, next, cnt+1);
        }
        return count;
    }
}