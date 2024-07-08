import java.util.*;

class Solution {
    public int[] solution(int[] prices) { // 시간복잡도 O(N)
        //1. 정답으로 리턴할 배열, 스택 생성
        int[] res = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();
        
        //2. 가격 배열 순회하면서 현재 가격과 스택 가격 비교
        for(int i=0; i<prices.length; i++) { // O(N)
            //3. 현재 가격 <= 스택 가격: 현재 가격이 스택 가격을 초과할 때까지 스택에서 인덱스 꺼냄 & 답 배열에 값 저장
            //** 정답 배열에 저장: 현재 인덱스 - 꺼낸 인덱스
            if(!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                while(!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                    int idx = stack.pop();
                    res[idx] = i - idx;
                }
            }
            //4. 현재 가격 > 스택 가격, 빈 스택: 스택에 현재 인덱스 저장
            stack.push(i);
        }
            
        //5. 스택이 비어있는지 확인
        //6. 비어있지 않으면: 빌 때까지 인덱스 꺼내서 답 배열에 값 저장
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            res[idx] = prices.length-1 - idx;
        }
        
        //7. 비어있으면 정답 배열 리턴
        return res;
    }
}