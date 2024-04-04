class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        String[] numbers = my_string.replaceAll("[^0-9]", " ").split(" ");
        for(String num : numbers) {
            if(!num.trim().equals("")) {
                answer += Integer.parseInt(num); 
            }
        }
        
        return answer;
    }
}