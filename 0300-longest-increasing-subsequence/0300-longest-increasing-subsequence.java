//주어진 배열에서 오름차순으로 정렬할 수 있는 배열의 최장 길이 
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] len = new int[nums.length];
        Arrays.fill(len, 1);
        
        for(int i=0; i<nums.length; i++) {
            for(int j=i; j<nums.length; j++) {
                if(nums[i] < nums[j]) len[j] = Math.max(len[i]+1, len[j]);
            }
        }

        return Arrays.stream(len).max().getAsInt();
    } 
}