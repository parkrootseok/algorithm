WITH PC AS (
    SELECT SUM(CODE) CODE
    FROM SKILLCODES
    WHERE NAME = 'C#' OR NAME = 'Python'
)

SELECT
    ID, EMAIL, FIRST_NAME, LAST_NAME
    
FROM
    DEVELOPERS d, PC pc

WHERE
    pc.CODE & d.SKILL_CODE

ORDER BY
    1