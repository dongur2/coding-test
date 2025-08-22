/*
    prices[i] = i초 시점의 가격
    >>> 가격이 떨어지지 않은 초
    
    [1,2,3,2,3] -> [4,3,1,1,0]
    마지막 초는 되자마자 종료 (카운트 X)
    1초[1] -> 4
    2초[2] -> 3
    3초[3] -> 1
    4초[2] -> 1
    5초[3] -> 종료 0
*/
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Entry> stack = new ArrayDeque<>();
        
        for(int i=0; i<prices.length-1; i++) {
            int curr = prices[i];

            //다음 가격보다 현재 가격이 낮으면(같을 경우도) 추가 
            if(curr <= prices[i+1]) stack.push(new Entry(i, curr)); //인덱스와 가격 
    
            //다음 가격이 현재보다 낮을 경우
            else {
                //일단 현재 값은 즉시 저장 (현재 초만 유지)
                answer[i] = 1;
                
                //스택에서 다음 가격일 때 떨어지는 경우 빼야 함
                while(!stack.isEmpty() && stack.peek().val > prices[i+1]) {
                    Entry top = stack.pop();
                    answer[top.idx] = i+1 - top.idx;
                }
            }
        }
        
        //스택 비우기
        while(!stack.isEmpty()) {
            Entry top = stack.pop();
            answer[top.idx] = prices.length - top.idx - 1;
        }
        
        return answer;
    }
    
    class Entry {
        int idx, val;
        public Entry(int idx, int val) {
            this.idx=idx; this.val=val;
        }
    }
}