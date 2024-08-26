import java.util.*;

class Solution {
    Map<Integer, List<Edge>> graph;
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        //그래프
        makeGraph(edges, succProb);
        
        //다익스트라:start -> end 최대 비용 구하기
        return findMaxProb(n, start_node, end_node);
    }
    
    class Edge {
        public int node;
        public double prob;
        
        public Edge(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    
    //그래프화
    void makeGraph(int[][] edges, double[] succProb) {
        graph = new HashMap<>();
        
        //무방향그래프이므로 서로 연결된 노드 양쪽에 관계 추가
        for(int i=0; i<edges.length; i++) {
            addConnection(edges[i][0], edges[i][1], succProb[i]);
            addConnection(edges[i][1], edges[i][0], succProb[i]);
        }
    }
    
    //연결관계 추가
    void addConnection(int start, int end, double prob) {
        List<Edge> list = graph.getOrDefault(start, new ArrayList<>());
        list.add(new Edge(end, prob));
        graph.put(start, new ArrayList<>(list));
    }
    
    //다익스트라
    double findMaxProb(int n, int start, int end) {
        //ready: set -INF, create heap
        double[] probs = new double[n];
        Arrays.fill(probs, Double.MIN_VALUE);
        
        //max heap: 힙은 가중치가 큰 노드를 우선
        Queue<Entry> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o2.prob, o1.prob));
        
        //set start
        probs[start] = 1.0; //출발 지점 -> 출발 지점 prob은 1.0
        pq.add(new Entry(start, 1.0));
        
        //while loop
        while(!pq.isEmpty()) {
            //cur
            Entry cur = pq.poll();
            //이미 방문한 노드(저장된 가중치가 현재 들고있는 가중치보다 작다면 무시)
            if(probs[cur.to] < cur.prob || graph.get(cur.to) == null) continue;
            
            //near
            for(Edge nxt:graph.get(cur.to)) {
                double newProb = probs[cur.to] * nxt.prob; //새로 계산된 인접 노드로 가는 가중치
                
                if(probs[nxt.node] >= newProb) continue; //새로 계산된 가중치보다 이미 저장된 가중치가 크다면 무시
                //아니라면 가중치 새로 저장하고 힙에 예약
                probs[nxt.node] = newProb;
                pq.add(new Entry(nxt.node, newProb));
            }
        }
        return probs[end];
    }
    
    class Entry implements Comparable<Entry>{
        public int to;
        public double prob;
        
        public Entry(int to, double prob) {
            this.to = to;
            this.prob = prob;
        }
        
        @Override
        public int compareTo(Entry o) {
            return Double.compare(o.prob, this.prob);
        }
    }
}