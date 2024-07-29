import java.util.*;

class Solution {
    /*
    문제: 명함을 다 넣을 수 있는 지갑의 최소 크기를 리턴
    - 1 <= 명함 개수(배열 길이) <= 10^4
    - 가로/세로를 바꿀 수 있음 --> 긴쪽을 한쪽으로 고정
    */
    public int solution(int[][] sizes) {
        int maxW = 0, maxH = 0;
        
        for(int[] size:sizes) {
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);
            maxW = Math.max(maxW, width);
            maxH = Math.max(maxH, height);
        }
        
        return maxW * maxH;
    }
}