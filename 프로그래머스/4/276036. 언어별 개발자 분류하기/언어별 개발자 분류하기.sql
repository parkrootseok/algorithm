WITH 
    FE AS (
        SELECT BIT_OR(CODE) AS SKILL_CODE
        FROM SKILLCODES
        WHERE CATEGORY = 'Front End'
    ),
    GRADE AS (
        SELECT 
            CASE
                WHEN 
                    dev.SKILL_CODE & FE.SKILL_CODE 
                    AND dev.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME='Python')
                THEN 'A'
                WHEN 
                    dev.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME='C#') 
                THEN 'B'
                WHEN dev.SKILL_CODE & FE.SKILL_CODE THEN 'C'
            END AS GRADE, ID, EMAIL
        FROM DEVELOPERS dev, FE
    )
    
SELECT GRADE, ID, EMAIL

FROM GRADE
    
WHERE GRADE IS NOT NULL

ORDER BY GRADE, ID