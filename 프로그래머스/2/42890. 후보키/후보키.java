import java.util.Set; import java.util.HashSet;
import java.util.List; import java.util.ArrayList;
class Solution {
    Set<List<Integer>> set = new HashSet<>(); 
    public int solution(String[][] relation) {
        for(int l=1; l<=relation[0].length; l++) {
            getCandidate(relation, l, new ArrayList<>(), 0);
        }
        return set.size();
    }
    
    void getCandidate(String[][] relation, int l, List<Integer> comb, int curr) {
        //길이만족 - 유일성,최소성확인
        if(comb.size() == l) {
            if(isUnique(relation, comb) && isMinimum(comb)) set.add(new ArrayList<>(comb));
            return;
        }
        
        for(int i=curr; i<relation[0].length; i++) {
            comb.add(i);
            getCandidate(relation, l, comb, i+1);
            comb.remove(Integer.valueOf(i));
        }
    }
    
    boolean isUnique(String[][] relation, List<Integer> comb) {
        Set<String> tuples = new HashSet<>();
        
        for(String[] r:relation) {
            StringBuilder t = new StringBuilder();
            comb.forEach(c -> t.append(r[c]));
            if(tuples.contains(t.toString())) return false;
            else tuples.add(t.toString());
        }
        
        return tuples.size() == relation.length;
    }
    
    boolean isMinimum(List<Integer> comb) {
        Set<Integer> t = new HashSet<>(comb);
        for(List<Integer> s:set) {
            if(t.containsAll(new HashSet<>(s))) return false;
        }
        return true;
    }
}