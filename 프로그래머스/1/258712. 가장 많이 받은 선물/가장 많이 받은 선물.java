/*
    다음달에 가장 많은 선물을 받는 친구가 받을 선물 개수 구하기
    
    -선물 기록 있음: 더 많이 준 사람이 다음 달에 하나 받음
    -선물 기록 없음/있는데 서로 횟수 같음: 선물 지수가 더 큰 사람이 하나 받음
     *선물 지수 = 이번 달까지 자기가 친구들에게 준 선물 - 받은 선물
      -- 선물 지수도 같으면 선물 주고받지 않음
*/
import java.util.Map; import java.util.HashMap;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        //선물 준 목록 집계
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> giftIdx = new HashMap<>(); //선물 지수
        
        for(String name : friends) {
            map.put(name, new HashMap<>());
        }
        
        for(String gift : gifts) {
            String[] arr = gift.split(" ");
            String giver = arr[0];
            String receiver = arr[1];
            
            Map<String, Integer> gaveList = map.get(giver);
            gaveList.put(receiver, gaveList.getOrDefault(receiver, 0)+1);
            
            //선물 받은 사람 선물지수 감소, 선물 준 사람 선물지수 증가
            giftIdx.put(giver, giftIdx.getOrDefault(giver, 0)+1);
            giftIdx.put(receiver, giftIdx.getOrDefault(receiver, 0)-1);
        }
        
        Map<String, Integer> answerMap = new HashMap<>();
        
        //친구들 모두 확인
        for(String giver : friends) {
            Map<String, Integer> list = map.get(giver);
            
            for(String other : friends) {
                //자기자신이면 무시 
                if(giver.equals(other)) continue;
                
                int gave = list.getOrDefault(other, 0); 
                int received = map.get(other).getOrDefault(giver, 0); 
                
                //이 친구한테 선물 준 적 있을 때
                if(gave > 0) {
                    //선물 더 많이 줬으면 하나 받음
                    if(gave > received) answerMap.put(giver, answerMap.getOrDefault(giver, 0)+1);
                    
                    //선물 더 적게 줬으면 무시
                    else if(gave < received) continue;
                        
                    //동일하게 주고받았으면 선물 지수 비교
                    else {
                        int giverIdx = giftIdx.getOrDefault(giver, 0);
                        int receiverIdx = giftIdx.getOrDefault(other, 0);

                        //선물 지수가 클 경우에 선물 받음 
                        if(giverIdx > receiverIdx) answerMap.put(giver, answerMap.getOrDefault(giver, 0)+1);
                    }
                }
                
                //이 친구한테 선물 준 적 없을 때
                else {
                    //그 친구는 나한테 준 적 있으면 넘어감
                    if(map.get(other).get(giver) != null) continue;
                    
                    int giverIdx = giftIdx.getOrDefault(giver, 0);
                    int receiverIdx = giftIdx.getOrDefault(other, 0);

                    //선물 지수가 클 경우에 선물 받음 
                    if(giverIdx > receiverIdx) answerMap.put(giver, answerMap.getOrDefault(giver, 0)+1);
                }
                
            }
        }
        
        
        int answer = 0;
        
        for(int v : answerMap.values()) {
            answer = Math.max(answer, v);
        }
        
        return answer;
    }
}