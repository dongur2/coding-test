class Solution {
    public int solution(int M, int N) {
        if(M*N == 1) return 0;
        if(N == 1) return M-1; 
        if(M == 1) return N-1;
        
        return (M-1) + (M * (N-1));
    }
}