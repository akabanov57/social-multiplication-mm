-- public.user_e definition

-- Drop table

-- DROP TABLE public.user_e;

CREATE TABLE public.user_e (
	id uuid NOT NULL,
	u_alias varchar(128) NOT NULL,
	CONSTRAINT user_alias_is_unique UNIQUE (u_alias),
	CONSTRAINT user_e_pkey PRIMARY KEY (id)
);


-- public.multiplication_attempt definition

-- Drop table

-- DROP TABLE public.multiplication_attempt;

CREATE TABLE public.multiplication_attempt (
	id uuid NOT NULL,
	factor_a int4 NOT NULL,
	factor_b int4 NOT NULL,
	multiplication_result int4 NOT NULL,
	correct bool NOT NULL,
	user_id uuid NOT NULL,
	CONSTRAINT multiplication_attempt_pkey PRIMARY KEY (id),
	CONSTRAINT belong_to_user FOREIGN KEY (user_id) REFERENCES public.user_e(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

