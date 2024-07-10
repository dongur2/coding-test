import java.util.*;
import java.util.stream.*;

public class Solution {
    /*
    * 배열에서 연속으로 나타나는 숫자는 하나만 남기고 제거한 배열을 리턴
    */
    public int[] solution(int []arr) {
        //1. 스택 생성
        Deque<Integer> stack = new ArrayDeque<>();
        
        //2. 배열 첫번째 숫자를 스택에 저장: 비교
        stack.push(arr[0]);
        
        //3. 배열에 있는 숫자를 두번째 인덱스부터 순차적으로 순회
        for(int i=1; i<arr.length; i++) {
            //4. 스택에 있는 숫자와 다르면 현재 숫자를 스택에 저장
            if(stack.peek() != arr[i]) stack.push(arr[i]);            
        }
        
        //5. 스택에서 숫자를 꺼내어 배열에 저장해 리턴
        List<Integer> list = stack.stream().collect(Collectors.toList());
        Collections.reverse(list);
        return list.stream().mapToInt(n->n).toArray();
    }
}