/*
    n개 송전탑
    - 전선 하나를 끊어서 전력망을 2개로 분할
    - 두 전력망의 송전탑 개수는 최대한 비슷하게 
    >>> 두 전력망의 송전탑 개수 차이 중 최소값
*/
import java.util.Map; import java.util.HashMap;
import java.util.List; import java.util.ArrayList;

class Solution {
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        //간선 하나씩 차례대로 끊어보기
        for(int i=0; i<wires.length; i++) {
            
            //노드:[연결노드]
            Map<Integer, List<Integer>> graph = new HashMap<>();
            makeGraph(wires, graph, i);   
            
            boolean[] visited = new boolean[n+1];
            
            //각 전력망 개수 카운트 
            int[] counts = new int[2];
            int idx = 0;
            for(int curr=1; curr<=n; curr++) {
                if(!visited[curr]) counts[idx++] = dfs(graph, curr, 1, visited);
            }
            
            //전력망 송전탑 개수 업데이트
            answer = Math.min(answer, Math.abs(counts[1] - counts[0]));
        }
        
        
        return answer;
    }
    
    void makeGraph(int[][] wires, Map<Integer, List<Integer>> graph, int cutIdx) {
        for(int i=0; i<wires.length; i++) {
            if(i == cutIdx) continue; //간선 컷 

            //무방향그래프
            graph.computeIfAbsent(wires[i][0], k -> new ArrayList<>()).add(wires[i][1]);
            graph.computeIfAbsent(wires[i][1], k -> new ArrayList<>()).add(wires[i][0]);
        }
    }
    
    int dfs(Map<Integer, List<Integer>> graph, int curr, int cnt, boolean[] visited) {
        visited[curr] = true;
        
        if(graph.get(curr) == null) return cnt;
        for(int next : graph.get(curr)) {
            if(!visited[next]) cnt = dfs(graph, next, cnt+1, visited);
        }
        
        return cnt;
    }
}