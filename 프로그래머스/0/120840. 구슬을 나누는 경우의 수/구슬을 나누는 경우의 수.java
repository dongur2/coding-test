class Solution {
    public int solution(int balls, int share) {
        // balls = n, share = m -- nCm
        double answer = 1;
        
        for(int i=balls; i>share; i--) { // n! / m!
            System.out.println("i= "+i);
            answer *= i;
            System.out.println("** "+answer);
        }
        
        for(int j=(balls-share); j>0; j--) { // (n-m)!
            answer /= j;
            System.out.println("// "+answer);
        }
        
        return (int)answer;
    }
}