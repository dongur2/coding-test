import java.util.*;

class Solution {
    /*
    * 가능한 의상 조합의 최대 개수 리턴
    */
    public int solution(String[][] clothes) {
        //1. HashMap - {의상 종류(Key) : 개수(Value)} 생성
        Map<String, Integer> map = new HashMap<>();
        
        //2. 의상 종류별로 개수 카운트
        for(String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);            
        }
        
        //3. 의상 조합 계산
        /*
        * 각 종류에서 가능한 선택: 배열 길이 + 1
        * 각 종류별 선택을 곱한 값 - 1 -> 이 가능한 조합 수
        */
        int cnt = 1;
        for(int m : map.values()) {
            cnt *= m + 1;
        }
        return cnt - 1;
    }
}