import java.util.*;

//모을 수 있는 최대 양 수
class Solution {
    static int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        //부모 노드:[자식 노드]
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge:edges) {
            graph.computeIfAbsent(edge[0], k->new ArrayList<>()).add(edge[1]);
        }
        
        //탐색
        dfs(info, graph, 0, 1, 0, graph.get(0));
        
        return answer;
    }
    
    public void dfs(int[] info, Map<Integer, List<Integer>> graph, int node, int sheep, int wolf, List<Integer> nxtNodes) {
        //현재 가지고 있는 양의 수와 최대 수 비교 후 업데이트
        answer = Math.max(sheep, answer);
        
        //자식 노드 탐색
        for(int nxt:nxtNodes) {
            int newSheep = sheep, newWolf = wolf;
            
            //다음 노드가 양/늑대
            if(info[nxt] == 0) newSheep++;
            else newWolf++;
            
            //양 <= 늑대면 방문하지 않음
            if(newSheep <= newWolf) continue;
            
            //양 > 늑대면 방문
            else {
                List<Integer> newNxtNodes = new ArrayList<>(nxtNodes);
                newNxtNodes.remove(Integer.valueOf(nxt));
                if(graph.containsKey(nxt)) newNxtNodes.addAll(graph.get(nxt));
                dfs(info, graph, nxt, newSheep, newWolf, newNxtNodes);
            }
        }
    }
}