CREATE DATABASE SCHOOL;

USE SCHOOL;

CREATE TABLE T_Department (
    d_id INT PRIMARY KEY AUTO_INCREMENT, d_name VARCHAR(50) NOT NULL
);

CREATE TABLE T_Teacher (
    t_matricule INT PRIMARY KEY AUTO_INCREMENT, t_last_name VARCHAR(50) NOT NULL, t_first_name VARCHAR(50) NOT NULL, t_age INT CHECK (t_age >= 18), t_grade VARCHAR(50), t_head_teacher BOOLEAN, d_id INT NOT NULL, FOREIGN KEY (d_id) REFERENCES T_Department (d_id)
);

CREATE TABLE T_Subject (
    s_id INT PRIMARY KEY AUTO_INCREMENT, s_title VARCHAR(50) NOT NULL, s_description VARCHAR(255), s_duration_minutes INT, s_coefficient INT
);

CREATE TABLE T_Class (
    c_id INT PRIMARY KEY AUTO_INCREMENT, c_name VARCHAR(50) NOT NULL, c_level VARCHAR(50), d_id INT NOT NULL, FOREIGN KEY (d_id) REFERENCES T_Department (d_id)
);

CREATE TABLE T_Student (
    st_id INT PRIMARY KEY AUTO_INCREMENT, st_last_name VARCHAR(50) NOT NULL CHECK (LENGTH(st_last_name) >= 3), st_first_name VARCHAR(50) NOT NULL CHECK (LENGTH(st_first_name) >= 3), st_email VARCHAR(50) CHECK (st_email LIKE '%@gmail.com%'), st_date_birth DATE, c_id INT NOT NULL, FOREIGN KEY (c_id) REFERENCES T_Class (c_id)
);

CREATE TABLE T_Note (
    n_id INT PRIMARY KEY AUTO_INCREMENT, n_value DECIMAL(4, 2) CHECK (
        n_value >= 0
        AND n_value <= 20
    ), n_comment VARCHAR(255), s_id INT NOT NULL, st_id INT NOT NULL, FOREIGN KEY (s_id) REFERENCES T_Subject (s_id), FOREIGN KEY (st_id) REFERENCES T_Student (st_id)
);

CREATE TABLE T_Time_Table (
    ti_id INT PRIMARY KEY AUTO_INCREMENT, ti_day_of_week VARCHAR(50) CHECK (
        ti_day_of_week IN (
            'LUNDI', 'MARDI', 'MERCREDI', 'JEUDI', 'VENDREDI'
        )
    ), ti_start_time TIME, c_id INT NOT NULL, FOREIGN KEY (c_id) REFERENCES T_Class (c_id)
);

CREATE TABLE T_Teacher_Subject (
    t_matricule INT, s_id INT, PRIMARY KEY (t_matricule, s_id), FOREIGN KEY (t_matricule) REFERENCES T_Teacher (t_matricule), FOREIGN KEY (s_id) REFERENCES T_Subject (s_id)
);

CREATE TABLE T_Teacher_Class (
    t_matricule INT, c_id INT, PRIMARY KEY (t_matricule, c_id), FOREIGN KEY (t_matricule) REFERENCES T_Teacher (t_matricule), FOREIGN KEY (c_id) REFERENCES T_Class (c_id)
);

CREATE TABLE T_Subject_Time_Table (
    s_id INT, ti_id INT, PRIMARY KEY (s_id, ti_id), FOREIGN KEY (s_id) REFERENCES T_Subject (s_id), FOREIGN KEY (ti_id) REFERENCES T_Time_Table (ti_id)
);