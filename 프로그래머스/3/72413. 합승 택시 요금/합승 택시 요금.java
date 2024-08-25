import java.util.*;

/*
Q. s에서 출발해서 각 도착 지점까지 택시를 타고 갔을 때, 최저 예상 택시요금을 반환
- s에서 출발하는 경우의 최소 비용: 하나씩 뒤로 밀어가면서 합승 종료 지점 찾기 - 0인 경우 합승 하지 않는 경우가 됨
- a까지의 최소 비용: 합승 종료 지점 -> a까지의 비용
- b까지의 최소 비용: 합승 종료 지점 -> b까지의 비용

fares[i] = [출발지점, 타겟지점, 비용]
*/
class Solution {
    Map<Integer, List<Edge>> graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        makeGraph(fares);
        return countMinimum(n, s, a, b);
    }
    
    class Edge {
        public int node; public int cost;
        public Edge(int node, int cost) {
            this.node = node; this.cost = cost;
        }
    }
    
    void makeGraph(int[][] fares) {
        graph = new HashMap<>();
        
        for(int[] fare:fares) {
            addConnection(fare[0], fare[1], fare[2]);
            addConnection(fare[1], fare[0], fare[2]);
        }
    }
    
    void addConnection(int start, int end, int cost) {
        List<Edge> list = graph.getOrDefault(start, new ArrayList<>());
        list.add(new Edge(end, cost));
        graph.put(start, new ArrayList<>(list));
    }
    
    int countMinimum(int n, int s, int a, int b) {
        int[] costs = countCosts(n, s);
        int[] costsForA = countCosts(n, a);
        int[] costsForB = countCosts(n, b);
        
        return findMin(costs, costsForA, costsForB);
    }
    
    int[] countCosts(int n, int start) {
        //setting: costs to INF
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        //set start
        Queue<Entry> pq = new PriorityQueue<>();
        costs[start] = 0; 
        pq.add(new Entry(start, 0));
        
        //loop
        while(!pq.isEmpty()) {
            //cur
            Entry cur = pq.poll();
            if(costs[cur.to] > cur.cost || graph.get(cur.to) == null) continue;
            
            //near
            for(Edge nxt:graph.get(cur.to)) {
                int newCost = costs[cur.to] + nxt.cost;
                if(newCost < costs[nxt.node]) {
                    costs[nxt.node] = newCost;
                    pq.add(new Entry(nxt.node, newCost));
                }
            }
        }
        return costs;
    }
    
    class Entry implements Comparable<Entry>{
        public int to; public int cost;
        public Entry(int to, int cost) {
            this.to = to; this.cost = cost;
        }
        
        @Override
        public int compareTo(Entry o) {
            return this.cost - o.cost;
        }
    }
    
    int findMin(int[] costs, int[] costsForA, int[] costsForB) {
        int answer = Integer.MAX_VALUE;
        
        for(int i=1; i<costs.length; i++) {
            int temp = costs[i] + costsForA[i] + costsForB[i];
            answer = Math.min(answer, temp);
        }
        
        return answer;
    }
}