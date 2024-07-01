import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        // 배열의 전화번호 중 다른 번호의 접두어가 있을 경우, false 리턴

        //1. 배열을 통째로 비교하게 되면 시간 초과 -> 정렬 후 비교
        Arrays.sort(phone_book);

        //2. 인접한 위치를 비교
        for(int i=0; i<phone_book.length-1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}