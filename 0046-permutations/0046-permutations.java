import java.util.List; import java.util.ArrayList;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    boolean[] visited;
    int n;

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        visited = new boolean[n];
        dfs(nums, new ArrayList<>());
        return answer;
    }

    void dfs(int[] nums, List<Integer> list) {
        //basecase: 크기 같으면 중지
        if(list.size() == n) {
            answer.add(new ArrayList<>(list)); 
            return;
        }

        for(int curr=0; curr<n; curr++) {
            if(visited[curr]) continue;

            //현재 원소 추가
            list.add(nums[curr]);
            visited[curr] = true;
            //다음 원소 탐색
            dfs(nums, list);
            //복구
            list.remove(Integer.valueOf(nums[curr]));
            visited[curr] = false;
        }
        
    }
}