//주어진 배열에서 [계속해서 증가하는 순서를 맞춘 배열] 중 가장 긴 경우의 길이 리턴 
class Solution {
    //nums.length: 1 ~ 2500 10^3
    public int lengthOfLIS(int[] nums) {
        int[] len = new int[nums.length];
        Arrays.fill(len, 1);

        //nums[i]가 마지막 원소가 되는 가장 긴 배열을 조합
        for(int i=1; i<nums.length; i++) {
            //nums[i]앞의 모든 숫자들을 확인 
            for(int j=0; j<i; j++) {
                //nums[i]이 nums[j](앞 숫자)보다 크면 - 오름차순이니까 가능  
                if(nums[i] > nums[j]) len[i] = Math.max(len[i], len[j]+1); //nums[j]로 끝나는 가장 긴 배열 길이+1 과 현재 계산중인 nums[i]로 끝나는 배열 길이 중 비교ㅡ - 더 큰 값 저장 
            }
        }

        return Arrays.stream(len).max().getAsInt();
    }
}