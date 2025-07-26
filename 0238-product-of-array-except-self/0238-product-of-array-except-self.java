/*
    숫자 배열 nums가 주어질 때, nums[i]를 제외한 모든 nums의 원소들의 곱이 되는 answer[i]
    O(N)
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        Arrays.fill(res, 1);

        int curr = 1;

        //앞에서부터 곱하기
        for(int i=0; i<n; i++) {
            res[i] *= curr;
            curr *= nums[i];
        }

        curr = 1; //초기화

        //뒤에서부터 곱하기
        for(int i=n-1; i>=0; i--) {
            res[i] *= curr;
            curr *= nums[i];
        }

        return res;
    }
}