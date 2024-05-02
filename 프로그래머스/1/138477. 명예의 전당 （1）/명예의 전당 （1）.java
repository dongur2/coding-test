import java.util.*;
import java.util.stream.*;

class Solution {
    private int[] my(int k, int[] score) {
        int[] answer = new int[score.length];
        
        int[] honors = {};
        int last = Integer.MIN_VALUE;
        
        // k < score.length일 경우    
        if(k > score.length) {
            for(int i=0; i<score.length; i++) {
                answer[i] = IntStream.of(Arrays.copyOf(score, i+1)).min().orElse(-1);
            }
            return answer;
        }
    
        // k일까지는 명예배열에 넣고, 최솟값 리턴
        for(int i=0; i<k; i++) {
            honors = Arrays.copyOf(score, i+1);
            last = IntStream.of(honors).min().orElse(-1);
            answer[i] = last;
        }
        
        // k일이후는 최소값보다 새로운 값이 작으면 명예배열 그대로, 최소값 그대로 리턴 
        // 새로운 값이 더 크면 배열의 최소값과 새로운값을 바꾸고, 그 중 최소값 리턴
        for(int i=k; i<answer.length; i++) {
            if(last < score[i]) {
                Arrays.sort(honors);
                honors[0] = score[i];
                last = IntStream.of(honors).min().orElse(-1);
            }
            answer[i] = last;
        }
        return answer;
    }
    
    private int[] useQueue(int k, int[] score) {
        int[] answer = new int[score.length];
        
        // 숫자가 작을수록 우선순위 높음 (앞)
        PriorityQueue<Integer> honors = new PriorityQueue<>();
        
        for(int i=0; i<answer.length; i++) {
            honors.add(score[i]);
            if(honors.size() > k) honors.poll();
            answer[i] = honors.peek();
        }
        return answer;
    }
    
    public int[] solution(int k, int[] score) {
        return useQueue(k, score);
    }
}