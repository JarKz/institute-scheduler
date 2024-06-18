INSERT INTO subject (id, name)
VALUES
  (1, 'Math Analysis'),
  (2, 'Algebra'),
  (3, 'Analytic Geometry'),
  (4, 'WEB-programming'),
  (5, 'Database'),
  (6, 'English');

INSERT INTO speciality (id, name)
VALUES
  (1, 'WEB Development'),
  (2, 'Mobile Development'),
  (3, 'Pedagogy');

INSERT INTO faculty (id, name)
VALUES
  (1, 'MMF'),
  (2, 'Phil');

INSERT INTO room (id, floor, number)
VALUES
  (1, 1, 102),
  (2, 3, 338),
  (3, 3, 340),
  (4, 3, 332),
  (5, 3, 344),
  (6, 3, 345),
  (7, 4, 402),
  (8, 4, 405),
  (9, 4, 408),
  (10, 4, 410);

INSERT INTO administrator (id, credentials_non_expired, enabled, expired, locked, username, password)
VALUES
  (0, true, true, false, false, 'a', '$2a$10$KuJcVss/COoucN5wBGcFee9INvnLeOnZGaG3.zYy3gvQ319y7aNHS');
