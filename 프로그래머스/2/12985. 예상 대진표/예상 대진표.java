class Solution {
    public int solution(int n, int a, int b) {
        int round = 1;
        
        // 마주칠 수 있는 조건
        // 1. a와 b는 연속
        // 2. a와 b 중 큰 수는 2의 배수 (1,2 3,4 5,6 이렇게 둘씩 짝지어 게임을 진행할 수 밖에 없음)
        while(Math.abs(a-b) != 1 || Math.max(a,b) % 2 != 0) {
            a = (int)Math.ceil((double)a/2);
            b = (int)Math.ceil((double)b/2);
            round++;
        }
        
        return round;
    }
}