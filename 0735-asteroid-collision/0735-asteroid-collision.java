import java.util.Deque;
import java.util.ArrayDeque;
/*
    소행성 숫자 배열 - 각 인덱스는 우주에서 각 행성의 상대적인 위치
    각 행성은 크기에 대해 절대값을 가지며 기호는 방향을 의미(+오른쪽, -왼쪽), 그리고 모두 같은 속도로 이동

    모든 충돌 이후 소행성의 상태를 알아낼 것
    - 소행성 두개가 만나면 -> 작은 쪽이 폭발 (크기가 같으면 모두 폭발)
    - 같은 방향으로 움직이는 소행성은 절대 만나지 않음
 */
class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int a : asteroids) {
            //새로운 행성의 존재 상태 
            boolean alive = true;

            //충돌 가능성 있는 동안 반복: 저장된 행성이 오른쪽으로(+), 새로운 행성이 왼쪽으로(-)
            while (alive && !stack.isEmpty() && stack.peek() > 0 && a < 0) {
                int top = stack.peek();

                //스택 행성보다 새로운 행성이 크기가 더 크면 스택 행성이 폭파 
                if (Math.abs(top) < Math.abs(a)) stack.pop();
                //두 행성의 크기가 같으면 둘 다 폭파 
                else if (Math.abs(top) == Math.abs(a)) {
                    stack.pop(); alive = false; //새로운 행성이 폭파되었으므로 충돌 가능성 제거 
                //스택 행성보다 새로운 행성이 크기가 더 작으면 새로운 행성이 폭파 -> 충돌 가능성 제거 
                } else alive = false;
            }

            //모든 충돌 후에도 새로운 행성이 살아있으면 스택에 저장 
            if (alive) stack.push(a);
        }


        int[] answer = new int[stack.size()];
        for(int i=answer.length-1; i>=0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }

}