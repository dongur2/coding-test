/*
    2 x n 보드판을 도미노로 채우기
    도미노: 2개짜리/3개짜리 -> 회전 가능

    2 x 0 = [x] 불가능 -> 1가지
    2 x 1 = [-] 1가지
    2 x 2 = [=], [||] 2가지
    2 x 3 = [|=],[|=],[|||],[ㄴㄱ](거꾸로+1) 5가지...

    열을 차례대로 넘어가면서 확인
    <i열의 2칸이 모두 비었을 경우>
        1) 세로 도미노 하나 놓기 -> i+1열로 이동, 빈 칸 없음
        2) 가로 도미노 하나 놓기 -> i+2열로 이동, 빈 칸 없음(아래 가로 도미노 자동)
        3) 트로미노 놓기 -> i+1열로 이동, 빈 칸 있음(i열은 모두 채우고 한 칸은 i+1열로 삐져나감)
    <i열의 1칸만 비었을 경우>
        1) 가로 도미노 하나 놓기 -> i열의 빈 1칸 채우고 i+1열로 이동, i+1열에 새로운 빈 칸 발생
        2) 트로미노 놓기 -> i열의 빈 1칸 채우고 i+1열도 모두 채우므로 i+2열로 이동, 빈 칸 없음
 */
class Solution {
    final int MOD = 1000000007;
    Long[][] dp;

    public int numTilings(int n) {
        dp = new Long[n+1][2];
        return (int)put(0, n, 0); //현재 열, 전체 열 길이, 빈 칸 존재 유무
    }

    long put(int col, int n, int existEmpty) {
        //열 모두 지났으면 확인 및 종료
        if(col == n) return (existEmpty == 1) ? 0 : 1;
        //보드판 외 -> 종료 
        if(col > n) return 0;

        //dp에 이미 계산된 값이 있으면 계산하지 않음
        if(dp[col][existEmpty] != null) return dp[col][existEmpty];

        if(existEmpty == 1) return dp[col][existEmpty] = (put(col+1, n, 1) + (2L * put(col+2, n, 0))) % MOD;
        return dp[col][existEmpty] = (put(col+1, n, 0) + put(col+2, n, 0) + put(col+1, n, 1)) % MOD;
    }
}