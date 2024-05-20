class Solution {
    public int solution(int[] arr) {
        int gcd = GCD(arr[0], arr[1]);
        for(int i=2; i<arr.length; i++) {
            gcd = GCD(gcd, arr[i]);
        }
        
        for(int i=1; ; i++) {
            int res = gcd * i;
            for(int j=0; j<arr.length; j++) {
                if(res % arr[j] > 0) break;
                else if(res % arr[j] == 0 && j == arr.length-1) return res;
            }
        }
    }
    
    private int GCD(int a, int b) {
        if(b == 0) return a;
        return GCD(b, a % b);
    }
}