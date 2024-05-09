class Solution {
    public int solution(int[] nums) {
        int cnt = 0;
        
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int l=j+1; l<nums.length; l++) {
                    int sum = nums[i] + nums[j] + nums[l];
                    
                    // 세 수의 합이 소수인지 판별
                    if(isPrime(sum)) cnt++;
                }
            }
        }
        return cnt;
    }
    
    private boolean isPrime(int num) {
        if (num % 2 == 0) return false; // 짝수는 소수가 아님
        
        // 제곱근까지의 수로만 나눠지는지 확인
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false; // 나누어 떨어지면 소수가 아님
        }
        return true; // 모두 통과하면 소수
    }
}