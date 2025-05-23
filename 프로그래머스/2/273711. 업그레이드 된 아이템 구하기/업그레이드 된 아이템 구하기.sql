-- 아이템 ID(ITEM_ID), 아이템 명(ITEM_NAME), 아이템의 희귀도(RARITY)
-- 희귀도 RARE인 아이템의 모든 다음 업그레이드 아이템
-- 아이템 ID 내림

SELECT i.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO i LEFT JOIN ITEM_TREE t
ON i.ITEM_ID = t.ITEM_ID
WHERE PARENT_ITEM_ID IN (
    -- 레어 아이템 아이디 목록
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY = 'RARE'
)
ORDER BY i.ITEM_ID DESC