/*
    도시 n개 (0번 - n-1번)
    방향 그래프: 두 도시 사이 간선은 1개 
    모든 도로가 한 방향으로 다니도록 바꿨음
    
    connections[i] = [도시1, 도시2] 도시1 -> 도시2 도로

    각 도시가 수도를 방문할 수 있도록 도로 방향을 바꿀 것

    방향을 바꿔야 하는 최소 도로 개수를 리턴
 */

import java.util.List; import java.util.ArrayList;
class Solution {
    List<List<Integer>> edges = new ArrayList<>();

    public int minReorder(int n, int[][] connections) {
        for(int i=0; i<n; i++) edges.add(new ArrayList<>());

        for(var c : connections) {
            edges.get(c[0]).add(c[1]);
            edges.get(c[1]).add(-c[0]); //기존 노드에서 방향을 바꿀 경우 구분 
        }

        return dfs(new boolean[n], 0); //0에서 모든 노드에 방문
    }

    int dfs(boolean[] visited, int from) {
        int change = 0;

        visited[from] = true;

        for(var to : edges.get(from)) {
            if(!visited[Math.abs(to)]) change += dfs(visited, Math.abs(to)) + (to > 0 ? 1 : 0); //경로를 바꿨으면 카운트
        }

        return change;
    }
}