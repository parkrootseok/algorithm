SELECT 
    ID, 
    (CASE
        WHEN r.rank <= 0.25 THEN 'CRITICAL'
        WHEN r.rank <= 0.5 THEN 'HIGH'
        WHEN r.rank <= 0.75 THEN 'MEDIUM'
        ELSE 'LOW'
    END) AS COLONY_NAME

FROM
    (SELECT ID, PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS `rank` FROM ECOLI_DATA) AS r
    
ORDER BY
    1