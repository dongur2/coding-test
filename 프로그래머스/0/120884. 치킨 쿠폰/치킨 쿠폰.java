class Solution {
    public int solution(int chicken) {
        int answer = 0;
        int coupon = chicken;
        
        while(coupon > 0 && coupon/10 > 0) {
            int service = coupon/10;
            answer += service;
            coupon %= 10;
            coupon += service;
        }
        
        return answer;
    }
}