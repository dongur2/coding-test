/*
    모든 섬을 연결하는 데 필요한 최소 비용
*/
import java.util.*;
class Solution {
    
    class Edge implements Comparable<Edge> {
        int from, to, cost;
        public Edge(int from, int to, int cost) { this.to=to; this.cost=cost; this.from=from; }
        @Override public int compareTo(Edge o) { return this.cost - o.cost; }
    }
    
    int[] prev;
    
    public int solution(int n, int[][] costs) {        
        List<Edge> edges = new ArrayList<>();
        for(int[] cost:costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        //저렴한 다리 우선 정렬
        Collections.sort(edges);
        
        //각 섬이 어떤 그룹에 속해있는지 파악하기 위한 그룹 관리 
        prev = new int[n];
        for(int i=0; i<n; i++) {
            prev[i] = i;
        }
        
        int answer = 0;
        int connected = 0;
        
        //정렬한 다리 차례대로 연결 
        for(Edge e : edges) {
            //두 섬이 연결되어있지않으면: 다리 추가+비용 추가
            if(findPrev(e.from) != findPrev(e.to)) {
                union(e.from, e.to);
                answer += e.cost;
                connected++;
            }
        }
        
        return answer; 
    }
    
    //x 노드의 루트 노드 찾기 
    int findPrev(int x) {
        if(prev[x] == x) return x;
        return findPrev(prev[x]);
    }
    
    void union(int x, int y) {
        int rootX = findPrev(x);
        int rootY = findPrev(y);
        if(rootX != rootY) prev[rootY] = rootX;
    }
}