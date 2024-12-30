WITH DISCOUNT AS (
    
    SELECT 
        history_id, c.CAR_TYPE, c.DAILY_FEE * (DATEDIFF(end_date, start_date) + 1) FEE,
        CASE
            WHEN 90 <= (DATEDIFF(end_date, start_date) + 1) THEN '90일 이상'
            WHEN 30 <= (DATEDIFF(end_date, start_date) + 1) THEN '30일 이상'
            WHEN 7 <= (DATEDIFF(end_date, start_date) + 1) THEN '7일 이상'
            ELSE '7일 이하'
        END DURATION_TYPE
    
    FROM
        CAR_RENTAL_COMPANY_RENTAL_HISTORY h
            JOIN CAR_RENTAL_COMPANY_CAR c ON h.CAR_ID = c.CAR_ID
            
    
    WHERE
        c.CAR_TYPE = '트럭'
    
)


SELECT
    HISTORY_ID, ROUND(FEE * (100 - IFNULL(DISCOUNT_RATE, 0)) / 100, 0) FEE

FROM
    DISCOUNT d
        LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
        ON d.DURATION_TYPE = p.DURATION_TYPE AND d.CAR_TYPE = p.CAR_TYPE
 
ORDER BY
    2 DESC, 1 DESC
        