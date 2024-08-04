import java.util.*;

class Solution {
    /*
    후보키: 유일성 & 최소성 만족
    - 만들 수 있는 속성 조합을 모두 만든 뒤, 유일성과 최소성 만족 여부를 차례로 검사
    -- 속성 조합: 중복 불가 && 순서에 의미 두지 않음 ==> 조합
    */
    
    boolean[] visited;
    List<Set<Integer>> list;
    
    public int solution(String[][] relation) {
        visited = new boolean[relation[0].length];
        list = new ArrayList<>();
        
        //속성 1개 선택 ~ 주어진 속성 모두 선택하는 조합 생성
        for(int len=1; len<=relation[0].length; len++) {
            backtrack(relation, len, 0, new HashSet<>());
        }
        
        return list.size();
    }
    
    void backtrack(String[][] relation, int len, int start, Set<Integer> tempKey) {
        //base case: 선택하려는 개수만큼 속성을 선택 완료했다면, 유일성 검사
        if(tempKey.size() == len) {
            //유일성 검사
            if(!isUnique(relation, tempKey)) return;
            
            //최소성 검사: 현재 인덱스 조합이 이미 저장된 후보키 조합을 포함한다면 최소성 불만족
            if(!list.isEmpty()) {
                for(Set<Integer> savedKey : list) {
                    if(tempKey.containsAll(savedKey)) return;
                }
            }
            
            list.add(new HashSet<>(tempKey)); return;
        }
        
        //recursive call: 전체 속성 인덱스 순회
        //방문한 속성은 무시, 아니라면 방문 처리 & 선택 추가 -> 그 다음 탐색 하청 -> 방문 무효 & 선택 제거
        for(int i=start; i<relation[0].length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true; tempKey.add(i);
            backtrack(relation, len, i+1, tempKey);
            visited[i] = false; tempKey.remove(i);
        }
    }
    
    //유일성 검사: 가져온 인덱스 조합을 각 튜플에 적용해 중복되는 값이 있는지 확인
    boolean isUnique(String[][] relation, Set<Integer> tempKey) {
        Set<String> tuples = new HashSet<>();
        
        for(String[] r:relation) {
            StringBuilder tuple = new StringBuilder();
            
            for(int t:tempKey) {
                tuple.append(r[t]);            
            }
            
            tuples.add(tuple.toString());
        }
        
        return tuples.size() == relation.length;
    }
}