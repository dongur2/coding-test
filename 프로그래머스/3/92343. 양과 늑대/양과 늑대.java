import java.util.*;

//모을 수 있는 최대 양 마리수
class Solution {
    static int answer = 0;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    
    public int solution(int[] info, int[][] edges) {
        makeGraph(edges); //연결관계    
        dfs(info, new ArrayList<>(map.get(0)), 1, 0); //루트 노드에서 시작
        return answer;
    }
    
    public void makeGraph(int[][] edges) {
        for(int i=0; i<edges.length; i++) {
            int[] edge = edges[i];
            map.computeIfAbsent(edge[0], k->new ArrayList<>()).add(edge[1]);
        }
    }
    
    public void dfs(int[] info, List<Integer> nxtNodes, int s, int w) {
        //최대 양 수 
        answer= Math.max(answer, s);
        
        //자식 노드 탐색
        for(int i=0; i<nxtNodes.size(); i++) {
            int newSheep = s, newWolf = w;
            
            //다음 노드
            int nxt = nxtNodes.get(i);
            
            //양과 늑대 카운트
            if(info[nxt] == 0) newSheep++;
            else newWolf++;
            
            //양 <= 늑대일 경우 이동하지 않음
            if(newWolf >= newSheep) continue;
            
            //양 > 늑대일 경우 이동
            //다음 노드의 자식 노드 추가
            List<Integer> newNxtNodes = new ArrayList<>(nxtNodes);
            if(map.containsKey(nxt)) newNxtNodes.addAll(map.get(nxt));
            newNxtNodes.remove(i); //현재 노드 삭제
            dfs(info, newNxtNodes, newSheep, newWolf);
        }
    }
}