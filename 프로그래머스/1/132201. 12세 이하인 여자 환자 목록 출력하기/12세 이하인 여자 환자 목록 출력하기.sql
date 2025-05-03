-- 환자이름, 환자번호, 성별코드, 나이, 전화번호
-- 12세 이하 여자
-- 전화번호 없으면 none
-- 나이 내림차순, 같으면 이름 오름차순
SELECT PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, 'NONE') AS TLNO
FROM PATIENT
WHERE AGE <= 12 AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME ASC;