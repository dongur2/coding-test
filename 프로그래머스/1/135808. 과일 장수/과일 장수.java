import java.util.Arrays;

//m개씩 포장 - 가격:가장 낮은 점수 * m -> 가능한 최대 이익
class Solution {
    public int solution(int k, int m, int[] score) {
        //최대 점수 k
        //점수 배열 .. -> 오름차순 정렬하고 m개씩 자르기 
        Arrays.sort(score);
        
        int answer = 0;
        
        for(int i=score.length-m; i>=0; i-=m) {
            answer += m * score[i];
        }
        
        return answer;
    }
}