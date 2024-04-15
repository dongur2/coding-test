-- 의사이름, 의사ID, 진료과코드, 고용일자
-- 진료과가 흉부외과(CS)이거나 일반외과(GS)
-- 고용일자 내림차순 & 이름 오름차순

SELECT DR_NAME, DR_ID, MCDP_CD, DATE_FORMAT(HIRE_YMD, '%Y-%m-%d') AS HIRE_YMD
FROM DOCTOR
WHERE MCDP_CD = 'CS' OR MCDP_CD = 'GS'
ORDER BY HIRE_YMD DESC, DR_NAME ASC;