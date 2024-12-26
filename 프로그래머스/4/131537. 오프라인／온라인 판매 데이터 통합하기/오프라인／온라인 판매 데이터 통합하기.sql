SELECT
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d') SALES_DATE, PRODUCT_ID, USER_ID, SUM(SALES_AMOUNT) AS SALES_AMOUNT
FROM
    ONLINE_SALE

WHERE
    YEAR(SALES_DATE) = '2022' AND MONTH(SALES_DATE) = '3'

GROUP BY 
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d'), PRODUCT_ID, USER_ID
    
UNION

SELECT
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d'), PRODUCT_ID, NULL, SUM(SALES_AMOUNT)
FROM
    OFFLINE_SALE
WHERE
    YEAR(SALES_DATE) = '2022' AND MONTH(SALES_DATE) = '3'
    
GROUP BY 
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d'), PRODUCT_ID
    
ORDER BY 1, 2, 3

