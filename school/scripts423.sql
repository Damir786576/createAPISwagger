SELECT
    student.name AS student_name,
    student.age AS student_age,
    faculty.name AS faculty_name
FROM
    student student
LEFT JOIN
    faculty faculty ON student.faculty_id = faculty.id;

SELECT
    student.name AS student_name,
    student.age AS student_age,
    faculty.name AS faculty_name
FROM
    student student
INNER JOIN
    avatar avatar ON student.id = avatar.student_id
LEFT JOIN
    faculty faculty ON student.faculty_id = f.id;
