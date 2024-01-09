-- CREATE ROLE

CREATE ROLE apollo WITH
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT -1
    PASSWORD 'apollo';

-- CREATE DB

CREATE DATABASE apollo_db
    WITH
    OWNER = apollo
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- CREATE TABLE

CREATE TABLE public.post
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    title character varying(256) NOT NULL,
    body text NOT NULL,
    PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.post
    OWNER to apollo;