/*
    문자열 s, 숫자 k
    길이가 k인 s의 부분 문자열에서 포함할 수 있는 최대 모음 개수 리턴 
 */
class Solution {
    public int maxVowels(String s, int k) {
        int max = 0;
        int res = 0;

        int left = 0, right = k-1;

        //처음 영역 내 모음 개수 확인 
        for(int i=0; i<=right; i++) {
            if(isVowel(s.charAt(i))) res++;
        }
        max = res;

        //loop: left-모음확인/감소, right-모음확인/추가, 최대 개수 확인/업데이트 
        while(right < s.length()-1) {
            if(isVowel(s.charAt(left++))) res--;
            if(isVowel(s.charAt(++right))) res++;
            
            max = Math.max(max, res);
        }

        return max;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}