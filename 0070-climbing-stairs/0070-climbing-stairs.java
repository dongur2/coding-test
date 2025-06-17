//가장 높은 계단으로 갈 수 있는 방법 개수
class Solution {
    static int[] ways;

    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;

        //방법 수 저장
        ways = new int[n+1];

        ways[1]=1; ways[2]=2;
        for(int i=3; i<=n; i++) {
            ways[i] = ways[i-1]+ways[i-2];
        }

        //계단 n층(꼭대기가 n층) - 0층(계단 전) -> 1층, 2층, ..., n층
        //1개 또는 2개씩 오르기 가능
        //(n-1)층까지 올 수 있는 방법 수 + (n-2)층까지 올 수 있는 방법 수
        return ways[n];
    }

    public int countWays(int top) {
        if(top == 1) return 1;
        if(top == 2) return 2;
        return countWays(top-1) + countWays(top-2);
    } 
}