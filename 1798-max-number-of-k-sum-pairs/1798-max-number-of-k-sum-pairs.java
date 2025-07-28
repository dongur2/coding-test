/*
    숫자 배열 nums, 숫자 k
    반복: 배열에서 합이 k가 되는 숫자 2개를 골라 제거한다.
    수행할 수 있는 최대 반복 횟수를 리턴
 */
class Solution {
    public int maxOperations(int[] nums, int k) {
        //nums.length <= 10^5

        //정렬 O(NlogN)
        Arrays.sort(nums);        

        int cnt = 0;
        int left = 0, right = nums.length-1;

        //O(logN)
        //합 < k: 왼쪽++, 합 > k: 오른쪽--
        //합 = k: 왼쪽,오른쪽 이동 + 카운트
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum < k) left++;
            else if(sum > k) right--;
            else {
                left++; right--; cnt++;
            }
        }

        return cnt;
    }
}