class Solution {
    //소수 판별 메서드
    public boolean isPrime(int num) {
        for(int i=2; i<num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    
    public int solution(int[] nums) {
        // 3개 수를 더했을 때 소수가 되는 경우의 수 리턴: 숫자는 중복 불허
        int cnt = 0;

        // 1.가능한 3개 조합 모두 순회
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int l=j+1; l<nums.length; l++) {
                    // 2. 소수 판별 후 소수라면 카운트
                    if(isPrime(nums[i]+nums[j]+nums[l])) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}