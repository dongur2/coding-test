-- 12세 이하 여자환자의 환자이름, 환자번호, 성별코드, 나이, 전화번호
-- 전화번호 없으면 NONE
-- 나이 기준 내림차순 정렬, 나이가 같으면 환자이름 기준 오름차순 정렬

SELECT PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, 'NONE')
FROM PATIENT
WHERE GEND_CD = 'W'
AND AGE <= 12
ORDER BY AGE DESC, PT_NAME ASC;