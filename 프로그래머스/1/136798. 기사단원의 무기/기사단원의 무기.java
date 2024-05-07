class Solution {
    public int solution(int number, int limit, int power) {
        int iron = 0;
        
        // '1번부터 number번까지의' 기사가 구매하는 무기를 위한 철의 무게 합
        for(int i=1; i<=number; i++) {
            iron += countDivisor(i, limit, power);
        }
        return iron;
    }
    
    // 각 수마다 약수 개수 리턴
    private int countDivisor(int num, int limit, int power) {
        int cnt = 0;
        
        for(int j=1; j*j<=num; j++) {
            if(j*j == num) cnt++;
            else if(num % j == 0) cnt += 2;

            if(cnt > limit) {
                cnt = power;
                break;
            }
        }
        
        return cnt;
    }
}