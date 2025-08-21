/*
    다리
    -정해진 순서
    -최대 트럭 bridge_length
    -최대 무게 weight: 완전히 안올랐으면 무시 
    >>> 모든 트럭이 다리를 건너는 데 걸리는 최소 시간
    
    1초에 한칸씩 이동
    큐 == 다리
*/
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0, w = 0;
        
        //다리 (무게만 저장)
        Deque<Integer> bridge = new ArrayDeque<>();
        
        for(int truck:truck_weights) {
            
            while(true) {
                //다리가 꽉 찼으면 맨 앞 트럭 탈출 
                if(!bridge.isEmpty() && bridge.size() == bridge_length) w -= bridge.poll();
                
                //다리에 자리 존재 
                else {
                    //무게 확인하고 추가 
                    if(weight >= w + truck) {
                        bridge.offer(truck); w += truck; time++;
                        break; //트럭 추가했으면 반복 종료하고 다음 트럭 확인
                    }
                    //무게 초과 -> 새로 추가 없이 1초 동안 기존 트럭만 이동(0 추가)
                    else {
                        bridge.offer(0); time++;
                    }
                }
            }
            
        }       
        
        return time + bridge_length; //마지막 트럭 시간까지 추가 
    }
    
}