/*
    n번째 트리보나치?? 수 리턴
 */

import java.util.Map; import java.util.HashMap;
class Solution {
    Map<Integer, Integer> map = new HashMap<>(); //tri(n)에 대한 값 저장 

    public int tribonacci(int n) {
        map.put(0, 0); map.put(1, 1); map.put(2, 1);
        
        return tri(n);
    }

    int tri(int n) {
        if(n > 2) {
            int result = 0;
            for(int i=0; i<3; i++) {
                int r = map.containsKey(n-3+i) ? map.get(n-3+i) : tri(n-3+i); //맵에 저장된 값이 없으면 계산 
                if(!map.containsKey(n-3+i)) map.put(n-3+i, r); //맵에 없으면 저장 
                result += r;
            }
            return result;
        }

        //2이하면 기본값 리턴 
        return map.get(n);
    }
}