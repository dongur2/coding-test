import java.util.*;

class Solution {
    private int[] myCode(int[][] score) {
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
    
    /*
    * 점수합을 저장한 배열을 내림차순 정렬 후, 순서대로 인덱스 저장
    * indexOf() -> 제일 작은 인덱스 반환
    */
    private int[] useArr(int[][] score) {
        List<Integer> sum = new ArrayList<>();
        for(int[] s : score) {
            sum.add(s[0]+s[1]);
        }
        sum.sort(Comparator.reverseOrder());
        
        int[] answer = new int[score.length];
        for(int i=0; i<score.length; i++) {
            answer[i] = sum.indexOf(score[i][0] + score[i][1]) + 1;
        }
        
        return answer;
    }
    
    public int[] solution(int[][] score) {
        return myCode(score);
    }
}