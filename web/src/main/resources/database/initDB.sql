-- Table: public.t_role

-- DROP TABLE public.t_role;

CREATE TABLE IF NOT EXISTS public.t_role
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT t_role_pkey PRIMARY KEY (id)
);

-- Table: public.t_user

-- DROP TABLE public.t_user;

CREATE TABLE IF NOT EXISTS public.t_user
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

-- Table: public.t_user_roles

-- DROP TABLE public.t_user_roles;

CREATE TABLE IF NOT EXISTS public.t_user_roles
(
    user_id  bigint NOT NULL,
    roles_id bigint NOT NULL,
    CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, roles_id),
    CONSTRAINT t_user_roles_roles_id_t_role_id_fk FOREIGN KEY (roles_id)
        REFERENCES public.t_role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT t_user_roles_user_id_t_user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.t_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
