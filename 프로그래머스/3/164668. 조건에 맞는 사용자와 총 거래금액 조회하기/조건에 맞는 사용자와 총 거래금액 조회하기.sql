SELECT ugu.USER_ID, ugu.NICKNAME, ugb.PRICE as TOTAL_SALES

FROM USED_GOODS_USER ugu 
    LEFT JOIN (SELECT WRITER_ID, SUM(PRICE) PRICE
               FROM USED_GOODS_BOARD
               WHERE STATUS = 'DONE'
               GROUP BY WRITER_ID
               HAVING 700000 <= SUM(PRICE)
              ) ugb ON ugu.USER_ID = ugb.WRITER_ID

WHERE ugb.PRICE IS NOT NULL

ORDER BY TOTAL_SALES


