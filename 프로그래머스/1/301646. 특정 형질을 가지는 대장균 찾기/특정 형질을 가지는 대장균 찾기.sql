
SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE 
    (GENOTYPE & 2) != 2
    AND ((GENOTYPE & 4) = 4 OR (GENOTYPE & 1) = 1)
