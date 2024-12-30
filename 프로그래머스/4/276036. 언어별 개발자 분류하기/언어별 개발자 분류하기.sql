WITH F AS (
    SELECT SUM(CODE) CODE FROM SKILLCODES WHERE CATEGORY='Front End'
), GRADES AS (
    SELECT
    CASE
        WHEN 
            f.CODE & d.SKILL_CODE 
            AND (SELECT CODE FROM SKILLCODES WHERE NAME='Python') & d.SKILL_CODE 
            THEN 'A'
        
        WHEN (SELECT CODE FROM SKILLCODES WHERE NAME='C#') & d.SKILL_CODE 
            THEN 'B'
        
        WHEN f.CODE & d.SKILL_CODE 
            THEN 'C'
            
    END GRADE, 
    ID, EMAIL

    FROM
        DEVELOPERS d, F f
)

SELECT
    GRADE, ID, EMAIL
    
FROM
    GRADES

WHERE
    GRADE IS NOT NULL

ORDER BY
    1, 2