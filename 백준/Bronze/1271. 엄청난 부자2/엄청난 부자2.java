import java.util.*;
import java.io.*;
import java.math.BigDecimal;

class Main {
    //n원으로 m명에게 나누어줄 수 있는 액수와 남는 돈
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigDecimal m = new BigDecimal(st.nextToken());
        BigDecimal n = new BigDecimal(st.nextToken());

        System.out.println(m.divideToIntegralValue(n));
        System.out.print(m.remainder(n));
    }
}