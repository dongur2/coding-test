import java.util.*;

class Solution {
    /*
     ** 주어지는 중복 없는 배열을 이용하여 만들 수 있는 모든 부분집합을 만들어 반환
     - 공집합도 포함
     - 중복되는 부분집합은 미포함
     
     - 중복 X, 순서 의미 없음 --> 조합 
     */

    boolean[] visited;
    List<List<Integer>> answer;

    public List<List<Integer>> subsets(int[] nums) {
        visited = new boolean[nums.length];
        answer = new ArrayList<>();

        for(int len=0; len<= nums.length; len++) {
            backtrack(nums, len, 0, new ArrayList<>());
        }
        
        return answer;
    }

    void backtrack(int[] nums, int len, int start, List<Integer> list) {
        //base case: 만들려고 하는 배열 길이와 현재 만들고있는 조합 길이가 같으면 저장하고 중지
        if(list.size() == len) {
            answer.add(new ArrayList<>(list)); return;
        }

        //recursive call: 방문한 적 없는 노드를 방문 처리 & 조합에 추가 - 그 노드를 포함한 조합에 대한 하청 - 현재 노드 방문 무효 & 제거
        for(int i=start; i<nums.length; i++) {
            if(visited[i]) continue;

            visited[i] = true; list.add(nums[i]);
            backtrack(nums, len, i+1, list);
            visited[i] = false; list.remove(Integer.valueOf(nums[i]));
        }
    }
}