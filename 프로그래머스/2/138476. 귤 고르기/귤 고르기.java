import java.util.*;

//귤 k개를 최대한 다양하게 골랐을 때 종류 개수
class Solution {
    public int solution(int k, int[] tangerine) {
        //종류별 개수 카운트
        Map<Integer, Integer> map = new HashMap<>();
        countType(map, tangerine);
        
        //개수 많은 것부터 정렬
        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[1] - t1[1];
            }
        });
        
        for(int type : map.keySet()) {
            int cnt = map.get(type);
            q.offer(new int[]{type, cnt});
        }
        
        //가장 많은 개수가 있는 종류부터 선택
        int box = 0, answer= 0;
        while(box < k) {
            int[] cur = q.poll();
            answer++; //새로운 종류 카운트
            box += cur[1];
        }
        
        return answer;
    }
    
    void countType(Map<Integer, Integer> map, int[] tangerine) {
        for(int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0)+1);
        }
    }
}