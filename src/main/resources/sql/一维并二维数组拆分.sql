SELECT
  e,
  ordinality,
  unnest ( b [ ordinality : ordinality ] ) ,
  c
FROM
  tbl_array,
  unnest ( a ) WITH ORDINALITY e