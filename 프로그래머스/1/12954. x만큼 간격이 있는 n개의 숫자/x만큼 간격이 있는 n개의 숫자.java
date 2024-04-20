import java.util.stream.*;
class Solution {
    public long[] solution(int x, int n) {
        return LongStream.rangeClosed(0, n-1).map(l -> l = x + (x*l)).toArray();
    }
}