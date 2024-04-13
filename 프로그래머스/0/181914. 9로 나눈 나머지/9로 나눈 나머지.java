class Solution {
    public int solution(String number) {
        int sum = 0;
        for(char num : number.toCharArray()) {
            sum += Character.getNumericValue(num);
        }
        
        return sum % 9;
    }
}