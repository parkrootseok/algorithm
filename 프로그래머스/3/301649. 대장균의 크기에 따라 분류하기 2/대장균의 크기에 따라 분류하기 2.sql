-- 코드를 작성해주세요
SELECT
    a.ID,
    CASE
        WHEN a.per <= 0.25 THEN 'CRITICAL'
        WHEN a.per <= 0.5 THEN 'HIGH'
        WHEN a.per <= 0.75 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME
    
FROM ( 
    SELECT 
        ID, PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS per 
    FROM 
        ECOLI_DATA 
) AS a
    
ORDER BY a.ID