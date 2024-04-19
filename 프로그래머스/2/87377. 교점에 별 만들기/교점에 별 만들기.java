import java.util.*;

class Solution {
    // 좌표를 나타낼 객체
    private static class Point {
        public final long x, y;
        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 교점 구하기 메서드
    private Point intersection(long a, long b, long e, long c, long d, long f) {
        double x = (double)(b*f - e*d)/(a*d - b*c);
        double y = (double)(e*c - a*f)/(a*d - b*c);
        return (x % 1 != 0 || y % 1 != 0)?  null : new Point((long)x, (long)y); // 정수가 아니라면 null 반환
    }
    
    // x좌표 y좌표의 최솟값 구하기
    private Point getMinimumPoint(List<Point> points) {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;
        
        for(Point p : points) {
            x = (p.x < x)? p.x : x;
            y = (p.y < y)? p.y : y;
        }
        return new Point(x, y);
    }
    
    // x좌표 y좌표의 최댓값 구하기
    private Point getMaximumPoint(List<Point> points) {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;
        
        for(Point p : points) {
            x = (p.x > x)? p.x : x;
            y = (p.y > y)? p.y : y;
        }
        return new Point(x, y);
    }
    
    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        
        // 1. 모든 직선쌍의 교점 구하기 
        // 1-a. 정수일 경우에만 저장
        for(int i=0; i<line.length; i++) {
            for(int j=i+1; j<line.length; j++) {
                Point point = intersection(line[i][0], line[i][1], line[i][2],
                                                  line[j][0], line[j][1], line[j][2]);
                if(point != null) points.add(point);
            }
        }        

        // 2. x, y 좌표의 최솟값, 최댓값 구하기
        Point min = getMinimumPoint(points);
        Point max = getMaximumPoint(points);
        
        // 3. 구한 값으로 배열 크기 정하기
        int width = (int)(max.x - min.x + 1);
        int height = (int)(max.y - min.y + 1);
        
        char[][] arr = new char[height][width];
        for(char[] a : arr) {
            Arrays.fill(a, '.');
        }
        
        // 4. 별표 표시
        for(Point p : points) {
            int x = (int)(p.x - min.x); // 배열을 최소한으로 줄임 => 변환
            int y = (int)(max.y - p.y); // 2차원 배열은 일반 좌표와 y축이 반대
            arr[y][x] = '*';
        }
        
        // 5. String[] 배열 반환
        // return Arrays.stream(arr).map(String::valueOf).toArray(String[]::new);
        String[] answer = new String[arr.length];
        for(int i=0; i<answer.length; i++) {
            answer[i] = String.valueOf(arr[i]);
        }
        return answer;
    }
}