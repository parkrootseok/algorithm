SELECT
    YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH, GENDER, COUNT(DISTINCT i.USER_ID) USERS

FROM
    USER_INFO i
        JOIN ONLINE_SALE s ON i.USER_ID = s.USER_ID
        
GROUP BY
    YEAR(SALES_DATE), MONTH(SALES_DATE), GENDER

HAVING GENDER IS NOT NULL

ORDER BY
    1, 2, 3