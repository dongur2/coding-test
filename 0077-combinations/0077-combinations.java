import java.util.*;

class Solution {
    List<List<Integer>> answer;
    
    public List<List<Integer>> combine(int n, int k) {
        answer = new ArrayList<>();
        
        backtrack(n, 1, k, new ArrayList<>());
        
        return answer;
    }
    
    void backtrack(int n, int start, int k, List<Integer> list) {
        if(list.size() == k) {
            answer.add(new ArrayList<>(list)); return;
        }
        
        for(int i=start; i<=n; i++) {
            list.add(i);
            backtrack(n, i+1, k, list);
            list.remove(Integer.valueOf(i));
        }
    }
}