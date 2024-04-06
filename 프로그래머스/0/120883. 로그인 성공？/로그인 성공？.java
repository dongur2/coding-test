class Solution {
    public String solution(String[] id_pw, String[][] db) {
        for(int i=0; i<db.length; i++) {
            if(!id_pw[0].equals(db[i][0])) continue;
            else {
                if(!id_pw[1].equals(db[i][1])) {
                    return "wrong pw";
                } else {
                    return "login";
                }
            }
        }
        return "fail";
    }
}