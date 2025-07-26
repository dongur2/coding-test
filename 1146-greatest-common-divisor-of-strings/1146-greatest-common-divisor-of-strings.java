//주어진 두 문자열에서 반복되는 공통 문자열 중 가장 긴 문자열 리턴 
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        //두 문자열이 같은 문자열의 반복으로 이루어졌을 경우, 순서바꿔서 붙여도 같은 문자열
        if(!(str1+str2).equals(str2+str1)) return "";

        /*
            <같은 문자열의 반복>으로 이루어진 두 문자열은 <"몫"만 다르다>는 말과 같음
            그럼 최대공약수를 구해서 확인
         */
        int len = gcd(str1.length(), str2.length());
        return str1.substring(0, len);
    }

    private int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}