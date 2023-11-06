CREATE TABLE userData (
                          id  VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100),
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          last_login TIMESTAMP,
                          token VARCHAR(200),
                          is_active BOOLEAN
);

CREATE TABLE phone (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id VARCHAR(100),
                       number VARCHAR(20),
                       citycode INT,
                       countrycode VARCHAR(10),
                       FOREIGN KEY (user_id) REFERENCES userData(id)
);

CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE START WITH 4 INCREMENT BY 1;
