import java.util.Map;
import java.util.HashMap;

//주어진 배열에서 더해서 target을 만들 수 있는 두 숫자의 '인덱스' 리턴 
class Solution {
    public int[] twoSum(int[] nums, int target) {
        /*
            1. 한가지 방법만 가능하도록 주어지며,
            2. 같은 위치 숫자를 중복해서 사용할 수 없음
         */
        // return sol1(nums, target);
        return sol2(nums, target);
    }

    //O(N^2 -- 10^8)
    private int[] sol1(int[] nums, int target) {
        for(int i=0; i<nums.length-1; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i] + nums[j] == target) return new int[] {i, j}; //합이 target이 되면 즉시 리턴 
            }
        }
        return new int[] {};
    }

    //O(N)
    private int[] sol2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); //값:인덱스

        //O(N)
        for (int i=0; i<nums.length; i++) {
            int need = target - nums[i]; //target을 만들기 위해 필요한 숫자 

            //필요한 숫자가 이미 맵에 저장돼있으면 리턴 O(1)
            if (map.containsKey(need)) return new int[] { map.get(need), i };
            
            //맵에 없으면 현재 숫자를 맵에 추가
            map.put(nums[i], i); 
        }

        return new int[] {};
    }

}