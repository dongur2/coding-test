import java.util.*;
class Solution {
    /*
    Q. 완주하지 못한 선수의 이름 반환
    - 참여 선수 배열
    - 완주 선수 배열
    -> 참여 배열에는 있으나, 완주 배열에 없는 이름을 반환
    */
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i=0; i<completion.length; i++) {
            if(!participant[i].equals(completion[i])) return participant[i];
        }
        
        return participant[participant.length-1];
    }
}