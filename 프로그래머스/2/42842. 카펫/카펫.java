import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        //주어진 노란색 격자 개수의 약수를 새로운 배열에 저장 : 2000000까지 가는데 괜찮나? 일단 해봄
        List<Integer> yellowHeight = new ArrayList<>();
        for(int i=1; i<=Math.sqrt(yellow); i++) {
            if(yellow % i == 0) yellowHeight.add(i);
        }
        
        //약수 배열을 차례대로 순회:
        for(int h : yellowHeight) {
            //노란색 격자의 세로 길이: 현재 약수
            //노란색 격자의 가로 길이: 주어진 노란색 격자 개수 / 현재 약수
            int w = yellow / h;
            
            //노란색 세로 > 가로일 경우 중지(이후도 똑같을 것이므로)
            if(h > w) break;
            
            //주어진 갈색 격자수가 현재 상황에 맞는지 확인: 맞으면 즉시 가로 세로 리턴
            if(brown - (w * 2) - ((h + 2) * 2) == 0) return new int[]{w+2, h+2};            
        }

        return new int[]{0};
    }
}