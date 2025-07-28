/*
    2진법 배열 nums, 숫자 k
    배열에서 1의 최대 연속 횟수를 리턴하는데, 최대 k번 0을 1로 바꿀 수 있다.
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int zero = 0; //0개수 
        int one = 0;  //연속된 1개수

        int left = 0, right = 0;

        if(nums[left] == 0) zero++;
        else one++;

        int answer = one;
        if(k >= zero) answer += zero;

        while(right < nums.length-1) {
            //0 커버할 수 있으면 right++
            //추가하는 값이 0이면 0 증가, 1이면 1 증가 
            if(nums[++right] == 0) zero++; 
            else one++;

            //0개수 > k이면 중지하고 left++
            while (zero > k) { //버리는 값이 0이었으면 0 감소, 1이면 1 감소 
                if(nums[left++] == 0) zero--;
                else one--;
            }

            answer = Math.max(answer, one+zero);
        }    

        return answer;
    }
}