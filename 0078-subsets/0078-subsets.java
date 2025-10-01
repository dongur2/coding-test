import java.util.List; import java.util.ArrayList;
class Solution {
    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        for(int l=0; l<=nums.length; l++) {
            dfs(nums, l, 0, new ArrayList<>());
        }
        return answer;
    }

    void dfs(int[] nums, int len, int idx, List<Integer> list) {
        //basecase:크기만족-중지
        if(list.size()==len) {
            answer.add(new ArrayList<>(list));
            return;
        }

        for(int curr=idx; curr<nums.length; curr++) {
            list.add(nums[curr]);
            dfs(nums, len, curr+1, list);
            list.remove(list.size()-1);
        }
    }
}