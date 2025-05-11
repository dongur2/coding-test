-- 22/10/16에 대여중/대여가능 구분해 표시 16일에 반납예정이어도 대여중
-- 아이디 내림차순

SELECT DISTINCT CAR_ID,
    IF (
        EXISTS (
           -- 대여중인 차
            SELECT 1 FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS sub
            WHERE sub.CAR_ID = main.CAR_ID AND
                  sub.START_DATE <= DATE('2022-10-16') AND sub.END_DATE >= DATE('2022-10-16')
        ), '대여중', '대여 가능'
    ) AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS main
ORDER BY CAR_ID DESC


