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

INSERT INTO student
  (
    id,
    credentials_non_expired,
    enabled,
    expired,
    locked,
    username,
    password,
    first_name,
    last_name,
    patronymic,
    faculty_id,
    speciality_id,
    phone_number,
    course
  )
VALUES
  (
    0,
    true,
    true,
    false,
    false,
    'bialiauski',
    '$2a$10$RHCKyVuEYhGillwpJaq9d.I.6RLvb6R2HBwjk5yRYuBfJxnKiGyQe',
    'Pavel',
    'Bialiauski',
    'Aliaksandravich',
    1,
    1,
    375298824134,
    3
  ),
  (
    1,
    true,
    true,
    false,
    false,
    'amend',
    '$2a$10$RHCKyVuEYhGillwpJaq9d.I.6RLvb6R2HBwjk5yRYuBfJxnKiGyQe',
    'Amend',
    'Bibal',
    'Farent',
    2,
    3,
    375298892134,
    2
  );

INSERT INTO teacher
  (
    id,
    credentials_non_expired,
    enabled,
    expired,
    locked,
    username,
    password,
    first_name,
    last_name,
    patronymic,
    faculty_id,
    department,
    phone_number
  )
VALUES
  (
    0,
    true,
    true,
    false,
    false,
    'mudar',
    '$2a$10$RHCKyVuEYhGillwpJaq9d.I.6RLvb6R2HBwjk5yRYuBfJxnKiGyQe',
    'Nikolay',
    'Maikh',
    'Alexeevich',
    1,
    'WEB-technology and Computer modeling',
    375291234843
  );


INSERT INTO schedule (id, lesson_date, lesson_start, lesson_end, break_in_minutes, teacher_id, room_id, subject_id)
VALUES
  (0, '2024-06-10', '12:00', '15:50', 10, 0, 7, 5),
  (1, '2024-06-25', '12:00', '15:50', 10, 0, 7, 5);

INSERT INTO schedule_students (schedule_id, students_id)
VALUES
  (0, 0),
  (1, 0);
