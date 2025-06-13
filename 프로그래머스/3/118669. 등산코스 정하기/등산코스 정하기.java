import java.util.*;

//[산봉우리 번호, intensity 최솟값]
//"휴식없이 이동해야 하는 최장 시간 == intensity"의 최솟값 구하기
//최솟값 등산코스가 여러 개일 경우 "산봉우리 번호가 가장 낮은" 코스
class Solution {
    static Map<Integer, List<Edge>> map = new HashMap<>();
    static Set<Integer> summitSet = new HashSet<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        //무방향 그래프
        for(int[] path:paths) {
            map.computeIfAbsent(path[0], k->new ArrayList<>()).add(new Edge(path[1], path[2]));
            map.computeIfAbsent(path[1], k->new ArrayList<>()).add(new Edge(path[0], path[2]));
        }
        
        for(int s:summits) { summitSet.add(s); }
        
        //등산코스 규칙: 출입구 -> 산봉우리(1) -> 원래 출입구
        int[] minIntensity = getMinIntensity(n, gates); //출입구로부터 모든 노드에 대한 최소 intensity 구하기
        
        
        int[] answer = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        Arrays.sort(summits); //낮은 번호 우선
        for(int s:summits) {
            if(minIntensity[s] < answer[1]) {
                answer[1] = minIntensity[s];
                answer[0] = s;
            }
        }
        return answer;
    }
    
    private static int[] getMinIntensity(int n, int[] gates) {
        int[] minIntensity = new int[n+1];
        Arrays.fill(minIntensity, Integer.MAX_VALUE);
        
        Queue<Edge> q = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        
        //모든 게이트를 출발지로 추가
        for(int gate:gates) {
            q.offer(new Edge(gate, 0));
            minIntensity[gate] = 0;
        }
        
        while(!q.isEmpty()) {
            Edge curr = q.poll();
            int from = curr.to;
            int intensity = curr.weight;
            
            //이미 최소 intensity를 계산할 수 없으면 무시 
            if (intensity > minIntensity[from]) continue;
            //봉우리에 도착했으면 경로 중지
            if(summitSet.contains(from)) continue; 
            
            for(Edge nxt:map.get(curr.to)) {                
                //더 작은 intensity로
                int newIntensity = Math.max(intensity, nxt.weight); //쉬지 못하는 '최장' 시간
                if(minIntensity[nxt.to] > newIntensity) {
                    minIntensity[nxt.to] = newIntensity;
                    q.offer(new Edge(nxt.to, newIntensity));
                }
            }
        }
        
        return minIntensity;
    }
    
    private static class Edge {
        int to, weight;
        
        public Edge(int to, int weight) {
            this.to=to; this.weight=weight;
        }
    }
}