-- 평균 요금만 출력
-- 자동차 종류가 SUV
-- 평균 일일 대여 요금: 소수 첫번째 자리에서 반올림

SELECT ROUND(SUM(DAILY_FEE)/COUNT(*), 0) AS 'AVERAGE_FEE'
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_TYPE = 'SUV';