DROP TABLE IF EXISTS exercise;

CREATE TABLE exercise
(
    id            BIGINT       NOT NULL auto_increment,
    exercise_name VARCHAR(255) NOT NULL,
    exercise_type VARCHAR(255) NOT NULL,
    user_name     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_exercise PRIMARY KEY (id)
)
    engine = innodb;
