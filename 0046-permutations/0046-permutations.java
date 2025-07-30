import java.util.List; import java.util.ArrayList;

//주어진 숫자 배열로 만들 수 있는 모든 순열 (중복X, 순서의미있음)
class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    boolean[] visited;
    int n;

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        visited = new boolean[n];

        backtrack(nums, new ArrayList<>());

        return answer;
    }

    private void backtrack(int[] nums, List<Integer> list) {
        if(list.size() == n) {
            answer.add(new ArrayList<>(list));
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            list.add(nums[i]);

            backtrack(nums, list);

            visited[i] = false;
            list.remove(Integer.valueOf(nums[i]));
        }
    }
}