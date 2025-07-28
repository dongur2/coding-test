/*
    여행 루트는 서로 다른 고도의 (n+1)개 지점으로 이루어져 있음
    바이커는 지점 0(고도가 0)에서 출발한다.

    길이가 n인 숫자 배열 gain이 주어진다. gain[i]는 i지점과 i+1지점 사이의 순수익이다. 
    지점 중 가장 높은 이익을 리턴한다.
 */
class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0; //0에서 시작 
        int res = 0;

        //포인트 차례로 이동 
        for(int i=0; i<gain.length; i++) {
            res += gain[i]; //누적합
            max = Math.max(max, res); //최대 이득 업데이트
        }

        return max;
    }
}