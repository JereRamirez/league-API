CREATE TABLE IF NOT EXISTS competition
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
),
    code VARCHAR
(
    255
) UNIQUE,
    area_name VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXISTS coach
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    date_of_birth DATE NOT NULL,
    nationality VARCHAR
(
    255
) NOT NULL
    );

CREATE TABLE IF NOT EXISTS team
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) UNIQUE,
    tla VARCHAR
(
    255
),
    short_name VARCHAR
(
    255
),
    area_name VARCHAR
(
    255
),
    address VARCHAR
(
    255
),
    coach_id INT UNIQUE,
    FOREIGN KEY
(
    coach_id
) REFERENCES coach
(
    id
),
    UNIQUE KEY team_coach_fk
(
    coach_id
),
    INDEX team_coach_idx
(
    coach_id
)
    );

CREATE TABLE IF NOT EXISTS player
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL,
    position VARCHAR
(
    255
) NOT NULL,
    date_of_birth DATE NOT NULL,
    nationality VARCHAR
(
    255
) NOT NULL,
    team_id INT,
    FOREIGN KEY
(
    team_id
) REFERENCES team
(
    id
),
    INDEX player_team_idx
(
    team_id
)
    );

CREATE TABLE IF NOT EXISTS team_competitions
(
    team_id
    INT,
    competitions_id
    INT,
    PRIMARY
    KEY
(
    team_id,
    competitions_id
),
    FOREIGN KEY
(
    team_id
) REFERENCES team
(
    id
),
    FOREIGN KEY
(
    competitions_id
) REFERENCES competition
(
    id
),
    INDEX team_competitions_team_idx
(
    team_id
),
    INDEX team_competitions_competition_idx
(
    competitions_id
)
    );
