import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int n;
    static boolean[][][][] visited;

    public int solution(int[][] board) {
        n = board.length;
        visited = new boolean[n][n][n][n];
        return bfs(board);
    }

    public static int bfs(int[][] board) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0, 1, 0});
        visited[0][0][0][1] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int sr = curr[0], sc = curr[1], er = curr[2], ec = curr[3], moves = curr[4];

            //도착
            if ((sr == n - 1 && sc == n - 1) || (er == n - 1 && ec == n - 1)) return moves;

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nSr = sr + dRow[i];
                int nSc = sc + dCol[i];
                int nEr = er + dRow[i];
                int nEc = ec + dCol[i];

                if (isValid(board, nSr, nSc, nEr, nEc)) {
                    int[] pos = sortPosition(nSr, nSc, nEr, nEc);
                    if (!visited[pos[0]][pos[1]][pos[2]][pos[3]]) {
                        visited[pos[0]][pos[1]][pos[2]][pos[3]] = true;
                        q.offer(new int[]{pos[0], pos[1], pos[2], pos[3], moves + 1});
                    }
                }
            }

            // 가로로 놓여있을 경우 회전 (22 '23' -> 22 '12', 22 '32'), (22 '23' -> 13 '23', 33 '23')
            if (sr == er) {
                for (int dir : new int[]{-1, 1}) {
                    int nsr1 = sr + dir, nsc1 = sc;
                    int nsr2 = er + dir, nsc2 = ec;

                    if (isInBoard(board, nsr1, nsc1) && isInBoard(board, nsr2, nsc2)) {
                        // 왼쪽 기준 회전 (왼쪽 고정)
                        int[] pos1 = sortPosition(sr, sc, nsr1, nsc1);
                        if (!visited[pos1[0]][pos1[1]][pos1[2]][pos1[3]]) {
                            visited[pos1[0]][pos1[1]][pos1[2]][pos1[3]] = true;
                            q.offer(new int[]{pos1[0], pos1[1], pos1[2], pos1[3], moves + 1});
                        }
                        // 오른쪽 기준 회전 (오른쪽 고정)
                        int[] pos2 = sortPosition(er, ec, nsr2, nsc2);
                        if (!visited[pos2[0]][pos2[1]][pos2[2]][pos2[3]]) {
                            visited[pos2[0]][pos2[1]][pos2[2]][pos2[3]] = true;
                            q.offer(new int[]{pos2[0], pos2[1], pos2[2], pos2[3], moves + 1});
                        }
                    }
                }
            }

            // 세로로 놓여있을 경우 회전 ((22 '32' -> 22 '21', 22 '23'), ('22' 32 -> '31' 32, '33' 32))
            if (sc == ec) {
                for (int dir : new int[]{-1, 1}) {
                    int nsr1 = sr, nsc1 = sc + dir;
                    int nsr2 = er, nsc2 = ec + dir;

                    if (isInBoard(board, nsr1, nsc1) && isInBoard(board, nsr2, nsc2)) {
                        // 위쪽 기준 회전 (위쪽 고정)
                        int[] pos1 = sortPosition(sr, sc, nsr1, nsc1);
                        if (!visited[pos1[0]][pos1[1]][pos1[2]][pos1[3]]) {
                            visited[pos1[0]][pos1[1]][pos1[2]][pos1[3]] = true;
                            q.offer(new int[]{pos1[0], pos1[1], pos1[2], pos1[3], moves + 1});
                        }
                        // 아래쪽 기준 회전 (아래쪽 고정)
                        int[] pos2 = sortPosition(er, ec, nsr2, nsc2);
                        if (!visited[pos2[0]][pos2[1]][pos2[2]][pos2[3]]) {
                            visited[pos2[0]][pos2[1]][pos2[2]][pos2[3]] = true;
                            q.offer(new int[]{pos2[0], pos2[1], pos2[2], pos2[3], moves + 1});
                        }
                    }
                }
            }
        }
        return 0; // 큐 다 돌고도 도착 못한 경우
    }

    public static boolean isValid(int[][] board, int sr, int sc, int er, int ec) {
        return isInBoard(board, sr, sc) && isInBoard(board, er, ec)
                && board[sr][sc] == 0 && board[er][ec] == 0;
    }

    public static boolean isInBoard(int[][] board, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0;
    }

    //정렬 (앞뒤가 뒤바뀔 수 있음)
    public static int[] sortPosition(int sr, int sc, int er, int ec) {
        if (sr < er || (sr == er && sc < ec)) return new int[]{sr, sc, er, ec};
        else return new int[]{er, ec, sr, sc};
    }
}
