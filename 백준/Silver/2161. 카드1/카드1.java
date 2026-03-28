import java.util.*;

class Main {
    public static void main(String[] args) {
        // 카드 N장 (1-N번)
        // 제일 위의 카드를 버리고, 그다음 카드를 아래로 --> 1장 남을 때까지
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1; i<=n; i++) {
            dq.add(i);
        }
        
        while(dq.size() > 1) {
            int top = dq.poll();
            System.out.print(top + " ");
            top = dq.poll();
            dq.add(top);
        }
        
        System.out.println(dq.poll());
    }
}