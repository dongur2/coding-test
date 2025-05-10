import java.util.Map;
import java.util.HashMap;
/*
토핑 일렬로 배치 - 각 조각에 동일한 가짓수 토핑이 올라가면 공평 (케익 조각 개수는 상관없음)
1,2,1,3,1,4,1,2 
[1,2,1] [3,1,4,1,2] 불공평: 왼쪽은 2개, 오른쪽은 4개
[1,2,1,3] [1,4,1,2] 공평: 왼쪽 3개, 오른쪽 3개
--> Q. 공평하게 자르는 <방법 개수> ?
*/
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        //종류
        Map<Integer, Integer> lm = new HashMap<>();
        Map<Integer, Integer> rm = new HashMap<>();
        
        //처음 상태 - 왼쪽 하나, 오른쪽 나머지
        lm.put(topping[0], 1);
        for(int i=1; i<topping.length; i++) {
            rm.put(topping[i], rm.getOrDefault(topping[i], 0)+1);
        }
        
        //종류 개수 같으면 카운트 
        if(lm.size() == rm.size()) answer++;
        
        //왼쪽 인덱스 고정, 오른쪽 인덱스++ 하면서 양쪽의 종류 업데이트
        for(int right=1; right<topping.length; right++) {
            int kind = topping[right];
            
            //오른쪽에서 제외 (-1) - 0이면 삭제 
            int rightCnt = rm.get(kind) - 1;
            if(rightCnt == 0) rm.remove(kind);
            else rm.put(kind, rightCnt);
            
            //왼쪽에 추가
            lm.compute(kind, (k,v) -> v == null ? 1 : v+1);
            
            //종류 개수 같으면 카운트 
            if(lm.size() == rm.size()) answer++;           
        }
        
        return answer;
    }
}