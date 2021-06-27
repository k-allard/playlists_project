#!/bin/sh -x

set -e

echo "Create Web DATABASES"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE DATABASE auth;
EOSQL


echo "Auth DB: Init"
psql --username "$POSTGRES_USER" --dbname "auth" -f /sql.tmp/auth.init.sql


echo "Auth DB: Populate"
psql --username "$POSTGRES_USER" --dbname "auth" -f /sql.tmp/auth.populate.sql


echo "Create Web User"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "auth" <<-EOSQL
CREATE ROLE ${WEB_MS_USER} WITH LOGIN;
ALTER USER ${WEB_MS_USER} WITH ENCRYPTED PASSWORD '${WEB_MS_PASSWORD}';
ALTER DATABASE auth OWNER TO ${WEB_MS_USER};

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.t_user TO ${WEB_MS_USER};
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.t_role TO ${WEB_MS_USER};
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.t_user_roles TO ${WEB_MS_USER};

GRANT SELECT, USAGE ON SEQUENCE public.t_role_id_seq TO ${WEB_MS_USER};
GRANT SELECT, USAGE ON SEQUENCE public.t_user_id_seq TO ${WEB_MS_USER};

EOSQL

echo "Create Web User for Migration"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "auth" <<-EOSQL
CREATE ROLE ${WEB_USER} WITH LOGIN;
ALTER USER ${WEB_USER} WITH ENCRYPTED PASSWORD '${WEB_PASSWORD}';

ALTER DATABASE auth OWNER TO ${WEB_USER};
ALTER SCHEMA public OWNER TO ${WEB_USER};

ALTER TABLE public.t_user OWNER TO ${WEB_USER};
ALTER TABLE public.t_role OWNER TO ${WEB_USER};
ALTER TABLE public.t_user_roles OWNER TO ${WEB_USER};

ALTER SEQUENCE public.t_role_id_seq OWNER TO ${WEB_USER};
ALTER SEQUENCE public.t_user_id_seq OWNER TO ${WEB_USER};
EOSQL