/*
    주어진 숫자 배열 nums에서 모든 0을 배열 끝으로 이동
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int[] res = new int[nums.length];

        int idx = 0; //배열 숫자 저장에 필요한 인덱스 
        for(int num:nums) {
            //0이 아니면 앞에서부터 채우면서 순서대로 저장 
            if(num != 0) nums[idx++] = num;
        }

        //남은 공간 0으로 대체 
        while(idx < nums.length) {
            nums[idx++] = 0;
        }
    }
}