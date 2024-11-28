import java.util.*;
class Solution {
    /*
    Q. 후보 키의 최대 개수 리턴
    - 후보키: 유일성(다른 튜플에 중복값이 없음) + 최소성([이름,전공]O, [이름,전공,학년]X)
    - 주어진 속성으로 만들 수 있는 모든 조합으로 후보키를 확인 -> 조합(부분집합)
    */
    public int solution(String[][] relation) {
        List<List<Integer>> keys = new ArrayList<>();
        
        //인덱스(속성)로 이루어진 모든 조합 생성
        List<List<Integer>> combs = new ArrayList<>();
        int len = relation[0].length;
        for(int i=1; i<=len; i++) {
            makeCombinations(combs, i, len, 0, new ArrayList<>(), new boolean[len]);
        }
        
        //각 인덱스조합이 후보키가 되는지 확인 후 저장
        checkIsCandidateKey(relation, keys, combs);
        
        return keys.size();
    }
    
    private void makeCombinations(List<List<Integer>> combs, int size, int maxIdx, int idx, List<Integer> list, boolean[] visited) {
        if(list.size() == size) {
            combs.add(new ArrayList<>(list));
            return;
        }
        
        for(int i=idx; i<maxIdx; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            list.add(i);
            
            makeCombinations(combs, size, maxIdx, i+1, list, visited);
            
            list.remove(Integer.valueOf(i));
            visited[i] = false;
        }
    }
    
    void checkIsCandidateKey(String[][] relation, List<List<Integer>> keys, List<List<Integer>> combs) {
        for(List<Integer> comb : combs) {
            //각 조합으로 만든 튜플이 중복값이 존재하는지 확인
            if(!isUnique(relation, comb)) continue;

            //중복되지 않고 & 이미 후보키로 저장한 조합 중에 포함되는 경우가 없으면 저장
            if(!isMinimum(keys, comb)) continue;
            
            keys.add(new ArrayList<>(comb));
        }
    }
    
    private boolean isUnique(String[][] relation, List<Integer> comb) {
        Set<String> set = new HashSet<>();
            
        for(String[] data : relation) {
            StringBuilder sb = new StringBuilder();

            for(Integer idx : comb) {
                sb.append(data[idx]);
            }

            set.add(sb.toString());
        }
        
        return set.size() == relation.length;
    }
    
    private boolean isMinimum(List<List<Integer>> keys, List<Integer> comb) {
        if(keys.isEmpty()) return true;
        
        for(List<Integer> key : keys) {
            if(comb.containsAll(key)) return false;
        }
        
        return true;
    }
    
}