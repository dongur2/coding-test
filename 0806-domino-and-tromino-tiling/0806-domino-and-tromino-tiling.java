/*
    도미노 타입 2가지: 2x1, 트로미노..??ㅋㅋ - 회전 가능
    숫자 n
    >>> 2xn 보드판에 타일을 배치할 수 있는 가짓수를 10^9+7로 나눈 나머지
    전체 보드판이 도미노로 모두 덮여야 함

 */
class Solution {
    final long mod = 1000000007;

    public int numTilings(int n) {
        int[] dp = new int[n+1];

        if(n==1) return 1;
        if(n==2) return 2;
        if(n==3) return 5;
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 5;

        for (int i = 4; i <= n; i++) {
            //dp[i]: 2*i 보드 덮는 방법 개수
            //dp[i-1]: 마지막 열만 도미노 세로로 덮음  
            //2*dp[i-1]: 마지막 2열을 도미노 가로로 덮음  
            //dp[i-3]: 마지막 3열을 트로미노로 덮음 
            dp[i] = (int)((2L * dp[i-1] + dp[i-3]) % mod);
        }
        return dp[n];
    }

    //현재 채우고 있는 열 i, 트로미노 때문에 반만 덮인 곳 존재 여부 halfEmpty
    long put(int n, int i, boolean halfEmpty) {
        //모든 열을 다 채웠으면 i == n, 이 시점에 반만 덮인 곳이 있으면 실패, 아니면 성공 
        if(i == n) return halfEmpty ? 0 : 1;
        //n을 초과하면 불가능 
        if(i > n) return 0;

        //이전 열이 반만 덮였다면, 현재 덮는 방법+다음 열에서 이어서 덮는 방법
        if(halfEmpty) return (put(n, i+1, false)+put(n, i+1, true)) % mod;

        //이전 열이 다 채워져있다면, 이번 열도 채우는 방법 + 가로로 채우는 방법 + 트로미노로 배치 방법 
        return (put(n, i+1, false) + put(n, i+2, false)+ 2 * put(n, i+2, true)) % mod;
    }
}