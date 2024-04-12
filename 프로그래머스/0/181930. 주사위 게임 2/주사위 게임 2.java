class Solution {
    private int my(int a, int b, int c) {
        if(a == b && a == c) return (a+b+c) * (a*a + b*b + c*c) * (a*a*a+ b*b*b + c*c*c);
        else if(a != b && a != c && b != c) return a+b+c;
        else return (a+b+c) * (a*a + b*b + c*c);
    }
    
    private int refer(int a, int b, int c) {
        int count = 1;
        
        if(a == b && b == c) count++;
        if(a==b || b==c || a==c) count++;
        
        int answer = 1;

        for(int i=1; i<=count; i++) {
            int temp = pow(i, new int[] {a,b,c});
            answer *= temp;
        }        
        
        return answer;
    }
    
    private int pow(int i, int[] nums) {
        int temp = 0;
        
        for(int num : nums) {
            temp += Math.pow(num, i);    
        }
        
        return temp;
    }
    
    public int solution(int a, int b, int c) {
        return refer(a,b,c);
    }
}