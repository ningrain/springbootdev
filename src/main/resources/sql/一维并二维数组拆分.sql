SELECT
  e,
  ordinality,
  unnest(b [ordinality :ordinality]),
  c
FROM
  tbl_array,
  unnest ( a ) WITH ORDINALITY e;

select *
from (
       SELECT
         e,
         unnest(b [ordinality :ordinality]) as be,
         c
       FROM
         tbl_array,
         unnest ( a ) WITH ORDINALITY e
     ) t
where be is not null;