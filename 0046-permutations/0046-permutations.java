import java.util.*;

class Solution {
    /*
    Q. 주어진 숫자 배열로 만들 수 있는 모든 순열을 반환
    - 순열 크기는 주어진 숫자 배열의 크기와 같음
    - 순열: 중복 불허 & 순서에 의미
    */
    
    boolean[] visited; //방문 체크
    List<List<Integer>> result; //반환할 배열
    
    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        result = new ArrayList<>();
        
        //배열을 순서대로 순회하면서 순열 생성
        makePermutation(nums, new ArrayList<>());
        
        return result;
    }
    
    private void makePermutation(int[] nums, List<Integer> list) {
        //만든 순열의 크기가 주어진 배열의 크기와 같으면 반환할 배열에 추가 후 종료
        if(list.size() == nums.length) {
            result.add(new ArrayList<>(list)); 
            return;
        }
        
        //다시 배열을 순서대로 순회
        for(int i=0; i<nums.length; i++) {
            //이미 방문한 숫자면 무시
            if(visited[i]) continue;
            
            //방문하지 않은 숫자면 방문 체크 후 순열에 추가
            visited[i] = true; list.add(nums[i]);
            makePermutation(nums, list);
            //가능한 순열 모두 생성 후에 다시 방문 체크 무효, 순열에서 제거
            visited[i] = false; list.remove(Integer.valueOf(nums[i]));
        }
    }
}