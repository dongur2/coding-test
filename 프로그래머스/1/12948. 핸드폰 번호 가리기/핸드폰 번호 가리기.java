class Solution {
    public String solution(String phone_number) {
        // return "*".repeat(phone_number.length()-4) + phone_number.substring(phone_number.length()-4);
        
        char[] chars = phone_number.toCharArray();
        for(int i=0; i<phone_number.length()-4; i++) {
            chars[i] = '*';
        }
        return String.valueOf(chars);
    }
}