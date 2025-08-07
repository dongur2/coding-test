/*
    peak element: 주변 원소보다 큰 원소 
    주어진 배열에서 peak element를 찾아 인덱스를 리턴할 것
    - 여러 개 있으면 아무거나
 */
class Solution {
    public int findPeakElement(int[] nums) {
        //원소 1개일 경우
        if(nums.length == 1) return 0;
        //원소 2개일 경우 
        if(nums.length == 2) return nums[0] > nums[1] ? 0 : 1;

        int left=0, right=nums.length;
        while(left <= right) {
            int mid = left + ((right - left) / 2);
            
            //왼쪽 끝이면 오른쪽이랑만 비교
            if(mid == 0) return nums[mid] > nums[mid+1] ? mid : mid+1;
            //오른쪽 끝이면 왼쪽이랑만 비교
            if(mid == nums.length-1) return nums[mid] > nums[mid-1] ? mid : mid-1;
            //중간 양쪽과 비교 - 중간이 가장 크면 리턴, 아니면 중간보다 큰쪽으로 이동 
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return mid;
            else {
                if(nums[mid] < nums[mid-1]) right = mid-1;
                else if(nums[mid] < nums[mid+1]) left = mid+1;
            }
        }

        return 0;
    }

}