import java.util.*;

//'최저 택시 요금' 리턴 
//[합승X 요금 > 합승O 요금]일 경우에만 합승
class Solution {
    static Map<Integer, List<Edge>> map = new HashMap<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //무방향 그래프 
        for(int[] f:fares) {
            map.computeIfAbsent(f[0], k->new ArrayList<>()).add(new Edge(f[1], f[2]));
            map.computeIfAbsent(f[1], k->new ArrayList<>()).add(new Edge(f[0], f[2]));
        }
        
        //최초 출발지 -> 모든 노드에 대한 최저 요금
        int[] fromS = getMinCosts(s, n);
        
        //합승X 요금: 각자 목적지까지 따로 택시 탑승 
        int separated = fromS[a] + fromS[b]; 
        
        //최저 합승 요금
        int[] fromA = getMinCosts(a, n);
        int[] fromB = getMinCosts(b, n);

        int answer = separated;
        
        //합승 종료 지점 확인
        for(int i=1; i<=n; i++) {
            int carpool = fromS[i] + fromA[i] + fromB[i];
            answer = Math.min(answer, carpool);
        }
        
        return answer;
    }
    
    public static int[] getMinCosts(int start, int n) {
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(start, 0));
        costs[start] = 0;
        
        while(!q.isEmpty()) {
            Edge curr = q.poll();
            
            for(Edge nxt:map.getOrDefault(curr.to, Collections.emptyList())) {
                int newCost = curr.cost + nxt.cost;
                
                if(costs[nxt.to] > newCost) {
                    costs[nxt.to] = newCost;
                    q.offer(new Edge(nxt.to, newCost));
                }
            }
        }
        
        return costs;
    }
                
    public static class Edge implements Comparable<Edge> {
        int to, cost;
        
        public Edge(int to, int cost) {
            this.to=to; this.cost=cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        } 
    }
}