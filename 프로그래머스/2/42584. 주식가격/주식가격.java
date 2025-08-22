import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Entry> stack = new ArrayDeque<>();
        
        for(int i=0; i<prices.length-1; i++) {
            int curr = prices[i];

            //스택에 저장된 값 중 현재 초에 가격이 떨어지는 경우 탐색
            while(!stack.isEmpty() && stack.peek().val > curr) {
                Entry top = stack.pop();
                answer[top.idx] = i - top.idx; 
            }
            
            //스택에 추가
            stack.push(new Entry(i, curr));
        }
        
        //스택 비우기
        while(!stack.isEmpty()) {
            Entry top = stack.pop();
            answer[top.idx] = prices.length - top.idx - 1; //마지막 초 제외 
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