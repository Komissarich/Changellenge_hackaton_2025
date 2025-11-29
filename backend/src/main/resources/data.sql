CREATE TABLE IF NOT EXISTS course_teachers (
    course_id BIGINT,
    teacher_id BIGINT,
    UNIQUE(course_id, teacher_id)
);