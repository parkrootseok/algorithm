SELECT
    c.CAR_ID,
    c.CAR_TYPE,
    ROUND(c.DAILY_FEE * 30 * (100 - p.DISCOUNT_RATE)/100, 0) AS FEE
    
FROM
    CAR_RENTAL_COMPANY_CAR c
        JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h ON c.car_id = h.car_id
        JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p ON c.car_type = p.car_type
        
WHERE
    c.CAR_ID NOT IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE END_DATE > '2022-11-01' AND START_DATE < '2022-12-01'
    )
    AND p.DURATION_TYPE = '30일 이상'
    
GROUP BY
    c.CAR_ID, c.CAR_TYPE
    
HAVING c.CAR_TYPE IN ('세단', 'SUV') AND FEE BETWEEN 500000 AND 2000000

ORDER BY
   3 DESC, 2, 1 DESC 
    