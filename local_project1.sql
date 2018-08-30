DROP TABLE Profile;
DROP TABLE Guest;
DROP TABLE Host;
DROP TABLE Room;
DROP TABLE Reservation;
DROP TABLE Message;

CREATE TABLE Profile (
    p_id INTEGER PRIMARY KEY,
    p_name VARCHAR2(30) UNIQUE NOT NULL,
    p_pass VARCHAR2(100) NOT NULL,
    f_name VARCHAR2(30),
    l_name VARCHAR2(30)
);

CREATE TABLE Guest (
    g_id INTEGER PRIMARY KEY,
    p_id INTEGER,
    g_email VARCHAR2(50),
    g_address VARCHAR2(100),
    g_dob DATE
);

CREATE TABLE Host (
    h_id INTEGER PRIMARY KEY,
    p_id INTEGER
);

CREATE TABLE Room (
    r_num INTEGER PRIMARY KEY,
    r_type VARCHAR2(20),
    r_bednum INTEGER,
    r_bedsize VARCHAR2(10),
    r_rate DECIMAL(10,2) NOT NULL,
    r_description VARCHAR2(250),
    r_img VARCHAR2(50)
);

CREATE TABLE Reservation (
    res_id INTEGER PRIMARY KEY,
    g_id INTEGER,
    r_id INTEGER,
    res_guestnum INTEGER,
    res_checkin DATE,
    res_checkout DATE,
    res_stat VARCHAR2(1)
);

CREATE TABLE Message (
    m_id INTEGER PRIMARY KEY,
    send_id INTEGER,
    recive_id INTEGER,
    text VARCHAR2(500) NOT NULL,
    date_time DATE
);

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/
ALTER TABLE Guest ADD CONSTRAINT fk_user
    FOREIGN KEY (p_id) REFERENCES Profile (p_id);
    
ALTER TABLE Host ADD CONSTRAINT fk_profile
    FOREIGN KEY (p_id) REFERENCES Profile (p_id);
    
ALTER TABLE Reservation ADD CONSTRAINT fk_guest
    FOREIGN KEY (g_id) REFERENCES Guest (g_id);
    
ALTER TABLE Reservation ADD CONSTRAINT fk_room
    FOREIGN KEY (r_id) REFERENCES Room (r_num);
    
ALTER TABLE Message ADD CONSTRAINT fk_sender
    FOREIGN KEY (send_id) REFERENCES Profile (p_id);
    
ALTER TABLE Message ADD CONSTRAINT fk_reciever
    FOREIGN KEY (recive_id) REFERENCES Profile (p_id);
    
ALTER TABLE Reservation MODIFY res_stat DEFAULT 'P';   
/*******************************************************************************
   Auto-Increment profile_id and account_id
********************************************************************************/    
   
CREATE SEQUENCE profileid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE guestid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE hostid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE resid_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE messid_sequence START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER make_profileid
    BEFORE INSERT ON Profile
    FOR EACH ROW
    BEGIN
        :new.p_id := profileid_sequence.nextval;
    END;
/
    
CREATE OR REPLACE TRIGGER make_guestid
    BEFORE INSERT ON Guest
    FOR EACH ROW
    BEGIN
        :new.g_id := guestid_sequence.nextval;
    END;
/

CREATE OR REPLACE TRIGGER make_hostid
    BEFORE INSERT ON Host
    FOR EACH ROW
    BEGIN
        :new.h_id := hostid_sequence.nextval;
    END;
/

CREATE OR REPLACE TRIGGER make_resid
    BEFORE INSERT ON Reservation
    FOR EACH ROW
    BEGIN
        :new.res_id := resid_sequence.nextval;
    END;
/

CREATE OR REPLACE TRIGGER make_messid
    BEFORE INSERT ON Message
    FOR EACH ROW
    BEGIN
        :new.m_id := messid_sequence.nextval;
    END;
/

CREATE OR REPLACE TRIGGER set_date
    BEFORE INSERT ON Message
    FOR EACH ROW
    BEGIN
        :new.date_time := CURRENT_DATE;
    END;
/
/**********************************************************************
    Stored Procedure SP_ 
**************************************************************************/
/*NEED TO FINISH!!!
Guest
    g_id INTEGER PRIMARY KEY,
    p_id INTEGER,
    g_email VARCHAR2(50),
    g_address VARCHAR2(100),
    g_dob DATE
    
Profile
    p_id INTEGER PRIMARY KEY,
    p_name VARCHAR2(30) NOT NULL,
    p_pass VARCHAR2(100) NOT NULL,
    f_name VARCHAR2(30),
    l_name VARCHAR2(30)
    */
    
CALL SP_CREATEGUEST('does', 'it', 'work', 'i', 'dont', 'know', CURRENT_DATE);
CREATE OR REPLACE PROCEDURE SP_CREATEGUEST 
( USERNAME IN VARCHAR2, PWORD IN VARCHAR2, FNAME IN VARCHAR2,
 LNAME IN VARCHAR2, EMAIL IN VARCHAR2, ADDRESS IN VARCHAR2,
 DOB IN VARCHAR2) AS
 
BEGIN
    INSERT INTO Profile ( p_name, p_pass, f_name, l_name ) VALUES ( USERNAME, PWORD, FNAME, LNAME );
    INSERT INTO Guest (p_id, g_email, g_address, g_dob) VALUES ((SELECT p_id FROM Profile WHERE p_name = USERNAME AND p_pass = PWORD), EMAIL, ADDRESS, TO_DATE(DOB, 'yyyy-mm-dd'));
    COMMIT;
END;
/

INSERT INTO Profile ( p_name, p_pass, f_name, l_name ) VALUES ( 'tester', 'password', 'Tim', 'Dees' );
INSERT INTO Host (p_id) VALUES (2);
CALL SP_CREATEGUEST('usename', 'password', 'myfirst', 'mylast', 'myemail', 'myaddress', CURRENT_DATE);

INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (100, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (101, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (102, 'Regular', 1, 'King', 39.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (103, 'Regular', 1, 'Queen', 34.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (200, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (201, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (202, 'Regular', 1, 'King', 39.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (203, 'Regular', 1, 'Queen', 34.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (300, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (301, 'Regular', 2, 'King', 59.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (302, 'Regular', 1, 'King', 39.96);
INSERT INTO Room (r_num, r_type, r_bednum, r_bedsize, r_rate) VALUES (303, 'Regular', 2, 'Queen', 49.96);

