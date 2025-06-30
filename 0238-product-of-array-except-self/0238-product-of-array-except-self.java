//answer[i] = nums[i]를 제외한 nums의 모든 원소의 곱 
class Solution {
    public int[] productExceptSelf(int[] nums) { //10^5
        int n = nums.length;
        int[] answer = new int[n];
        
        int[] pre = new int[n]; //앞
        int[] suff = new int[n]; //뒤

        //각 배열의 시작점 1
        pre[0] = 1;
        suff[n-1] = 1;

        for(int i=1; i<n; i++) {
            pre[i] = pre[i-1] * nums[i-1];
        }

        for(int i=n-2; i>=0; i--) {
            suff[i] = suff[i+1] * nums[i+1];
        }

        for(int i=0; i<n; i++) {
            answer[i] = pre[i] * suff[i];
        }

        return answer;     
    }
}