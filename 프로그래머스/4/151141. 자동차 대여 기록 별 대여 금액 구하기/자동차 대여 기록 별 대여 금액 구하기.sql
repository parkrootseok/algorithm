WITH DISCOUNT AS (
    
    SELECT
            ccrh.HISTORY_ID, 
            ccr.CAR_TYPE, 
            ccr.DAILY_FEE,
            DATEDIFF(ccrh.END_DATE, ccrh.START_DATE) + 1 AS period,
            (CASE 
                WHEN 90 <= (DATEDIFF(ccrh.END_DATE, ccrh.START_DATE) + 1) THEN '90일 이상'   
                WHEN 30 <= (DATEDIFF(ccrh.END_DATE, ccrh.START_DATE) + 1) THEN '30일 이상'   
                WHEN 7 <= (DATEDIFF(ccrh.END_DATE, ccrh.START_DATE) + 1)  THEN '7일 이상'
                ELSE 'NONE'
             END) AS DURATION_TYPE
    
    FROM
        CAR_RENTAL_COMPANY_RENTAL_HISTORY ccrh
            JOIN CAR_RENTAL_COMPANY_CAR ccr ON ccrh.CAR_ID = ccr.CAR_ID
    
    WHERE ccr.CAR_TYPE = '트럭'

)

SELECT
    d.HISTORY_ID,
    ROUND(d.DAILY_FEE * d.period * (100 - IFNULL(ccdp.DISCOUNT_RATE, 0)) / 100) AS FEE

FROM DISCOUNT d
        LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN ccdp 
        ON d.DURATION_TYPE = ccdp.DURATION_TYPE AND d.CAR_TYPE = ccdp.CAR_TYPE

ORDER BY FEE DESC, HISTORY_ID DESC