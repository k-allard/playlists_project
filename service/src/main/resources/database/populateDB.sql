INSERT INTO playlist
    ("name")
VALUES
    ('guitar songs')
ON CONFLICT DO NOTHING
;


INSERT INTO song
    ("playlist_id", "name", "artist", "cover_url")
VALUES
    (1, 'Me and Bobby McGee', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
ON CONFLICT DO NOTHING
;

