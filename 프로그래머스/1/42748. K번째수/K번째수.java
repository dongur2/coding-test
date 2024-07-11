import java.util.*;

class Solution {
    /*
    문제: 원래 배열을 i~j번째 숫자(포함)까지의 부분 배열로 자른 뒤, 정렬한 후 k번째 수를 리턴
    
    1. 답으로 리턴할 정답 배열을 commands배열길이로 생성
    2. [for] commands로 주어진 원소배열을 하나씩 순회 ([i,j,k] - 각 값은 1번부터 시작한 값이므로 -1을 해줘야 함을 유의)
        3. command에 해당하는 값만큼 array의 배열을 복사하여 새로운 변수에 저장
        4. 배열을 정렬
        5. 정답 배열 인덱스에 k번째 값을 저장
    6. 정답 배열을 리턴
    */
    public int[] solution(int[] array, int[][] commands) {
        //1. 정답 배열 생성
        int[] answer = new int[commands.length];
        
        //2. commands배열 순회
        int idx = 0;
        for(int[] command:commands) {
            //3. command 배열 안에 든 i~j 만큼 array 배열을 복사해 저장 후 배열을 정렬, k번째 값 저장
            int[] copied = Arrays.copyOfRange(array, command[0]-1, command[1]);
            Arrays.sort(copied);
            answer[idx++] = copied[command[2]-1];
        }
        return answer;
    }
}