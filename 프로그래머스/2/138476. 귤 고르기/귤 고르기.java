import java.util.*;

/*
- k개를 골랐을 때 최소 종류 개수
- 개수 많은 종류 선택 최우선
*/
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>(); //크기:개수
        
        //크기별 개수 카운트
        for(int t:tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        //개수 적은 순서대로 정렬
        int[] table = new int[map.size()];
        int idx = 0;
        for(int value : map.values()) {
            table[idx++] = value;
        }
        Arrays.sort(table);
        
        //카운트
        int cnt = 0, type = 0;
        for(int i=table.length-1; i>=0; i--) {
            if(cnt >= k) break;
            else {
                cnt += table[i];
                table[i] = 0;
                type++;
            }
        }
        
        return type;
    }
}