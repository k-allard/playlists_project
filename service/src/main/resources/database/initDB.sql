CREATE TABLE IF NOT EXISTS playlist
  (
     id             bigserial PRIMARY KEY,
     "name"         VARCHAR(250) NOT NULL,
     created_at     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
     CONSTRAINT     t_playlist_name_uniq UNIQUE (name)
  );


CREATE TABLE IF NOT EXISTS song
  (
	id              bigserial PRIMARY KEY,
    playlist_id     bigint NOT NULL,
	"name"          VARCHAR(250) NOT NULL,
	artist          VARCHAR(250) NOT NULL,
	cover_url       VARCHAR(250) NOT NULL,
    CONSTRAINT      song_playlist_id_fk FOREIGN KEY (playlist_id) REFERENCES playlist (id),
	created_at      TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT      t_song_uniq UNIQUE (playlist_id, name, artist, cover_url)

  );
