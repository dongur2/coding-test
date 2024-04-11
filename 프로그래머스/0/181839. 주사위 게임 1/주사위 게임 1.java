class Solution {
    public int solution(int a, int b) {
        if(a % 2 == 0 && b % 2 == 0) return Math.abs(a-b);
        if(a % 2 > 0 && b % 2 > 0) return (int)(Math.pow(a,2) + Math.pow(b,2));
        else return 2 * (a+b);
    }
}