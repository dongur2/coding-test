import java.util.Set;
import java.util.HashSet;
/*
    m번 시도로 가능한 정수 조합 개수 
    - 정수 범위 1-n
    - 코드: 서로 다른 5개 오름차순 정수 
    - 각 시도마다 포함된 개수 출력 
    
    1-n으로 만들 수 있는 모든 다섯자리 코드 조합을 구하고 .. 입력마다 적용하고 출력 숫자가 같은지 확인?
    -> n*n-1*n-2*n-3*n-4 = O(n^5)
*/
class Solution {
    static int answer = 0;
    
    public int solution(int n, int[][] q, int[] ans) {
        makeCodeAndCheck(n, q, ans, 1, new HashSet<>());
        return answer;
    }
    
    void makeCodeAndCheck(int n, int[][] q, int[] ans, int num, Set<Integer> code) {
        if(code.size() == 5) {
            if(check(q, ans, code)) answer++; //시스템 출력과 맞는지 확인 
            return;
        }
        
        for(int curr = num; curr<=n; curr++) {
            code.add(curr);
            makeCodeAndCheck(n, q, ans, curr+1, code);
            code.remove(curr);
        }
    }
    
    boolean check(int[][] q, int[] ans, Set<Integer> code) {
        for(int i=0; i<q.length; i++) {
            int cnt = 0;
            for(int qNum : q[i]) {
                if(code.contains(qNum)) cnt++;
            }
            if(cnt != ans[i]) return false;
        }
        return true;
    }
}