/*
    주어진 문자열에서 단어 배열을 거꾸로 바꿔서 리턴 (공백 기준)
    앞뒤 공백은 없애고 단어 사이 중복 공백은 하나로 
 */
class Solution {
    public String reverseWords(String s) {
        String trimmed = s.trim(); //앞뒤 공백 제거
        System.out.println(trimmed);

        String[] arr = trimmed.split(" "); //공백 기준 단어 분할
        for(String a:arr) {
            System.out.print(a+", ");
        }

        StringBuilder sb = new StringBuilder();
        for(int i=arr.length-1; i>=0; i--) {
            if(arr[i].equals("")) continue; //공백 여러 개일 경우 공백도 원소 -> 무시

            sb.append(arr[i]);
            if(i>0) sb.append(" ");
        }

        return sb.toString();
    }
}