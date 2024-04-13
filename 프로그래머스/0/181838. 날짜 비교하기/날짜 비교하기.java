import java.time.*;

class Solution {
     private int my(int[] date1, int[] date2) {
        for(int i=0; i<3; i++) {
            if(date1[i] < date2[i]) return 1;
            else if (date1[i] > date2[i]) return 0;
        }
        return 0;
    }
    
    private int useDate(int[] date1, int[] date2) {
        LocalDate dateOne = LocalDate.of(date1[0], date1[1], date1[2]);
        LocalDate dateTwo = LocalDate.of(date2[0], date2[1], date2[2]);
        
        return dateOne.isBefore(dateTwo)? 1:0;
    }
    
    public int solution(int[] date1, int[] date2) {
        return useDate(date1, date2);
    }
}