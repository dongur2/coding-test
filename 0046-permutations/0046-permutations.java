class Solution {
    /*
     ** 주어진 배열 길이로 만들 수 있는 가능한 모든 순열을 만들어서 반환
     - 순열: 중복X && 순서의미O
     */

    boolean[] visited; //중복으로 원소를 추가하지 않기 위해 방문 여부 관리
    List<List<Integer>> answer; //만든 순열을 넣어 반환할 리스트

    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        answer = new ArrayList<>();

        backtrack(nums, new ArrayList<>());

        return answer;
    }

    private void backtrack(int[] nums, List<Integer> list) {
        //basecase: 현재 만들고있는 순열의 길이가 주어진 배열 길이와 같다면 리스트에 저장하고 재탐색 중지
        if(list.size() == nums.length) {
            answer.add(new ArrayList<>(list)); return;
        }

        /* recursive call: 
        배열 전체를 순회하며 방문 & 방문했던 노드라면 무시
        1. 새로운 노드라면 방문 체크 & 순열에 추가 (하청에서 현재 노드를 또 방문해 추가하지 않도록 :: 중복 불가능)
        2. 그 노드에서 재탐색, 길이를 만족하면 저장 후 중지(하청): 지금까지의 순열 + 추가 노드를 합해 만들 수 있는 순열을 모두 만들어 저장 완료
        3. 하청 완료 후 현재 노드 방문 무효 & 순열에서 제거
        */
        for(int i=0; i<nums.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true; list.add(nums[i]);
            backtrack(nums, list);
            visited[i] = false; list.remove(Integer.valueOf(nums[i]));
        }
    }
}