DROP TABLE IF EXISTS exercise;

CREATE TABLE exercise
(
    id            BIGINT       NOT NULL auto_increment,
    user_name     VARCHAR(255) NOT NULL,
    exercise_name VARCHAR(255) NOT NULL,
    exercise_type VARCHAR(255) NOT NULL,
    CONSTRAINT pk_exercise PRIMARY KEY (id)
)
    engine = innodb;
