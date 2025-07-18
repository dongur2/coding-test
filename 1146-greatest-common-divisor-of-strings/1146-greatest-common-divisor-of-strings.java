//두 문자열이 어떤 문자열 x를 여러 번 붙여 만든 것이라면 -> x 구하기 
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        //두 문자열이 같은 패턴으로 생성되었는지 확인
        //서로 순서를 바꿔 이어 붙였을 때 두 경우의 문자열이 같으면 같은 패턴
        //ABABAB, AB -> ABABABAB == ABABABAB
        if(!(str1+str2).equals(str2+str1)) return ""; //같은 패턴이 아니면 답 없음 

        //공통 패턴의 길이는 최대공약수
        //str1.length() = x.length의 배수 = str2.length() - x를 여러 번 이어붙였으므로 
        int gcdLen = gcd(str1.length(), str2.length()); 
        
        return str1.substring(0, gcdLen);
    }

    //최대공약수 
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}