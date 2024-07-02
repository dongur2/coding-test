import java.util.*;

class Solution {
    public int useMap(int[] nums) {
        //1. 종류별 카운트할 HashMap 생성
        Map<Integer, Integer> map = new HashMap<>();
        
        //2. 배열 돌면서 Key 비교해서 등록하거나 카운트
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        //3. 맵 크기와 N/2 중 작은 개수를 리턴: 고를 수 있는 최대값이 N/2
        return Math.min(map.size(), nums.length/2);
    }
    
    public int useSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num:nums) {
            set.add(num);
        }
        return Math.min(set.size(), nums.length/2);
    }
    
    public int solution(int[] nums) {
        return useSet(nums);
    }
}