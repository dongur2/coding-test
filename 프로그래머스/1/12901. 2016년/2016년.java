import java.time.LocalDate;

class Solution {
    private String real(int a, int b) {
        return LocalDate.of(2016,a,b).getDayOfWeek().toString().substring(0,3);
    }
    
    private String again(int a, int b) {
        int[] endOfMonth = {31,29,31,30,31,30,31,31,30,31,30,31};
        String[] MM = {"FRI","SAT","SUN","MON","TUE","WED","THU"};
        
        int today = 0;
        for(int i=0; i<a-1; i++){
            today += endOfMonth[i];
        }
        today += b-1;
        
        return MM[today%7];
    }
    
    public String solution(int a, int b) {
        return again(a,b);
    }
}