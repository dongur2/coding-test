-- 2022년 5월에 예약한 환자 수를 진료과코드 별로 조회

-- 2022년 5월 예약 환자
SELECT MCDP_CD 진료과코드, COUNT(1) 5월예약건수 FROM APPOINTMENT
WHERE DATE_FORMAT(APNT_YMD, '%Y-%m') = '2022-05'
GROUP BY MCDP_CD
ORDER BY COUNT(1), MCDP_CD