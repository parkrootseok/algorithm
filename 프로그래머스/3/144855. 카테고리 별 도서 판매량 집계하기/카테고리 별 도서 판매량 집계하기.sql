-- 코드를 입력하세요
SELECT CATEGORY, SUM(SALES)

FROM BOOK b
    LEFT JOIN BOOK_SALES bs ON bs.BOOK_ID = b.BOOK_ID 
                AND bs.SALES_DATE like ('2022-01%')
                
GROUP BY CATEGORY

ORDER BY CATEGORY