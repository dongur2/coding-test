class Solution {
    public int[] solution(int[][] score) {
        double[] avg = new double[score.length];
        for(int i=0; i<score.length; i++) {
            avg[i] = (double)(score[i][0] + score[i][1])/2;
        }
        
        int[] answer = new int[score.length];
        
        int cnt = 1;
        for(int i=0; i<avg.length; i++) {
            double nowAvg = avg[i];
            
            for(int j=0; j<avg.length; j++) {
                if(i!=j && nowAvg < avg[j]) cnt++;
            }
            answer[i] = cnt;
            cnt = 1;
        }
        
        return answer;
    }
}