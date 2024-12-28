SELECT 
    i.ITEM_ID, ITEM_NAME, RARITY

FROM
    ITEM_INFO i

WHERE i.ITEM_ID IN (
    SELECT t.ITEM_ID
    FROM 
        ITEM_TREE t
        JOIN ITEM_INFO i ON t.PARENT_ITEM_ID = i.ITEM_ID AND i.RARITY = 'RARE'
)

ORDER BY
    1 DESC