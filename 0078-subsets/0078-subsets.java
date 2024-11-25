import java.util.*;

class Solution {
    /*
    Q. 주어진 중복 없는 숫자 배열로 만들 수 있는 모든 부분집합을 반환
    - 공집합 포함
    - 중복 불허 & 순서 상관없음 => "조합"
    */
    
    List<List<Integer>> result;
    
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        
        //만들어야하는 부분집합 크기 0 ~ nums.length개
        for(int i=0; i<=nums.length; i++) {
            makeSubsets(nums, new boolean[nums.length], new ArrayList<>(), i, 0);
        }
        
        return result;
    }
    
    private void makeSubsets(int[] nums, boolean[] visited, List<Integer> list, int len, int idx) {
        //만든 집합의 크기가 만들어야하는 크기와 같으면 추가하고 종료
        if(list.size() == len) {
            result.add(new ArrayList<>(list)); return;
        }
        
        for(int i=idx; i<nums.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            list.add(nums[i]);
            makeSubsets(nums, visited, list, len, i+1);
            visited[i] = false;
            list.remove(Integer.valueOf(nums[i]));
        }
    }
}