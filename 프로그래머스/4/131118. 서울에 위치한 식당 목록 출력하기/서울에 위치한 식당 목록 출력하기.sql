SELECT
    i.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, ROUND(AVG(r.REVIEW_SCORE), 2) SCORE

FROM
    REST_INFO i
        JOIN REST_REVIEW r ON i.REST_ID = r.REST_ID

WHERE ADDRESS LIKE '서울%'

GROUP BY REST_ID

ORDER BY 6 DESC, 4 DESC