import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score);
        int box = score.length / m; // 총 박스 개수
        
        int sales = 0;
        for(int i=1; i<=box; i++) {
            int lowest = score[score.length - (m*i)]; // 박스당 최저 사과 점수
            sales += lowest * m;
        }
        return sales;
    }
}