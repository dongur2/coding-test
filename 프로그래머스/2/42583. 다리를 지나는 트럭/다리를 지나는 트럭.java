import java.util.*;

class Solution {
    /*
    문제: "일차선 다리"를 "정해진 순서대로" 트럭 "여러 대"가 건너는데, "모든 트럭이 다리를 건너려면 최소 몇 초"가 걸리는지 리턴
    - 순서는 주어지는 트럭별 무게 배열대로: truck_weights[i] -> 큐에 저장?
    ** 다리의 길이는 bridge_length라는 걸 유의
    
    1. 다리 역할을 하는 큐를 생성, 시간(초) 변수, 다리 위 트럭 무게의 합 변수 생성
    2. 트럭 배열 앞에서부터 한 대씩 순회: for 인덱스 - O(N) 
        3. while 무한 반복
            4. 다리가 비어있으면 새로운 트럭 다리에 진입: +1초, 무게합 += 트럭 무게 & 다음 트럭으로 넘어감
            5. 다리가 꽉 차있으면 맨 앞 트럭은 밖으로: 무게합 -= 트럭 무게
            6. 다리에 빈 공간이 있으면, 새로운 트럭을 더한 무게합이 최대 하중을 초과하는지 확인
                :초과하면 다리에 0 추가 & +1초
                :초과하지 않으면 새로운 트럭 추가 & +1초 & 무게합 += 트럭 무게 & 다음 트럭으로 넘어감
    4. 마지막 트럭을 다리에 올리면 for 반복문이 종료되므로, 지금까지 더한 시간에 다리 길이를 더해준 시간을 리턴
    */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //1. 큐, 시간, 무게합 변수 생성
        Queue<Integer> bridge = new ArrayDeque<>();
        int sec = 0, weights = 0;
        
        //2. 트럭 배열을 앞에서부터 한 대씩 순회
        for(int truck : truck_weights) {
            while(true) {
                //3. 다리에 빈 자리가 없으면, 맨 앞 트럭만 다리를 완전히 넘어가 밖으로
                if(!bridge.isEmpty() 
                   && bridge.size() == bridge_length) weights -= bridge.poll();
                
                //4. 다리에 빈 공간이 있으면
                else {
                    // 4-1. 새로운 트럭을 다리 위에 올려도 무게합이 다리 최대 하중을 초과하지 않을 때만 새로운 트럭 진입 => 다음 트럭으로 넘어가기 위해 반복문 종료
                    if(weights + truck <= weight) {
                        bridge.add(truck);
                        weights += truck; sec++;
                        break;
                        
                    // 4-2. 새로운 트럭을 올리면 최대 하중을 초과할 경우에는 올리지 않음
                    } else {
                        bridge.add(0); sec++;
                    }
                }
            }
        }
        //5. 마지막 트럭을 다리에 올리면 반복문 종료 => 마지막 트럭이 다리를 건너는 시간을 기존 시간에 더하여 리턴
        return sec + bridge_length;
    }
}