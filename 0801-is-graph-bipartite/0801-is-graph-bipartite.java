/*
    그래프 노드가 두 분류로 나눠지는지 확인 
    연결되는 두 노드는 서로 다른 분류여야 한다.
 */
 import java.util.Map; import java.util.HashMap;
class Solution {
    int[] kinds;
    int n;
    boolean res = true;

    public boolean isBipartite(int[][] graph) {
        n = graph.length;
        kinds = new int[n]; //7,8

        for(int i=0; i<n; i++) {
            if(kinds[i] == 0) dfs(graph, i, 7);
        }
        return res;
    }

    void dfs(int[][] g, int curr, int k) {
        kinds[curr] = k; //그룹 부여

        for(int nxt : g[curr]) {
            //미방문
            if(kinds[nxt] == 0) dfs(g, nxt, (k == 7) ? 8 : 7);
            
            //방문했던 노드
            //서로 분류 같으면 false
            if(kinds[nxt] == kinds[curr]) {
                res = false; return;
            }
        }
    }
}