import java.util.*;

class Solution {
    /*
    Q. 얻을 수 있는 최대 이익 리턴
    - 1상자에 m개씩 포장
    - 1상자의 가격은 m * 포장된 사과 중 가장 낮은 점수
    - 이익이 발생하지 않으면 0 리턴
    */
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        for(int i=score.length-m; i>=0; i-=m) {
            answer += score[i] * m;
        }
        
        return answer;
    }
}