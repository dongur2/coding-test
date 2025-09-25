import java.util.*;
class Solution {
    //각 노드의 부모 노드를 저장할 배열
    int[] prev;
    
    class Edge implements Comparable<Edge> {
        int from, to, cost;
        public Edge(int from, int to, int cost) { this.from=from; this.to=to; this.cost=cost; }
        @Override public int compareTo(Edge o) { return this.cost - o.cost; }
    }
    
    public int solution(int n, int[][] costs) {
        prev = new int[n];
        for(int i=0; i<n; i++) {
            prev[i] = i; //초기값은 자기 자신
        }
        
        int answer = 0;
        
        //다리 목록 
        List<Edge> edges = new ArrayList<>();
        for(int[] cost:costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        //비용 싼 다리부터 정렬
        Collections.sort(edges);    
        
        //모든 노드가 연결되어야 하므로, 결국 모든 노드의 루트 노드는 같은 노드여야 한다.
        for(Edge edge:edges) {
            //현재 확인하고 있는 다리의 시작점과 도착점이 연결되어 있는지 확인한다.
            //연결x - 다리 건설하여 연결 (지금이 제일 싼 다리니까 즉시)
            if(!isConnected(edge.from, edge.to)) {
                answer += edge.cost;
                connect(edge.from, edge.to);
            }
        }
        
        return answer;
    }
    
    //각 노드의 루트 노드가 같은지 확인한다
    boolean isConnected(int x, int y) {
        return getRoot(x) == getRoot(y);
    }
    
    //부모 노드를 타고 가서 결국 시작점인 루트 노드를 조회
    int getRoot(int node) {
        if(prev[node] == node) return node;
        return getRoot(prev[node]);
    }
    
    //노드를 연결: 
    void connect(int x, int y) {
        int rootX = getRoot(x);
        int rootY = getRoot(y);
        if(rootX != rootY) prev[rootY] = rootX;
    }
}