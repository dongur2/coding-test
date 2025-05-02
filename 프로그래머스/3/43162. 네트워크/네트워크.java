import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//네트워크 개수
class Solution {
    public int solution(int n, int[][] computers) {
        //연결된 그래프 개수 - dfs 활용
        int answer = 0;

        //{노드:[인접노드]} 맵 만들기
        Map<Integer, List<Integer>> map = new HashMap<>();
        makeGraph(map, n, computers);
        
        //dfs로 연결된 영역 탐색하면서 네트워크 개수 세기
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            dfs(map, visited, i);
            answer++;
        }
        
        
        return answer;
    }
    
    public void makeGraph(Map<Integer, List<Integer>> map, int n, int[][] computers) {
        for(int i=0; i<n; i++) {
            int[] arr = computers[i];
            
            for(int j=0; j<n; j++) {
                if(arr[j] == 1) map.computeIfAbsent(i, k->new ArrayList<>()).add(j);
            }
        }
    }
    
    public void dfs(Map<Integer, List<Integer>> map, boolean[] visited, int node) {
        visited[node] = true;
        
        if(map.get(node).isEmpty()) return;
        //인접 노드 이동
        for(int n:map.get(node)) {
            if(visited[n]) continue;
            dfs(map, visited, n);
        }
    }
}