/*
    현재 한쪽으로만 향해있는 각각의 경로
    모든 도시에서 0번 도시로 올 수 있도록 바꿔야 하는 최소 경로 수 
 */
 import java.util.*;
class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int answer = 0;

    public int minReorder(int n, int[][] connections) {
        //연결 리스트 형식 그래프 생성 
        for(int[] conn : connections) {
            graph.computeIfAbsent(conn[0], k -> new ArrayList<>()).add(conn[1]);
            graph.computeIfAbsent(conn[1], k -> new ArrayList<>()).add(-conn[0]); //바꿀 경우의 경로 (음수로 구분)
        }    

        return dfs(new boolean[n], 0);
    }

    int dfs(boolean[] visited, int curr) {
        int change = 0;

        visited[curr] = true;

        if(graph.get(curr) != null) {
            for(int nxt : graph.get(curr)) {
                if(visited[Math.abs(nxt)]) continue;
                change += dfs(visited, Math.abs(nxt)) + (nxt > 0 ? 1 : 0); //양수일 경우 0 -> 다른 도시인 경로니까 바꿔야 함(+1)
            }
        }

        return change;
    }
}