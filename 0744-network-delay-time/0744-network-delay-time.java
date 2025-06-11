import java.util.*;

/*
Q. k번 노드에서 신호를 보냈을 때, 모든 노드에 신호가 도착하기까지 필요한 '최소 시간'
- 모든 노드에 도달할 수 없으면 -1
- 1번 ~ n번 노드   
*/
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //times[i] = [출발, 도착, 소요 시간]
        
        //연결된 인접노드 그래프 
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int[] t:times) {
            int from = t[0], to = t[1], time = t[2];
            map.computeIfAbsent(from, key->new ArrayList<>()).add(new Edge(to, time));
        }

        //각 노드에 대한 최소 시간 배열 
        int[] res = new int[n+1];
        Arrays.fill(res, Integer.MAX_VALUE);

        Queue<Edge> pq = new PriorityQueue<>();

        //k번 노드에서 신호 발신 
        pq.offer(new Edge(k, 0));
        res[k] = 0;

        while(!pq.isEmpty()) {
            Edge curr = pq.poll();
            int curLoc = curr.to;
            int curTime = curr.time;

            if(map.get(curLoc) == null) continue;
            for(Edge nxt:map.get(curLoc)) {
                int nxtLoc = nxt.to;
                int nxtTime = curTime + nxt.time;

                if(res[nxtLoc] > nxtTime) {
                    res[nxtLoc] = nxtTime;
                    pq.offer(new Edge(nxtLoc, nxtTime));
                }
            }
        }

        //도달못한 노드가 있으면 -1 
        for(int i=1; i<=n; i++) {
            if(res[i] == Integer.MAX_VALUE) return -1;
        }

        //모든 노드에 도달했으면 최소 시간 
        Arrays.sort(res);
        return res[n-1];
    }

    private class Edge implements Comparable<Edge> {
        int to, time;

        public Edge(int to, int time) {
            this.to=to; this.time=time;
        }

        @Override
        public int compareTo(Edge o) {
            return this.time - o.time;
        }
    }
}