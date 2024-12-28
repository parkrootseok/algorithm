WITH MAX AS (
    SELECT YEAR(DIFFERENTIATION_DATE) YEAR, MAX(SIZE_OF_COLONY) AS MAX
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
)

SELECT
    YEAR(DIFFERENTIATION_DATE) AS YEAR, (MAX - SIZE_OF_COLONY) AS YEAR_DEV, ID

FROM
    ECOLI_DATA e
        JOIN MAX m ON YEAR(e.DIFFERENTIATION_DATE) = m.YEAR
        
ORDER BY
    1, 2