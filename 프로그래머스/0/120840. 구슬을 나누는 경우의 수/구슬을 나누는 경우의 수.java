class Solution {
    public int solution(int balls, int share) {
        // balls = n, share = m -- nCm
        double answer = 1;
        
        for(int i=balls; i>share; i--) { // n! / m!
            answer *= i;
        }
        
        for(int j=(balls-share); j>0; j--) { // (n-m)!
            answer /= j;
        }
        
        return (int)answer;
    }
}