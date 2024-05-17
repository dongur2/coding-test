class Solution {
    public int solution(int n) {
        int front = 0;
        int after = 1;
        
        for(int i=2; i<n+1; i++) {
            int temp = after;
            after = (after + front) % 1234567;
            front = temp;
        }
        return after;
    }
}