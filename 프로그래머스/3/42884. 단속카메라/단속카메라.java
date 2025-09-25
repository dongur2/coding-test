import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        //차량의 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> o1[1]-o2[1]);

        //첫 번째 카메라
        int camera = 1; 
        int loc = routes[0][1]; //첫 차량의 진출 지점에 설치

        //나머지 차량들을 순회
        for (int i=1; i<routes.length; i++) {
            //현재 차량의 진입 지점이 카메라 위치보다 뒤에 있을 경우 (기존 카메라로 커버되지 않는다면)
            if (routes[i][0] > loc) {
                camera++; //카메라 추가 설치
                loc = routes[i][1]; //새로운 카메라를 현재 차량의 진출 지점에 설치
            }
        }
        
        return camera;
    }
}