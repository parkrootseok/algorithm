SELECT
    MONTH(START_DATE) MONTH, CAR_ID, COUNT(*) RECORDS

FROM
    CAR_RENTAL_COMPANY_RENTAL_HISTORY h

WHERE
    DATE_FORMAT(START_DATE, '%Y-%m') BETWEEN '2022-08' AND '2022-10'
    AND
    CAR_ID IN (
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
        WHERE DATE_FORMAT(START_DATE, '%Y-%m') BETWEEN '2022-08' AND '2022-10'
        GROUP BY CAR_ID
        HAVING 5 <= COUNT(*)
    )

GROUP BY
    MONTH(START_DATE), CAR_ID

HAVING
    0 < COUNT(*)
    
ORDER BY
    1, 2 DESC
        