import java.util.*;

/*
순서를 바꾸지 않고 더하거나/빼서 타겟 넘버를 만들 수 있는 방법 개수를 반환
DFS: 0부터 시작해서 -1/+1 곱한 결과로 이동
*/
class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    void dfs(int[] numbers, int target, int cur, int idx) {
        if(idx == numbers.length) {
            if(target == cur) answer++;
            return;
        }
        
        for(int n : new int[]{-1, 1}) {
            int nextNode = cur + (numbers[idx] * n);
            if(idx < numbers.length) dfs(numbers, target, nextNode, idx+1);
        }
    }
}