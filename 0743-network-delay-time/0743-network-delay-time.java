import java.util.*;

class Solution {
    Map<Integer, List<Edge>> graph;
    int[] costs;
    int INF = Integer.MAX_VALUE;
    
    public int networkDelayTime(int[][] times, int n, int k) {
        //그래프화
        makeGraph(times);
        
        //다익스트라
        return dijkstra(n, k);
    }
    
    void makeGraph(int[][] times) {
        graph = new HashMap<>();
        
        for(int[] time:times) {
            List<Edge> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new Edge(time[1], time[2]));
            graph.put(time[0], new ArrayList<>(list));
        }
    }
  
    //노드 번호, 비용
    class Edge implements Comparable<Edge> {
        public int node; public int cost;
        public Edge(int node, int cost) {
            this.node = node; this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
    
    int dijkstra(int n, int k) {
        int[] costs = new int[n+1]; //최소비용 저장
        Arrays.fill(costs, INF); //무한대로 초기화

        Queue<Edge> pq = new PriorityQueue<>(); //heap
        
        //시작점 초기화
        costs[k] = 0; //비용 0
        pq.add(new Edge(k, 0)); //힙에 시작점 추가
        
        //예약
        while(!pq.isEmpty()) {
            Edge cur = pq.poll(); //지금까지의 비용 가장 적은 노드 방문
            
            if(costs[cur.node] < cur.cost || graph.get(cur.node) == null) continue; //이미 저장된 현재 노드로 오는 데 필요한 최소 비용보다 현재 노드가 가진 비용이 크다면 무시: 이미 비용 초과
            
            for(Edge near : graph.get(cur.node)) { //인접 노드 업데이트
                int newCost = costs[cur.node] + near.cost;
                if(newCost < costs[near.node]) {
                    pq.add(new Edge(near.node, newCost));
                    costs[near.node] = newCost;
                }
            }
        }
        
        //인덱스 0을 제외하고 INF값이 없으면 모든 노드 순회 완료
        return Arrays.stream(costs).filter(cnt -> cnt == INF).count() == 1L ? Arrays.stream(costs).skip(1).max().getAsInt() : -1;
    }
}