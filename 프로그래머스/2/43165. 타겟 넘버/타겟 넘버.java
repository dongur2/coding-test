import java.util.*;

/*
Q. 순서를 유지한 상태로 "더하거나 빼서" 타겟 넘버로 만들 수 있는 "총 방법 수"를 리턴

- 시작 노드는 무조건 "0"
- 다음 노드는 음수/양수 두가지 경우의 수로
- 앞 노드는 방문할 필요 없으므로 인덱스를 하나씩 밀기
- dfs
*/
class Solution {
    int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0); 
        return cnt;    
    }
    
    void dfs(int[] numbers, int target, int now, int idx) {
        //base case: 끝 노드라면 현재 숫자가 타겟과 같은지 확인 후 종료
        if(idx == numbers.length) {
            if(target == now) cnt++; return;
        }
        
        //loop: 현재 숫자에 다음 노드 숫자를 빼거나 더함
        dfs(numbers, target, now + numbers[idx], idx+1);
        dfs(numbers, target, now - numbers[idx], idx+1);
    }
}