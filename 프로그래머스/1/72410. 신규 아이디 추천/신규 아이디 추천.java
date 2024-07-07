class Solution {
    /*
    * 규칙에 맞지 않는 아이디를 입력받았을 경우, 입력된 아이디와 유사하고 규칙을 충족하는 아이디를 추천(리턴)
    - 처리 과정에 따라 차례차례 진행
    */
    public String solution(String new_id) {
        //1. 소문자로 치환
        String recommend = new_id.toLowerCase();

        //2. 가능한 글자 제외 모든 글자 제거
        recommend = recommend.replaceAll("[^a-z0-9-_.]", "");
        
        //3. 마침표가 2개 이상 연속 => 1개로 치환
        recommend = recommend.replaceAll("[.]{2,}", ".");
        
        //4. 마침표가 처음/끝이면 제거
        recommend = recommend.replaceAll("^[.]|[.]$", "");
        
        //5. 빈 문자열이면 a 대입
        recommend = recommend.isEmpty() ? "a" : recommend;

        StringBuilder recommendSb = new StringBuilder(recommend);
        
        //6. 16자 이상이면 첫 15개 문자 이후는 제거
        if(recommendSb.length() >= 16) {
            recommendSb.delete(15, recommendSb.length());
            //7. 마지막 글자가 마침표라면 제거
            if(recommendSb.charAt(recommendSb.length()-1) == '.') recommendSb.deleteCharAt(recommendSb.length()-1);
        }
        
        //8. 2자 이하라면 마지막 문자를 길이가 3이 될 때까지 중복하여 끝에 추가
        while(recommendSb.length() < 3) {
            recommendSb.append(recommendSb.charAt(recommendSb.length()-1));
        }
        
        return recommendSb.toString();
    }
}