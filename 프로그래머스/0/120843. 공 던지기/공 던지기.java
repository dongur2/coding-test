class Solution {
    public int solution(int[] numbers, int k) {
        int answer = ((2 * k) - 1) % numbers.length;
        return (answer == 0)? numbers.length : answer;
    }
}