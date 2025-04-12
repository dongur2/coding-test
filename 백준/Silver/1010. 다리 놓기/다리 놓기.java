import java.io.*;
import java.util.*;

public class Main {
  static int[][] comb = new int[31][31];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < t; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      //m개 중에 n개를 고르는 것과 같으므로 "조합"
      sb.append(getComb(m, n)).append("\n");
    }
    System.out.print(sb);
  }

  // 조합 mCn
  public static int getComb(int n, int r) {
    if (n == r || r == 0) return 1;
    if (comb[n][r] > 0) return comb[n][r]; //이미 구한경우 리턴

    return comb[n][r] = getComb(n - 1, r - 1) + getComb(n - 1, r);
  }
}
