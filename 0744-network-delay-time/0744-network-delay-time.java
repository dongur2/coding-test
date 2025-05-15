import java.util.*;
import java.util.stream.*;
//n개 노드(1번 ~ n번)
//[출발 노드, 도착 노드, 가중치]
//k번 노드에서 신호 전송 -> 모든 n개 노드에 신호가 도착하는 데 필요한 최소 시간 리턴 (불가하면 -1)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for(int[] time:times) {
            graph.computeIfAbsent(time[0], key->new ArrayList<>()).add(new Edge(time[1], time[2]));
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        check(n, k, graph, dist);

        int answer = Arrays.stream(dist).max().getAsInt();
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void check(int n, int k, Map<Integer, List<Edge>> graph, int[] dist) {
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(k, 0));
        dist[k-1] = 0;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            int curNode = curr.num;
            int curDist = curr.dist;

            if(graph.get(curNode) != null) {
                for(Edge edge:graph.get(curNode)) {
                    int nxtNode = edge.to;
                    int nxtDist = curDist + edge.dist;

                    if(dist[nxtNode-1] > nxtDist) {
                        dist[nxtNode-1] = nxtDist;
                        q.offer(new Node(nxtNode, nxtDist));
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node>{
        int num, dist;

        public Node(int n, int d) {
            this.num=n; this.dist=d;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    private static class Edge {
        int to, dist;

        public Edge(int t, int d) {
            this.to=t; this.dist=d;
        }
    }
}