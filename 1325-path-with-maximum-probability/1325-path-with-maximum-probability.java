import java.util.*;

/* 
Q. 시작 노드에서 출발하여 도착 노드에 도착하는 '최대 거리' (불가능하면 0)
- 0번 ~ (n-1)번 노드 
- 무방향 그래프
*/
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        //인접노드 그래프 
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            map.computeIfAbsent(edges[i][0], key->new ArrayList<>()).add(new Edge(edges[i][1], succProb[i]));
            map.computeIfAbsent(edges[i][1], key->new ArrayList<>()).add(new Edge(edges[i][0], succProb[i]));
        }

        //각 노드까지의 최대 거리 
        double[] dists = new double[n];
        Arrays.fill(dists, Double.MIN_VALUE);

        //start_node에서 출발 
        Queue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(start_node, 1));
        dists[start_node] = 1;

        while(!q.isEmpty()) {
            Edge curr = q.poll();
            int curLoc = curr.to;
            double curDist = curr.dist;
            
            if(curLoc == end_node) break;

            if(map.get(curLoc) == null) continue;
            for(Edge nxt:map.get(curLoc)) {
                double newDist = curDist * nxt.dist;

                if(dists[nxt.to] < newDist) {
                    dists[nxt.to] = newDist;
                    q.offer(new Edge(nxt.to, newDist));
                }
            }
        }

        return dists[end_node] == Double.MIN_VALUE ? 0 : dists[end_node];
    }

    private class Edge implements Comparable<Edge> {
        int to; double dist;

        public Edge(int to, double dist){
            this.to=to; this.dist=dist;
        }

        @Override
        public int compareTo(Edge o){
            return Double.compare(o.dist, this.dist);
        }
    }
}