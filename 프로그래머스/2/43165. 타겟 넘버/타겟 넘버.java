import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        return backtrack(numbers, target, 0, 0);
    }
    
    private int backtrack(int[] numbers, int target, int idx, int node) {
        //2. 인덱스가 배열 끝이라면, 현재 노드값이 타겟 숫자와 같은지 확인 후, 같다면 1을 리턴
        if(idx == numbers.length) {
            return node == target ? 1 : 0;
        }
        
        //1. 인덱스 0부터 각 배열값을 더하거나/빼가면서 전체 노드를 순회
        int cnt = 0;
        cnt += backtrack(numbers, target, idx+1, node + numbers[idx]);
        cnt += backtrack(numbers, target, idx+1, node - numbers[idx]);
        return cnt; //3. 리턴된 1과 0의 합을 리턴
    }
}