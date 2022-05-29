CREATE TABLE IF NOT EXISTS public.roles
(
    id
    integer
    NOT
    NULL,
    name
    character
    varying
(
    20
),
    CONSTRAINT roles_pkey PRIMARY KEY
(
    id
)
    );
