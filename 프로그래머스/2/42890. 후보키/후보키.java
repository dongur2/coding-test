/*
    후보키 (1)모든 튜플에 대해 유일하게 식별되는 속성 (2)유일하게 식별하는 데 꼭 필요한 속성으로만 구성
    > 후보 키의 개수 리턴 
    
    *1 <= relation[0].length <= 8 튜플 개수 
    *1 <= relation.length <= 20 속성 개수 
*/
import java.util.List; import java.util.ArrayList;
import java.util.Set; import java.util.HashSet; import java.util.stream.Collectors;
class Solution {
    List<List<Integer>> comb = new ArrayList<>();
    
    public int solution(String[][] relation) {
        
        //모든 속성 인덱스 조합 
        makeAllCombinations(relation, 0, new ArrayList<>()); 
        
        //유일성 확인
        filterUniqueAttributeCombinations(relation);
        
        //조합 크기 오름차순 정렬
        comb.sort((a,b) -> a.size() - b.size());
        
        //최소성 확인
        filterMinimumCombinations();
        
        return comb.size();
    }
    

    void makeAllCombinations(String[][] relation, int idx, List<Integer> list) {
        //인덱스가 범위를 넘어서면 중지 
        if(idx == relation[0].length) return;
        
        for(int i=idx; i<relation[0].length; i++) {
            list.add(i);
            comb.add(new ArrayList<>(list));
            makeAllCombinations(relation, i+1, list);
            list.remove(list.size()-1); //복구
        }
    }
    
    void filterUniqueAttributeCombinations(String[][] relation) {
        comb = comb.stream().filter(c -> isUnique(relation, c)).collect(Collectors.toList());
    }

    //유일성 만족 확인
    boolean isUnique(String[][] relation, List<Integer> list) {
        Set<String> tuples = new HashSet<>();
        
        for(String[] r:relation) {
            StringBuilder data = new StringBuilder();
            for(int idx:list) {
                data.append(r[idx]);
            }     
            
            if(tuples.contains(data.toString())) return false;
            else tuples.add(data.toString());
        }
        
        return tuples.size() == relation.length;
    }
    
    void filterMinimumCombinations() {
        comb = comb.stream().filter(c -> isMinimum(c)).collect(Collectors.toList());
    }
    
    //최소성 유지 
    boolean isMinimum(List<Integer> list) {
        for (List<Integer> c : comb) {
            if (list.containsAll(c) && !list.equals(c)) return false;
        }
        return true;
    }

}