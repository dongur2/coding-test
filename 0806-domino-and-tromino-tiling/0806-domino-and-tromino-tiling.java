class Solution {
    final int MOD = 1000000007;

    Long[][] dp; //dp[col][existHalf]

    public int numTilings(int n) {
        dp = new Long[n+1][2];
        return (int)put(0, n, 0); //현재 열, 열 길이, 빈 칸 존재     
    }

    long put(int col, int n, int existHalf) {
        //base case
        if(col == n) return existHalf == 1 ? 0 : 1; //열 테두리 도착 => 빈 칸 존재하는지 확인 
        if(col > n) return 0; //보드판 탈출

        //저장된 값이 없을 때만 연산, 저장된 값 있으면 즉시 리턴 
        if(dp[col][existHalf] != null) return dp[col][existHalf];

        long ans; 

        //현재 열이 모두 비었으면 3가지 가능
        if(existHalf == 0) {
            //3가지 경우에서 가능한 경우의 수를 모두 더해서 리턴 
            //1. 세로로 도미노 하나 놓기 -> 다음 열로 이동 (빈 칸 없음)
            //2. 가로로 도미노 하나 놓기 -> 자동으로 밑에도 가로 도미노 배치 -> 다다음 열로 이동 (빈 칸 없음)
            //3. 트로미노 놓기 -> 현재 열은 꽉 채우고 다음 열에 빈 칸 발생 -> 다음 열로 이동 + 빈 칸 있음!!!
            ans = (put(col+1, n, 0) + put(col+2, n, 0) + put(col+1, n, 1)) % MOD;
        }

        //현재 열에 반쪽짜리 도미노가 있다면 -- 전 열에서 놓은 트로미노 조각 -> 2가지 가능
        else {
            //2가지 경우에서 가능한 경우의 수를 모두 더해서 리턴 
            //1. 뒤집은 트로미노를 배치해서 맞물려 채우기 -> 다음 열도 채워짐 -> 다다음 열로 이동 (빈 칸 없음)
            //1-1. 이 경우 트로미노가 두 방향으로 놓여있을 수 있으므로 *2 
            //2. 가로 도미노 놓기 -> 현재 열은 채워지지만, 다음 열에 반쪽만 채워지면서 새로운 빈 칸 발생 -> 다음 열로 이동 + 빈 칸 있음!!!
            ans = (2L * put(col+2, n, 0) + put(col+1, n, 1)) % MOD;
        }

        return dp[col][existHalf] = ans;
        
    }
    
}