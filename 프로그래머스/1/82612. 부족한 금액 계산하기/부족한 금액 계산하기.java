class Solution {
    public long solution(int price, int money, int count) {
        long balance = money;
        for(int i=1; i<=count; i++) {
            balance -= (long)price * i;
        }
        
        return (balance >= 0)? 0:Math.abs(balance);
    }
}