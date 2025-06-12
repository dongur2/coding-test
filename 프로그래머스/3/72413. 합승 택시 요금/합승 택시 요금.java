import java.util.*;

//최저 택시요금
class Solution {
    static Map<Integer, List<Edge>> map = new HashMap<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //무방향 그래프
        for(int[] f:fares) {
            map.computeIfAbsent(f[0], k->new ArrayList<>()).add(new Edge(f[1], f[2]));
            map.computeIfAbsent(f[1], k->new ArrayList<>()).add(new Edge(f[0], f[2]));
        }
        
        //출발지에서 모든 노드에 대한 최소 요금 
        int[] fromS = getMinCostFrom(s, n);
        
        //모든 노드에서 a로 가는 최소 요금
        int[] toA = getMinCostFrom(a, n);
        //모든 노드에서 b로 가는 최소 요금
        int[] toB = getMinCostFrom(b, n);
        
        //[따로] 출발지 -> 각각 도착지 요금의 합
        int separated = fromS[a] + fromS[b];
        
        int answer = separated; //정답 (최저요금)
        
        //[합승] 출발지 -> 합승종료 -> 각각 도착지 요금의 합
        //모든 지점에서 각각 합승종료하는 경우를 확인
        for(int i=1; i<=n; i++) {
            int carpool = fromS[i] + toA[i] + toB[i]; //카풀했을 때 최저요금
            answer = Math.min(answer, carpool); //따로 가는 요금 > 합승 요금일 경우에만 합승, 더 낮은 요금 루트로 합승
        }
        
        return answer;
    }
    
    public static int[] getMinCostFrom(int start, int n) {
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(start, 0));
        costs[start] = 0;
        
        while(!q.isEmpty()) {
            Edge curr = q.poll();
            
            if(map.get(curr.to) == null) continue;
            for(Edge nxt:map.get(curr.to)) {
                int newCost = curr.cost + nxt.cost;
                
                if(costs[nxt.to] > newCost) {
                    costs[nxt.to] = newCost;
                    q.offer(new Edge(nxt.to, newCost));
                }
            }
        }
        
        return costs;
    }
    
    private static class Edge implements Comparable<Edge> {
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