WITH FRONT_SKILL AS (
    SELECT SUM(CODE) CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
)

SELECT
    ID, EMAIL, FIRST_NAME, LAST_NAME

FROM DEVELOPERS d, FRONT_SKILL s

WHERE s.CODE & d.SKILL_CODE

ORDER BY 1