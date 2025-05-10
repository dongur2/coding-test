import java.util.*;
/*
귤 k개를 골랐을 때 최소 종류 개수 
*/
class Solution {
    //귤 개수 10^5, 귤 종류 10^7
    public int solution(int k, int[] tangerine) {
        //종류별로 개수 세서 저장 - {종류:개수}
        Map<Integer, Integer> map = new HashMap<>();
        for(int t:tangerine) {
            map.put(t, map.getOrDefault(t, 0)+1);
        }
        
        //종류별 개수만 추출
        List<Integer> list = new ArrayList<>(map.values());
        
        //정렬
        list.sort(Collections.reverseOrder());
        
        int sum = 0, answer = 0;
        for(int l:list) {
            sum += l; answer++;
            if(sum >= k) return answer;
        }
        return answer;
    }
}