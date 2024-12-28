WITH GRADES AS (
    SELECT
        EMP_NO,
        (CASE
            WHEN 96 <= AVG(SCORE) THEN 'S'   
            WHEN 90 <= AVG(SCORE) THEN 'A'   
            WHEN 80 <= AVG(SCORE) THEN 'B'  
            ELSE 'C'    
        END) AS GRADE
    
    FROM
        HR_GRADE
    
    GROUP BY
        EMP_NO
    
)

SELECT 
    e.EMP_NO, e.EMP_NAME, GRADE, 
    CASE GRADE
        WHEN 'S' THEN SAL * 0.2
        WHEN 'A' THEN SAL * 0.15
        WHEN 'B' THEN SAL * 0.1
        ELSE 0
    END AS BONUS
    
FROM 
    HR_EMPLOYEES e
    JOIN GRADES g ON e.EMP_NO = g.EMP_NO