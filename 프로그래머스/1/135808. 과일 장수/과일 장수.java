import java.util.*;

class Solution {
    /*
    주어진 사과들(score)로 m개가 들은 상자를 만들어서 판매했을 때, 얻을 수 있는 최대 이익
    - 각 상자의 가격은 들어있는 사과 중 최소 점수 x m개 
    
    --> 높은 점수 사과부터 박스에 패키징하고 남는 걸 버리기
    */
    public int solution(int k, int m, int[] score) {
        //주어진 사과로 만들 수 있는 박스 개수
        int box = score.length / m;
        
        //주어진 사과를 점수 순서대로 정렬
        Arrays.sort(score);
        
        //뒤에서부터 사과를 m개씩 박스에 넣고, 박스에 넣은 마지막 사과가 최소 점수가 되므로, 그 점수 * 사과 개수를 계산한 가격을 합산
        int profit = 0;
        for(int i=score.length-m; i>=0; i -= m) {
            profit += score[i] * m;
        }
        return profit;
    }
}