#!/bin/sh -x

set -e

echo "Create Service DATABASES"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE DATABASE music;
EOSQL


echo "Music DB: Init"
psql --username "$POSTGRES_USER" --dbname "music" -f /sql.tmp/music.init.sql


echo "Music DB: Populate"
psql --username "$POSTGRES_USER" --dbname "music" -f /sql.tmp/music.populate.sql


echo "Create Music User"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "music" <<-EOSQL
CREATE ROLE ${SERVICE_MS_USER} WITH LOGIN;
ALTER USER ${SERVICE_MS_USER} WITH ENCRYPTED PASSWORD '${SERVICE_MS_PASSWORD}';

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.playlist TO ${SERVICE_MS_USER};
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE public.song TO ${SERVICE_MS_USER};

GRANT SELECT, USAGE ON SEQUENCE public.playlist_id_seq TO ${SERVICE_MS_USER};
GRANT SELECT, USAGE ON SEQUENCE public.song_id_seq TO ${SERVICE_MS_USER};

EOSQL


echo "Create Music User for Migration"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "music" <<-EOSQL
CREATE ROLE ${SERVICE_USER} WITH LOGIN;
ALTER USER ${SERVICE_USER} WITH ENCRYPTED PASSWORD '${SERVICE_PASSWORD}';

ALTER DATABASE music OWNER TO ${SERVICE_USER};
ALTER SCHEMA public OWNER TO ${SERVICE_USER};

ALTER TABLE public.playlist OWNER TO ${SERVICE_USER};
ALTER TABLE public.song OWNER TO ${SERVICE_USER};

ALTER SEQUENCE public.playlist_id_seq OWNER TO ${SERVICE_USER};
ALTER SEQUENCE public.song_id_seq OWNER TO ${SERVICE_USER};
EOSQL

