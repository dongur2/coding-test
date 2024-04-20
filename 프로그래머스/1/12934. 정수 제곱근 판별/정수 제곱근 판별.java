class Solution {
    public long solution(long n) {
        // for(long i=1; i*i<=n; i++) {
        //     if(i*i == n) return (i+1)*(i+1);
        // }
        // return -1;
        
        if(Math.pow((int)Math.sqrt(n), 2) == n) {
            return (long)Math.pow(Math.sqrt(n)+1, 2);
        }
        return -1;
    }
}