import java.util.List; import java.util.ArrayList;
import java.util.Map; import java.util.HashMap;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        Map<Integer, List<Integer>> forest = new HashMap<>();
        makeForest(forest, edges);
        
        //루트노드 차례로 회전
        for(int root : nodes) {
            int children = countChildren(forest, root, 0, root);
            
            //루트노드가 홀/짝 노드일 경우 -> 계속해서 홀짝인지 확인
            if(isEven(root) == isEven(children)) {
                if(checkOE(forest, root, root)) answer[0]++;
            }
            
            //루트노드가 역홀/역짝 노드일 경우 -> 계속해서 역홀/역짝인지 확인
            else {
                if(checkReversedOE(forest, root, root)) answer[1]++;
            }
        }
        
        return answer;
    }
    
    void makeForest(Map<Integer, List<Integer>> forest, int[][] edges) {
        //무방향 그래프
        for(int[] edge : edges) {
            forest.computeIfAbsent(edge[0], k->new ArrayList<>()).add(edge[1]);
            forest.computeIfAbsent(edge[1], k->new ArrayList<>()).add(edge[0]);
        }
    }
    
    boolean isEven(int num) { return num % 2 == 0; }
    
    int countChildren(Map<Integer, List<Integer>> forest, int curr, int cnt, int prev) {
        if(!forest.containsKey(curr)) return 0; //자식 노드가 없으면 0 리턴
        
        for(int next : forest.get(curr)) {
            if(next == prev) continue;
            cnt++; //직접 연결된 노드만 자식 노드임!!
        }
        
        return cnt;
    }
    
    boolean checkOE(Map<Integer, List<Integer>> forest, int curr, int prev) {
        int children = countChildren(forest, curr, 0, prev);
        
        //자식 노드가 없으면 현재 노드 번호 짝/홀 확인 후 즉시 리턴 
        if(children == 0) return isEven(curr);
        
        //홀수 노드이거나 짝수 노드면 다음 자식 노드로 이동
        if(isEven(curr) == isEven(children)) {
            for(int next : forest.get(curr)) {
                if(next == prev) continue;
                if(!checkOE(forest, next, curr)) return false;
            }
        } else return false; //홀/짝노드가 아니면 실패 
        
        return true;
    }
    
    boolean checkReversedOE(Map<Integer, List<Integer>> forest, int curr, int prev) {
        int children = countChildren(forest, curr, 0, prev);
        
        //자식 노드가 없으면 현재 노드 번호 짝/홀 확인 후 즉시 리턴 
        if(children == 0) return !isEven(curr);
        
        //역홀수 노드이거나 역짝수 노드면 다음 자식 노드로 이동
        if(isEven(curr) != isEven(children)) {
            for(int next : forest.get(curr)) {
                if(next == prev) continue;
                if(!checkReversedOE(forest, next, curr)) return false;
            }
        } else return false; //역홀/짝노드가 아니면 실패
        
        return true;
    }
}