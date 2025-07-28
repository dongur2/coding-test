/*
    n개 원소로 이루어진 숫자 배열 nums, 숫자 k
    길이가 k인 연속된 원소로 이루어진 부분 배열을 찾아서, 이 경우의 최대 평균값을 리턴 
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0, right = k-1;
        double sum = 0, res = 0;

        //초기값
        for(int i=0; i<=right; i++) {
            sum += nums[i];
        }
        res = sum / k;

        //오른쪽으로 윈도우를 밀면서 왼쪽은 빼고 오른쪽은 추가하면서 최대 평균값 비교 
        while(right < nums.length-1) {
            sum -= nums[left++];
            sum += nums[++right];
            res = Math.max(res, (sum / k)); 
        }

        return res;
    }
}