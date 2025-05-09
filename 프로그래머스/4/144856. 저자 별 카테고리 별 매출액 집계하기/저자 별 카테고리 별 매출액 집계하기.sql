-- 저자별, 카테고리별 매출액
-- 저자id, 저자명, 카테고리, 매출액
-- 저자id 오름차순, 카테고리 내림차순

-- 카테고리별 작가의 총 매출
SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, SUM(p.SALE_PRICE) TOTAL_SALES
FROM BOOK b JOIN 
    ( -- 각 책의 매출 
        SELECT b.BOOK_ID, (b.PRICE * s.SALES) SALE_PRICE
        FROM BOOK b JOIN BOOK_SALES s ON b.BOOK_ID = s.BOOK_ID
        WHERE s.SALES_DATE BETWEEN DATE('2022-01-01') AND DATE('2022-01-31')
    ) p ON b.BOOK_ID = p.BOOK_ID
    JOIN AUTHOR a ON b.AUTHOR_ID = a.AUTHOR_ID
GROUP BY a.AUTHOR_ID, b.CATEGORY
ORDER BY a.AUTHOR_ID, b.CATEGORY DESC