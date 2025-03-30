import java.util.*;

//모을 수 있는 최대 양 수
//양 <= 늑대: 양 0
class Solution {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int answer = Integer.MIN_VALUE;
    
    public int solution(int[] info, int[][] edges) {
        //{노드번호 : [연결된 노드 번호 목록]}
        for(int[] edge:edges) {
            map.computeIfAbsent(edge[0], m -> new ArrayList<>()).add(edge[1]);
        }
        
        //루트에서 시작
        dfs(info, 0, 0, new ArrayList<>(List.of(0)));
            
        return answer;
    }
    
    void dfs(int[] info, int sheep, int wolf, List<Integer> nextNodes) {
        answer = Math.max(answer, sheep);
        
        //자식 노드 탐색
        for (int i=0; i<nextNodes.size(); i++) {
            int curr = nextNodes.get(i);
            int newSheep = sheep;
            int newWolf = wolf;

            if (info[curr] == 0) newSheep++;
            else newWolf++;

            if (newWolf >= newSheep) continue; // 늑대 >= 양: 무시

            List<Integer> newNext = new ArrayList<>(nextNodes);
            newNext.remove(i); // 현재 노드 제외
            
            if (map.containsKey(curr)) newNext.addAll(map.get(curr)); // 다음 방문 리스트에 자식 노드 목록 추가

            dfs(info, newSheep, newWolf, newNext);
        }
    }
}