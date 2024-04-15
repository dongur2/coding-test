-- 아이스크림 맛 조회
-- 상반기 총 주문량 내림차순 & 출하 번호 오름차순

SELECT FLAVOR FROM FIRST_HALF 
ORDER BY TOTAL_ORDER DESC, SHIPMENT_ID ASC;