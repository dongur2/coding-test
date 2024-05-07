class Solution {
    public int solution(int number, int limit, int power) {
        return refer(number, limit, power);
    }
    
    // 다른 사람 풀이
    private int refer(int number, int limit, int power) {
        int[] count = new int[number + 1]; // 해당 인덱스의 약수 개수를 저장
        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= number / i; j++) { // number 이하의 i의 배수를 구하기 위한 number/i
                count[i * j]++; // i의 배수는 i를 약수로 포함
            }
        }
        
        // 공격력 제한 필터링
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            answer += (count[i] > limit)? power:count[i];
        }
        return answer;
    }
    
    private int my(int number, int limit, int power) {
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