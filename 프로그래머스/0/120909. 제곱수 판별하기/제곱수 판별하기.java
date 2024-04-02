class Solution {
    public int solution(int n) {
        // n이 제곱수라면 1, 아니면 2
        return Math.sqrt(n) == (int)Math.sqrt(n)? 1:2;
    }
}