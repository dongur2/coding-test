import java.util.*;

//휴식 없이 이동하는 최장 거리가 가장 작은 등산코스 [봉우리번호, 휴식X이동거리]
class Solution {
    static Map<Integer, List<Edge>> map = new HashMap<>();
    static Set<Integer> summitSet = new HashSet<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        //무방향 그래프 
        makeMap(paths);
        
        //출입구 -> 산봉우리(1) -> 출입구
        for(int s:summits) {
            summitSet.add(s);
        }
        
        //낮은 산봉우리 우선
        Arrays.sort(summits);
        
        int[] intensities = getLongestIntensity(n, gates); 
        
        int[] answer = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for(int s:summits) {
            if(answer[1] > intensities[s]) {
                answer[0] = s; answer[1] = intensities[s];
            }
        }
        return answer;
    }
    
    public static void makeMap(int[][] paths) {
        for(int[] p:paths) {
            map.computeIfAbsent(p[0], k->new ArrayList<>()).add(new Edge(p[1], p[2]));
            map.computeIfAbsent(p[1], k->new ArrayList<>()).add(new Edge(p[0], p[2]));
        }
    }
    
    public static int[] getLongestIntensity(int n, int[] gates) {
        int[] result = new int[n+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        
        Queue<Edge> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        
        for(int gate:gates) {
            q.offer(new Edge(gate, 0));
            result[gate] = 0;
        }
        
        while(!q.isEmpty()) {
            Edge curr = q.poll();

            //산봉우리에 도착했으면 더이상 길찾기 중지
            if(summitSet.contains(curr.to)) continue;
            
            //이미 최소 초과 - 중지
            if(curr.dist > result[curr.to]) continue;
            
            for(Edge nxt:map.getOrDefault(curr.to, Collections.emptyList())) {
                int newIntensity = Math.max(curr.dist, nxt.dist);
                
                if(result[nxt.to] > newIntensity) {
                    result[nxt.to] = newIntensity;
                    q.offer(new Edge(nxt.to, newIntensity));
                }
            }
        }
        
        return result;
    }
    
    public static class Edge {
        int to, dist;
        
        public Edge(int to, int dist) {
            this.to=to; this.dist=dist;
        }
    }
}