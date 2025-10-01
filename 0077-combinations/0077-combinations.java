import java.util.List; import java.util.ArrayList;
class Solution {
    List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new ArrayList<>());    
        return answer;
    }

    void dfs(int n, int k, int num, List<Integer> list) {
        //basecase:크기 같으면 중지
        if(list.size()==k) {
            answer.add(new ArrayList<>(list));
            return;
        }

        for(int curr=num; curr<=n; curr++) {
            list.add(curr);
            dfs(n, k, curr+1, list);
            list.remove(list.size()-1);
        }
    }
}