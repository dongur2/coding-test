/*
    주어진 숫자 배열 nums에서 모든 0을 배열 끝으로 이동
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int[] res = new int[nums.length];

        int idx = 0; //배열 숫자 저장에 필요한 인덱스 
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) continue; //0이면 무시 (기본값 0)
            res[idx++] = nums[i];
        }

        for(int i=0; i<nums.length; i++) {
            nums[i] = res[i];
        }
    }
}