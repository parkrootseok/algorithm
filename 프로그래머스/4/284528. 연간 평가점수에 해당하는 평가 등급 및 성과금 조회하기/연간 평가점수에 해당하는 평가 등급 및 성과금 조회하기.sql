WITH EVALUATION as (
    SELECT  EMP_NO, 
            (CASE 
                WHEN 96 <= AVG(SCORE) THEN 'S'
                WHEN 90 <= AVG(SCORE) THEN 'A'
                WHEN 80 <= AVG(SCORE) THEN 'B'
                ELSE 'C'
            END) as GRADE
    FROM    HR_GRADE
    GROUP BY EMP_NO, YEAR
)

SELECT  he.EMP_NO,
        he.EMP_NAME,
        GRADE,
        (CASE GRADE
                WHEN 'S' THEN SAL * 0.2
                WHEN 'A' THEN SAL * 0.15
                WHEN 'B' THEN SAL * 0.1
                ELSE 0
        END) as BONUS

FROM    HR_EMPLOYEES he LEFT JOIN EVALUATION e ON he.EMP_NO = e.EMP_NO

ORDER BY EMP_NO
