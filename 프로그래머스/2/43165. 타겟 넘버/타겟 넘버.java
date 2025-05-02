//주어진 배열의 숫자를 순서를 유지한 채로 더하거나 빼서 타겟 숫자로 만드는 방법 수
class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int idx, int num) {
        //마지막 인덱스까지 왔을 경우 값 비교
        if(idx == numbers.length) {
            if(num == target) answer++;
            return;
        }
        
        //dfs로 각 원소마다 -, + 진행
        dfs(numbers, target, idx+1, num+numbers[idx]);
        dfs(numbers, target, idx+1, num-numbers[idx]);
    }
}