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

INSERT INTO room (id, number)
VALUES
  (1, 102),
  (2, 338),
  (3, 340),
  (4, 332),
  (5, 344),
  (6, 345),
  (7, 402),
  (8, 405),
  (9, 408),
  (10, 410);

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


INSERT INTO student_group (id, name)
VALUES
  (0, 'web development');

INSERT INTO student_group_students (student_group_id, students_id)
VALUES
  (0, 0),
  (0, 1);

INSERT INTO schedule (id, lesson_date, lesson_start, lesson_end, break_in_minutes, teacher_id, room_id, subject_id)
VALUES
  (0, '2024-06-10', '12:00', '15:50', 10, 0, 7, 5),
  (1, '2024-06-25', '12:00', '15:50', 10, 0, 7, 5),
  (2, '2024-06-25', '8:00', '11:20', 0, 0, 7, 4),
  (3, '2024-06-25', '16:00', '17:20', 0, 0, 7, 3);

INSERT INTO schedule_students (schedule_id, students_id)
VALUES
  (0, 0),
  (1, 0),
  (2, 0),
  (3, 0);
