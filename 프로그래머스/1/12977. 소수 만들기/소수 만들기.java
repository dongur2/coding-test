import java.util.*;

class Solution {
    //nums 배열의 숫자들 중 3개를 더해서 소수가 되는 경우 개수를 리턴
    int answer = 0, sum = 0;
    
    public int solution(int[] nums) {
        //세개 고르기: 순서의미없는 중복 불가 '조합'
        backtrack(nums, 0, 0, 0);
        return answer;
    }
    
    void backtrack(int[] nums, int start, int cnt, int sum) {
        //basecase: 세개 고르기 완료
        if(cnt == 3) {
            if(isPrime(sum)) answer++;
            return;
        }
        
        //recursive: 세개 고르자
        for(int i=start; i<nums.length; i++) {
            backtrack(nums, i+1, cnt+1, sum + nums[i]);
        }
    }
    
    boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}