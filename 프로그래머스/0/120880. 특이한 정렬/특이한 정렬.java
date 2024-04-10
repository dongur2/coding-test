import java.util.*;

class Solution {
    public int[] solution(int[] numlist, int n) {
        // n과 가까운 수부터 정렬: 거리가 같으면 더 큰 수가 앞
        Map<Double, Integer> map = new HashMap<>();
        
        // key(n과의 차이 - 음수일 경우 +0.5) : value(숫자)
        for(int num : numlist) {
            double distance = num - n;
            if(distance < 0) map.put(Math.abs(distance)+0.5, num);
            else map.put(distance, num);
        }
        
        // List에서 n과의 차이 오름차순 정렬
        List<Double> keys = new ArrayList<>();
        for(double key : map.keySet()) {
            keys.add(key);
        }
        keys.sort(Comparator.naturalOrder());
        
        // 정렬된 n과의 차이 key를 이용해 map에서 value가져와 순서대로 저장
        int[] answer = new int[keys.size()];
        for(int i=0; i<keys.size(); i++) {
            answer[i] = map.get(keys.get(i));
        }
        
        return answer;
    }
}