import java.util.*;

class Solution {
    public String solution(String bin1, String bin2) {
        int num1 = Integer.parseInt(bin1, 2); // 2진수 -> 10진수
        int num2 = Integer.parseInt(bin2, 2);
        return Integer.toBinaryString(num1 + num2); // 10진수 -> 2진수
    }
}