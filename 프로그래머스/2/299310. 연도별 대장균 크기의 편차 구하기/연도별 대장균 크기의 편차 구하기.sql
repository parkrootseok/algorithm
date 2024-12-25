WITH A as (
    SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR, MAX(SIZE_OF_COLONY) AS MAX
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
)

SELECT
    YEAR(ECOLI_DATA.DIFFERENTIATION_DATE) YEAR, (MAX - ECOLI_DATA.SIZE_OF_COLONY) YEAR_DEV, ECOLI_DATA.ID

FROM
    ECOLI_DATA
        LEFT JOIN A ON YEAR(ECOLI_DATA.DIFFERENTIATION_DATE) = A.YEAR
    
ORDER BY
    1, 2