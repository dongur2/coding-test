/*
    2진법 배열 nums, 숫자 k
    배열에서 1의 최대 연속 횟수를 리턴하는데, 최대 k번 0을 1로 바꿀 수 있다.
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0, zero = 0;
        int answer = 0;

        for(int right=0; right<nums.length; right++) {
            if(nums[right] == 0) zero++;

            //바꿀 수 있는 0의 개수보다 더 많은 0을 가지고 있으면 버리기 
            while(zero > k) {
                if(nums[left++] == 0) zero--;
            }
            
            //최대 연속된 1의 개수 리턴 
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }
}