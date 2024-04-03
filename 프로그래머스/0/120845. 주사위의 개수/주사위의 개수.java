class Solution {
    public int solution(int[] box, int n) {
        int floorOfBox = (box[0] / n) * (box[1] / n);
        int heightOfBox = box[2] / n;
        return floorOfBox * heightOfBox;
    }
}