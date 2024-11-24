import java.util.*;
class Solution {
    /*
    Q. 완주하지 못한 선수의 이름 반환
    - 참여 선수 배열
    - 완주 선수 배열
    -> 참여 배열에는 있으나, 완주 배열에 없는 이름을 반환
    */
    public String solution(String[] participant, String[] completion) {
        return useMap(participant, completion);
    }
    
    private String useSort(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i=0; i<completion.length; i++) {
            if(!participant[i].equals(completion[i])) return participant[i];
        }
        
        return participant[participant.length-1];
    }
    
    private String useMap(String[] participant, String[] completion) {
        //{이름 : 완주횟수}
        Map<String, Integer> map = new HashMap<>();
        for(String c : completion) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for(String p : participant) {
            if(!map.containsKey(p)) return p;
            
            if(map.get(p) > 0) map.put(p, map.get(p)-1);
            else return p;
        }
        
        return null;
    }
}