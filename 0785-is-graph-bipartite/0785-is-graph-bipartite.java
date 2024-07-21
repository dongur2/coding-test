import java.util.*;

class Solution {
    int[] sets;

    public boolean isBipartite(int[][] graph) {
        sets = new int[graph.length];
        Arrays.fill(sets, -1); //세트 기본값 -1로 초기화

        return isDividedIntoTwo(graph);  
    }

    //BFS
    private boolean isDividedIntoTwo(int[][] graph) {
        Queue<Integer> q = new ArrayDeque<>();
        sets[0] = 0; //시작 노드의 세트를 0으로 지정 후 시작

        for(int curVertex=0; curVertex<graph.length; curVertex++) {
            q.offer(curVertex); //시작 노드 대기열 추가

            while(!q.isEmpty()) { //대기열 소진까지 반복
                curVertex = q.poll(); //시작 노드 방문

                for(int nextVertex:graph[curVertex]) { //시작 노드의 인접노드들을 탐색
                    if(sets[nextVertex] == -1) { //세트 지정이 안된 노드(방문X)라면 시작 노드의 세트와 다른 세트에 포함하고 방문 예약
                        int type = sets[curVertex] == 0 ? 1 : 0;
                        q.offer(nextVertex); sets[nextVertex] = type;
                    } else { //세트 지정이 된 노드라면 시작 노드와 같은 세트에 포함되는지 확인: 같은 세트라면 즉시 false 
                        if(sets[nextVertex] == sets[curVertex]) return false;
                    }
                }
            }
        }

        return true; //순회가 끝날 때까지 같은 세트에 포함된 인접 노드를 찾지 못했다면 true 
    }
}