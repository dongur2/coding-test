import java.util.Arrays;

class Solution {
    /*
    Q. 주어지는 숫자 배열 중 더하면 주어지는 숫자(target)가 되는 '두 숫자의 인덱스'를 포함한 배열을 반환
    - 같은 인덱스의 숫자를 두 번 더할 수는 없음
    - 더하면 target이 되는 숫자쌍은 오직 1개
    */
    public int[] twoSum(int[] nums, int target) {
        return useMap(nums, target);
    }
    
    //34ms
    private int[] checkAll(int[] nums, int target) {
        for(int a=0; a<nums.length-1; a++) {
            for(int b=a+1; b<nums.length; b++) {
                if(nums[a] + nums[b] == target) {
                    return new int[]{a, b};
                }
            }
        }
        return new int[]{-1};
    }
    
    private int[] useMap(int[] nums, int target) {
        //1. 원래 배열대로 {인덱스 : 숫자}로 맵을 생성하여 저장
        Map<Integer, Integer> origin = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            origin.put(i, nums[i]);
        }
        
        //2. 배열을 오름차순 정렬
        Arrays.sort(nums);

        //3. <합이 타겟이 될 때까지> 투포인터로 (1)합이 크면 오른쪽을 왼쪽으로, (2)합이 작으면 왼쪽을 오른쪽으로 이동
        //이 때, 두 포인터가 서로 겹치지 않도록 제한
        int a = 0, b = nums.length-1;
        while(nums[a] + nums[b] != target && a < b) {
            if(nums[a] + nums[b] > target) b--;
            else if(nums[a] + nums[b] < target) a++;
        }
        
        //4. 숫자의 원래 인덱스값을 배열에 넣어 반환
        //값이 또 들어가지 않도록 -1일 때 검사하도록 제한
        int[] result = new int[]{-1, -1};
        for(int idx : origin.keySet()) {
            if(result[0] == -1 && origin.get(idx) == nums[a]) { 
                result[0] = idx; continue;
            } else if(result[1] == -1 && origin.get(idx) == nums[b]) result[1] = idx;  
            
            if(result[0] != -1 && result[1] != -1) break;
        }
        
        return result;
    }
}