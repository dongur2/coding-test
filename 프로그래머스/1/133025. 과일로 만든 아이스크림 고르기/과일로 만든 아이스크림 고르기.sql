-- 맛만 출력
-- 상반기 아이스크림 총주문량 >= 3000 && 주성분이 과일
-- 총주문량이 큰 순서대로

SELECT f.FLAVOR
FROM FIRST_HALF f LEFT JOIN ICECREAM_INFO i
ON f.FLAVOR = i.FLAVOR
WHERE f.TOTAL_ORDER >= 3000 AND i.INGREDIENT_TYPE = 'fruit_based'
ORDER BY f.TOTAL_ORDER DESC;