class Solution {
    // * Integer.bitCount(num): num의 2진수에서 1의 갯수 리턴
    public int solution(int n) {
        int nxt = n;
        int countOneOfN = Integer.bitCount(n);

        while(true){
            if(Integer.bitCount(++nxt) == countOneOfN) return nxt;
        }
    }
}