import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] room;
    static int N, M;

    static int[] rowArr = new int[] {0, 1, 0, -1};
    static int[] colArr = new int[] {1, 0, -1, 0};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        makeRoom();
        buildWallsAndCountSafeArea(0, 0, 0, new boolean[N][M]);
        System.out.println(answer);
    }

    //연구소 생성
    private static void makeRoom() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //벽 세우고 안전 지대 개수 카운트
    private static void buildWallsAndCountSafeArea(int row, int col, int wallCnt, boolean[][] visited) {
        if(wallCnt == 3) {
            int[][] copied = copyRoom();
            spreadVirus(copied, new boolean[N][M]);

            int safety = countSafeArea(copied);

            answer = Math.max(answer, safety);

            return;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(room[i][j] == 0 && !visited[i][j]) {
                    room[i][j] = 1; visited[i][j] = true;
                    buildWallsAndCountSafeArea(row+1, col+1, wallCnt+1, visited);
                    room[i][j] = 0; visited[i][j] = false;
                }
            }
        }
    }

    //바이러스 전파
    static void spreadVirus(int[][] copied, boolean[][] visited) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {

                if(copied[i][j] == 2 && !visited[i][j]) {
                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[] {i, j});

                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        int curR = cur[0];
                        int curC = cur[1];

                        for(int l=0; l<4; l++) {
                            int nxtR = curR + rowArr[l];
                            int nxtC = curC + colArr[l];

                            if(isValid(copied, nxtR, nxtC, visited)) {
                                copied[nxtR][nxtC] = 2;
                                q.offer(new int[]{nxtR, nxtC});
                                visited[nxtR][nxtC] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    //바이러스 전파 시 상하좌우 유효값 확인
    static boolean isValid(int[][] copied, int r, int c, boolean[][] visited) {
        return r >= 0 && r < N && c >= 0 && c < M && !visited[r][c] && copied[r][c] == 0;
    }

    //연구소 깊은 복사
    static int[][] copyRoom() {
        int[][] copied = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copied[i][j] = room[i][j];
            }
        }

        return copied;
    }

    //안전지대 개수 카운트
    static int countSafeArea(int[][] copied) {
        int cnt = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(copied[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    static void printRoom() {
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }
}
