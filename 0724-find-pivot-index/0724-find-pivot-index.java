/*
    숫자 배열 nums - 이 배열의 피벗 인덱스를 계산하기
    *피벗 인덱스: 배열의 왼쪽과 오른쪽의 합이 같아지도록 하는 중간 인덱스
    피벗인덱스가 0인 경우 - '배열[0] == 배열 원소의 모든 합'
    
    제일 왼쪽에 있는 피벗 인덱스를 리턴할 것 (없으면 -1)
 */
class Solution {
    public int pivotIndex(int[] nums) {
        //전체 합
        int total = 0;
        for(int num:nums) {
            total += num;
        }

        //왼쪽 합 
        int leftSum = 0;
        for(int i=0; i<nums.length; i++) {
            //i가 피벗 인덱스라고 가정할 경우 오른쪽 합
            int rightSum = total - leftSum - nums[i];
            if(leftSum == rightSum) return i; //왼쪽과 오른쪽 합이 같으면 즉시 리턴 (가장 왼쪽 값이니까)
            leftSum += nums[i]; //왼쪽 합 축적 
        }

        return -1;
    }
}