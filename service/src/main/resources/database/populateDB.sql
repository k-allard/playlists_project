INSERT INTO playlist
VALUES
    (1, 'guitar songs', CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING
;

INSERT INTO song
    ("id", "playlist_id", "name", "artist", "cover_url")
VALUES
(1, 1, 'Me and Bobby McGee', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
,(2, 1, 'Me and Bobby McGee 2', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
,(3, 1, 'Me and Bobby McGee 3', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
,(4, 1, 'Me and Bobby McGee 4', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
,(5, 1, 'Me and Bobby McGee 5', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
,(6, 1, 'Me and Bobby McGee 6', 'Janis Lyn Joplin', 'https://www.dummies.com/wp-content/uploads/open-guitar-chords-chart.jpg')
ON CONFLICT DO NOTHING
;
