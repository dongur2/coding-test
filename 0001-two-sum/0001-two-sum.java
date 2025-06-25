//주어진 배열에서 더해서 target을 만들 수 있는 두 숫자의 '인덱스' 리턴 
class Solution {
    public int[] twoSum(int[] nums, int target) {
        /*
            1. 한가지 방법만 가능하도록 주어지며,
            2. 같은 위치 숫자를 중복해서 사용할 수 없음
         */
         
        //O(N^2 -- 10^8)
        for(int i=0; i<nums.length-1; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] + nums[j] == target) return new int[] {i, j};
            }
        }

        return new int[] {};
    }
}