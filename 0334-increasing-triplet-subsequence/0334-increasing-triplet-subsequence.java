/*
    주어진 숫자 배열 nums에서, 
    'i<j<k'와 'nums[i] < nums[j] < nums[k]'를 만족하는 쌍이 존재하는지 여부를 리턴 
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for(int num:nums) {
            if(num <= a) a = num;
            else if(num <= b) b = num;
            else return true;
        }
        
        return false;
    }
}