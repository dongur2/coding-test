import java.util.*;

class Solution {
    public int solution(int[] nums) {
        //N마리 중 N/2마리 & 종류마다 번호로 구분
        //최대한 많은 종류의 포켓몬을 선택했을 때, 포켓몬 종류의 개수를 리턴
        
        //배열 [] 인덱스값+1 => 종류
        //원소 => 카운트
        
        //1. 종류별 카운트할 HashMap 생성
        Map<Integer, Integer> map = new HashMap<>();
        
        //2. 배열 돌면서 Key 비교해서 등록하거나 카운트
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        //3. 맵 크기와 N/2 중 작은 개수를 리턴: 고를 수 있는 최대값이 N/2
        return Math.min(map.size(), nums.length/2);
    }
}