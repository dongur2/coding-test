import java.util.*;

//최대 양 수
class Solution {
    int answer = Integer.MIN_VALUE;
    Map<Integer, List<Integer>> map = new HashMap<>();
    
    public int solution(int[] info, int[][] edges) {
        //연결 노드 맵 
        for(int[] edge:edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }
        
        dfs(info, 0, 1, 0, new ArrayList<>(map.get(0))); //루트노드
        
        return answer;
    }
    
    void dfs(int[] info, int curr, int sheep, int wolf, List<Integer> nextNodes) {
        answer = Math.max(answer, sheep);
        
        //다음 이동 노드 목록 탐색
        //recursive: 다음 노드의 양/늑대 입양 - 늑대 < 양일 경우에만 이동 
        for(int i=0; i<nextNodes.size(); i++) {
            int newSheep = sheep, newWolf = wolf;
            
            int nxt = nextNodes.get(i); //다음 노드
            
            if(info[nxt] == 0) newSheep++;
            else newWolf++;
            
            //basecase: 늑대 >= 양: 이동하지 않음
            if(newWolf >= newSheep) continue;
            
            //늑대 < 양: 이동 - 이동할 노드의 자식 노드 추가
            List<Integer> newNextNodes = new ArrayList<>(nextNodes);
            newNextNodes.remove(i);
            if(map.containsKey(nxt)) newNextNodes.addAll(map.get(nxt));
            
            //이동
            dfs(info, nxt, newSheep, newWolf, newNextNodes);
        }
    }
}