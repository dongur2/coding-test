import java.util.Deque;
import java.util.ArrayDeque;

//가격이 떨어지지 않은 초
class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        
        //가격 비교
        Deque<Node> st = new ArrayDeque<>();
        for(int i=0; i<n-1; i++) {
            int nxtPrice = prices[i+1];
            
            //다음 초 가격 >= 현재 가격: 스택에 추가
            if(nxtPrice >= prices[i]) st.push(new Node(prices[i], i));
            
            //다음 초 가격 < 현재 가격: 1초 유지 바인딩 및 스택에 있는 가격을 다음 초 가격과 비교 (떨어지면 꺼내고 바인딩)
            else {
                answer[i] = 1;
                
                while(!st.isEmpty() && st.peek().price > nxtPrice) {
                    Node prior = st.pop();
                    answer[prior.idx] = i+1 - prior.idx;
                }
            }
        }
        
        //스택에 남은 가격
        while(!st.isEmpty()) {
            Node prior = st.pop();
            answer[prior.idx] = n - prior.idx - 1;
        }
        
        return answer;
    }
    
    private class Node {
        private int price;
        private int idx;
        
        public Node (int price, int idx) {
            this.price = price;
            this.idx = idx;
        }
    }
}