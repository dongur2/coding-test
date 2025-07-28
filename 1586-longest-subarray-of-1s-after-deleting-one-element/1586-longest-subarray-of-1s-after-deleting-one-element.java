/*
    주어진 2진법 숫자 배열 nums에서 원소 1개를 삭제해야 한다.
    1만 포함하는 부분 배열 중 가장 긴 길이를 리턴 (없으면 0)
    - non-emtpy -> 연속된 위치의 1 부분 배열 
 */
class Solution {
    public int longestSubarray(int[] nums) {
        int zero = 0, answer = 0;
        
        int left = 0;
        for(int right = 0; right<nums.length; right++) {
            if(nums[right] == 0) zero++;

            //범위에 포함하는 0의 개수를 무조건 1개로 유지 
            while(zero > 1) {
                if(nums[left++] == 0) zero--;
            }

            answer = Math.max(answer, right - left); //길이 - 0(1개)
        }

        return answer;
    }
}