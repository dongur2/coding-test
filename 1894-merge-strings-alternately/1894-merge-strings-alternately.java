//주어진 단어 2개를 병합하여 리턴 (첫번째 단어로 시작)
//한글자씩 차례로 끼워서 병합 
class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();

        int len1 = word1.length();
        int len2 = word2.length();

        //두 단어 중 긴 단어의 길이로 반복문 수행 
        int len = Math.max(len1, len2);
        
        //현재 인덱스가 단어 길이를 초과하지 않는지 확인한 뒤 글자 추가 
        for(int i=0; i<len; i++) {
            //첫번째 단어 글자 추가
            if(i < len1) sb.append(word1.charAt(i));
            //두번째 단어 글자 추가 
            if(i < len2) sb.append(word2.charAt(i));
        }

        return sb.toString();
    }
}