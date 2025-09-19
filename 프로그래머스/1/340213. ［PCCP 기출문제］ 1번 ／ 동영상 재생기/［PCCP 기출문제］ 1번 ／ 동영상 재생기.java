//최종 동영상 위치 리턴 
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int len = convertToSec(video_len); //영상 길이
        int curr = convertToSec(pos); //현재 위치
        int opStart = convertToSec(op_start); //오프닝 시작
        int opEnd = convertToSec(op_end); //오프닝 종료
        
        for(String command : commands) {
            if(curr >= opStart && curr <= opEnd) curr = opEnd; //현재 재생 위치가 오프닝 구간인 경우: 자동으로 오프닝 종료 위치로

            if(command.equals("prev")) curr = curr-10 < 0 ? 0 : curr-10; //10초 미만으로 지났을 경우 0으로
            else if(command.equals("next")) curr = curr+10 > len ? len : curr+10; //10초 미만으로 남았을 경우 끝으로
        }

        if(curr >= opStart && curr <= opEnd) curr = opEnd; //오프닝 구간 마지막으로 확인
        
        
        int min = curr / 60;
        int sec = curr % 60;
        
        StringBuilder sb = new StringBuilder();
        if(min / 10 == 0) sb.append("0");
        sb.append(min+":");
        if(sec / 10 == 0) sb.append("0");
        sb.append(sec);
        
        return sb.toString();
    }
    
    int convertToSec(String str) {
        String[] arr = str.split(":");
        int min = Integer.parseInt(arr[0]);
        int sec = Integer.parseInt(arr[1]);
        return (min * 60) + sec;
    }
}