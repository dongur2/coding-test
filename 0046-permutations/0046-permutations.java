import java.util.*;

class Solution {
    //1. 전역 변수 선언: 리턴할 리스트, 방문 여부 배열
    List<List<Integer>> result;
    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        //2. 전역 변수 초기화
        result = new ArrayList<>();
        visited = new boolean[nums.length];

        //3. backtrack 호출: 매개변수로 넘기는 새로운 리스트에 순열 요소를 저장해서 계속해서 공유
        backtrack(new ArrayList<Integer>(), nums); 

        return result;
    }

    private void backtrack(List<Integer> cur, int[] nums) {
        //base case: 순열 크기가 nums배열 크기와 같으면 정답 리스트에 추가하고 재귀 종료
        if(cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
        }

        //recursive call
        //1. nums를 앞에서부터 순회
        for(int i=0; i<nums.length; i++) {
            //2. 방문한 노드의 visited 값이 true(이미 방문): 다음 노드로 이동
            if(visited[i]) continue;

            //3. 방문하지 않았다면 
            //노드의 visited 값을 true로 변경
            visited[i] = true;
            //cur에 현재 노드값을 추가
            cur.add(nums[i]);

            //backtrack() 호출: 아직 순열 크기가 nums배열 크기에 미달하므로 추가할 값이 더 필요
            backtrack(cur, nums);

            //4. backtrack 작업을 끝내고 돌아온 후:
            //visited 값을 false로 변경: backtrack으로 이어진 재귀 마지막에서 이미 순열을 추가했으므로
            visited[i] = false;
            //cur에서 현재 노드값을 삭제
            cur.remove(cur.size() - 1);
        }
    }
}