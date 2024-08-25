import java.util.*;

class Solution {
    /*
     Q. k번 노드에서 출발하여 모든 n번 노드를 통과할 수 있는 최소 거리를 반환
     - n번 노드에 갈 수 없으면 -1 반환
     times[i] = [출발 노드, 타겟 노드, 가중치]
     */

    Map<Integer, List<Edge>> graph;
    final int INF = Integer.MAX_VALUE;

    public int networkDelayTime(int[][] times, int n, int k) {
        makeGraph(times);
        return countMinimum(n, k);
    }

    class Edge {
        public int node; public int time;
        public Edge(int node, int time) {
            this.node = node; this.time = time;
        }
    }

    void makeGraph(int[][] times) {
        graph = new HashMap<>();

        for(int[] time:times) {
            List<Edge> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new Edge(time[1], time[2]));
            graph.put(time[0], list);
        }
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

    int countMinimum(int n, int k) {
        //ready: set INF
        int[] costs = new int[n+1];
        Arrays.fill(costs, INF);

        //make heap: priority queue - min heap
        Queue<Entry> pq = new PriorityQueue<>();

        //set start point
        costs[k] = 0;
        pq.add(new Entry(k, 0));

        //while loop
        while(!pq.isEmpty()) {
            //cur
            Entry cur = pq.poll();
            if(costs[cur.to] < cur.cost || graph.get(cur.to) == null) continue; //저장된 타겟노드로의 비용이 지금 들고있는 비용이 크다면 무시(이미 방문) 또는 인접 노드가 없으면 무시

            //next
            for(Edge nxt:graph.get(cur.to)) {
                int newCost = costs[cur.to] + nxt.time;
                if(costs[nxt.node] > newCost) {
                    costs[nxt.node] = newCost;
                    pq.add(new Entry(nxt.node, newCost));
                } //인접노드에 방문한 적 없으면 비용 업데이트 & 예약 - 최소
            }
        }
        return Arrays.stream(costs).filter(c -> c == INF).count() == 1L ? Arrays.stream(costs).skip(1).max().getAsInt() : -1;
    }
}