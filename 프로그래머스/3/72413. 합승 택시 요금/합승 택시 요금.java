import java.util.*;

class Solution {
    Map<Integer, List<Edge>> graph;
    int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //그래프화
        makeGraph(fares);
        
        //다익스트라
        int[] startA = dijkstra(n, a); // a에서 출발했을 때 최소비용(무방향 그래프 => 거꾸로 해석 가능: a에 도착했을 때 최소비용)
        int[] startB = dijkstra(n, b); // b에서 출발했을 때 최소비용
        int[] start = dijkstra(n, s); // s에서 출발했을 때 최소비용
        
        //비교: 합승 끝내는 지점을 1~N번까지 확인해서 최소 비용 구하기
        int answer = INF; 
        for(int i=1; i<=n; i++) {
            answer = Math.min(answer, start[i]+startA[i]+startB[i]);
        }
        return answer;
    }
    
    void makeGraph(int[][] fares) {
        graph = new HashMap<>();
        
        for(int[] fare:fares) {
            addConnection(fare[0], fare[1], fare[2]);
            addConnection(fare[1], fare[0], fare[2]);
        }
    }
    
    void addConnection(int start, int arrive, int cost) {
        List<Edge> list = graph.getOrDefault(start, new ArrayList<>());
        list.add(new Edge(arrive, cost));
        graph.put(start, new ArrayList<>(list));
    }
    
    class Edge implements Comparable<Edge> {
        public int node; public int cost;
        public Edge(int node, int cost) {
            this.node = node; this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    int[] dijkstra(int n, int start) {
        //초기 작업: 비용 무한대로 초기화
        int[] costs = new int[n+1];
        Arrays.fill(costs, INF);
        
        Queue<Edge> pq = new PriorityQueue<>();
        
        //시작점 초기화
        costs[start] = 0;
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            //cur
            Edge cur = pq.poll();
            
            //현재 노드의 비용이 현재 노드까지 오는 데 필요한 최소 비용을 초과하거나, 현재 노드의 인접 노드가 없다면 통과
            if(cur.cost > costs[cur.node] || graph.get(cur.node) == null) continue; 
            //near
            for(Edge next : graph.get(cur.node)) {
                int nextCost = costs[cur.node] + next.cost; //다음 노드까지 가는 데 필요한 비용
                
                //새롭게 계산한 비용이 저장되어있는 다음 노드까지 가는 데 필요한 최소 비용보다 작을 경우에만 업데이트
                if(nextCost < costs[next.node]) {
                    costs[next.node] = nextCost;
                    pq.add(new Edge(next.node, nextCost));
                }
            }
        }
        return costs;
    }
}