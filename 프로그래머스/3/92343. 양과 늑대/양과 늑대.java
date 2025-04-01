import java.util.*;

//최대 양 수
class Solution {
    static int answer = Integer.MIN_VALUE;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    
    
    public int solution(int[] info, int[][] edges) {
        //노드:[자식노드]
        for(int[] e:edges) {
            map.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        
        dfs(info, 1, 0, new ArrayList<>(map.get(0)));
        
        return answer;
    }
    
    public void dfs(int[] info, int sheep, int wolf, List<Integer> nextNodes) {
        //양 수 업데이트
        answer = Math.max(answer, sheep);
        
        //자식 노드 탐색
        for(int i=0; i<nextNodes.size(); i++) {
            int newSheep = sheep, newWolf = wolf;
            
            //다음 노드
            int nxt = nextNodes.get(i);
            
            if(info[nxt] == 0) newSheep++;
            else newWolf++;
            
            //늑대 >= 양이면 방문하지 않음
            if(newWolf >= newSheep) continue;
            
            //늑대 < 양이면 방문 후 다음 노드 추가
            List<Integer> newNxt = new ArrayList<>(nextNodes);
            newNxt.remove(i); //현재 노드는 삭제
            
            if(map.containsKey(nxt)) newNxt.addAll(map.get(nxt)); //다음 노드의 자식 노드 추가
            
            //방문
            dfs(info, newSheep, newWolf, newNxt);
        }
    }
    
}