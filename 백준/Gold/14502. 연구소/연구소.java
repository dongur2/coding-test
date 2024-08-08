import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int maxSafe = Integer.MIN_VALUE; // 안전 영역 최대값
    static String[][] map;

    static int[] rowArr = {0, 1, 0, -1}, colArr = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 지도의 세로 크기 N과 가로 크기 M
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        //둘째 줄부터 N개의 줄에 지도의 모양이 주어진다.
        //0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수
        map = new String[n][m];
        for(int i=0; i<n; i++) {
            map[i] = br.readLine().split(" ");
        }

        buildWalls(new boolean[n][m], 0);

        System.out.println(maxSafe);
    }

    /* 3개 벽 세울 수 있는 모든 경우의 수 */
    static void buildWalls(boolean[][] wallMap, int num) {
        //벽을 3개 모두 세웠을 경우 바이러스 감염
        if(num == 3) {
            //맵 복사 - 수정하기 위함
            String[][] anotherMap = new String[n][m];
            for(int r=0; r<n; r++) {
                for(int c=0; c<m; c++) {
                    anotherMap[r][c] = map[r][c];
                }
            }

            //감염
            infect(anotherMap);

            //안전영역 카운트
            int cnt = 0;
            for(int r=0; r<n; r++) {
                for(int c=0; c<m; c++) {
                    if(anotherMap[r][c].equals("0")) cnt++;
                }
            }
            maxSafe = Math.max(cnt, maxSafe);
            return;
        }

        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                //모든 노드를 순회하며, 방문한 적 없는 빈 칸(0)인 경우 벽 세우기(방문 체크)
                if(map[r][c].equals("0") && !wallMap[r][c]) {
                    wallMap[r][c] = true; map[r][c] = "1";
                    buildWalls(wallMap, num+1); //그 다음 벽 세우는 하청
                    wallMap[r][c] = false; map[r][c] = "0"; //벽 없애기(방문 무효)
                }
            }
        }
    }

    //감염 BFS
    static void infect(String[][] anotherMap) {
        boolean[][] visited = new boolean[n][m];

        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                //바이러스가 있는 지점이면 감염 시작
                if(anotherMap[r][c].equals("2") && !visited[r][c]) {
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{r, c});

                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        int curR = cur[0], curC = cur[1];

                        //상하좌우로 감염
                        for(int i=0; i<4; i++) {
                            int nxtR = curR + rowArr[i], nxtC = curC + colArr[i];

                            if(isValid(nxtR, nxtC) && !anotherMap[nxtR][nxtC].equals("1") && !visited[nxtR][nxtC]) {
                                anotherMap[nxtR][nxtC] = "2";
                                q.offer(new int[]{nxtR, nxtC}); visited[nxtR][nxtC] = true;
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
