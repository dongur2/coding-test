class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        int num = 1;
        
        int cycle = (n % 2 == 0)? n/2 : n/2 + 1;
        
        for(int time=0; time < cycle; time++) {
            
            for(int j=time; j < n - (1*time); j++) {
                answer[time][j] = num++;
            }
            
            for(int i=time+1; i < n - (1*time); i++) {
                answer[i][n - (1*time + 1)] = num++;
            }
            
            for(int j=n-(2+time); j > time; j--) {
                answer[n-(1+time)][j] = num++;
            }
            
            for(int i=n-(1+time); i > time; i--) {
                answer[i][time] = num++;
            }
            
        }
        return answer;
    }
}