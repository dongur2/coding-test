import java.util.stream.*;

class Solution {
    public int solution(int n) {
        return IntStream.rangeClosed(1, n).filter(l -> n % l == 1).findFirst().orElse(n);
    }
}