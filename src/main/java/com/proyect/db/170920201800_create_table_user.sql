DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

INSERT INTO
    users (
        id,
        first_name,
        last_name,
        email,
        password_hash
    )
VALUES
    (
        1,
        'Mario',
        'Velasco',
        'mario@myemail.com',
        'dasf687345sfSDERF'
    ),
    (
        2,
        'Pedro',
        'Alonso',
        'pedro@myemail.com',
        'sd·$534534535s'
    ),
    (
        3,
        'Juan',
        'Sanchez',
        'juan@myemail.com',
        'contraseña'
    );

SELECT
    `users`.*
FROM
    `users` AS `users`