public class Solution {
    public int solution(int n) {
        // int power = 0;
        // 2로 나누다가 나누어 떨어지지 않으면 -1씩 감소 - 0까지
        // while(n>0) {
        //     if(n % 2 == 0) n/=2;
        //     else {
        //         n--;
        //         power++;
        //     }
        // }
        // return power;
        
        // * 2로 나누어지지 않을 때만 카운트하니까 bitCount()로 1인 경우만 카운트하는 것과 같음
        return Integer.bitCount(n);
    }
}