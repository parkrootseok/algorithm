WITH CAR AS (
    
    SELECT 
        HISTORY_ID, c.CAR_ID, c.CAR_TYPE, 
        c.DAILY_FEE * (DATEDIFF(END_DATE, START_DATE) + 1) TOTAL_FEE,
        CASE
            WHEN 90 <= (DATEDIFF(END_DATE, START_DATE) + 1) THEN '90일 이상'
            WHEN 30 <= (DATEDIFF(END_DATE, START_DATE) + 1) THEN '30일 이상'
            WHEN 7 <= (DATEDIFF(END_DATE, START_DATE) + 1) THEN '7일 이상'
            ELSE '7일 미만'
        END DURATION_TYPE

    
    FROM
        CAR_RENTAL_COMPANY_RENTAL_HISTORY h
        JOIN CAR_RENTAL_COMPANY_CAR c ON h.CAR_ID = c.CAR_ID
    
    WHERE
        CAR_TYPE = '트럭'
    
)

SELECT
    HISTORY_ID, 
    ROUND(TOTAL_FEE * (100 - IFNULL(p.DISCOUNT_RATE, 0)) / 100, 0) FEE

FROM
    CAR c
    LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
    ON c.CAR_TYPE = p.CAR_TYPE AND c.DURATION_TYPE = p.DURATION_TYPE
    
ORDER BY
    2 DESC, 1 DESC