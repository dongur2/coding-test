-- 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수
-- 서울 위치
-- 리뷰 평균점수: 소수점 세 번째 자리에서 반올림 
-- 평균점수 내림차순, 즐겨찾기 내림차순

SELECT i.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, ROUND(AVG(REVIEW_SCORE), 2) SCORE
FROM REST_INFO i JOIN REST_REVIEW r
ON i.REST_ID = r.REST_ID
WHERE i.REST_ID IN (
    -- 서울 위치한 식당
    SELECT REST_ID FROM REST_INFO  
    WHERE SUBSTRING(ADDRESS, 1, 2) = '서울'
)
GROUP BY i.REST_ID
ORDER BY SCORE DESC, FAVORITES DESC;
