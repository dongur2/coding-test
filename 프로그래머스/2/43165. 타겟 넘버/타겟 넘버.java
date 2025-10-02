class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0, 0);
    }
    
    int dfs(int[] numbers, int target, int idx, int cnt, int curr) {
        cnt = 0; 
        
        //basecase: 주어진 숫자를 모두 연산했으면 값 확인
        if(idx == numbers.length) {
            if(curr == target) return 1;
            else return 0;
        }
        
        cnt += dfs(numbers, target, idx+1, cnt, curr+numbers[idx]);
        cnt += dfs(numbers, target, idx+1, cnt, curr-numbers[idx]);
        
        return cnt;
    }
}