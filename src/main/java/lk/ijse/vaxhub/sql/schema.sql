drop database vaxhub;

create database vaxhub;

USE vaxhub;

CREATE TABLE RegulatoryBody(
                               regulatory_id varchar(15)PRIMARY KEY,
                               regulatory_name varchar(30)
);


CREATE TABLE facility(
                         facility_id varchar(15)PRIMARY KEY,
                         regulatory_id varchar(15),
                         address varchar(30),
                         capacity varchar(15),
                         facility_name varchar (30),
                         FOREIGN KEY (regulatory_id) REFERENCES RegulatoryBody(regulatory_id) ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE inventory(
                          inventory_id varchar(15)PRIMARY KEY,
                          facility_id varchar(15),
                          inventory_name varchar(30),
                          quantity varchar(15),
                          FOREIGN KEY (facility_id) REFERENCES facility(facility_id) ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE employee(
                         employee_id varchar(15)PRIMARY KEY,
                         facility_id varchar(15),
                         first_name varchar(15),
                         last_name varchar(15),
                         role varchar(15),
                         email varchar(30),
                         address varchar(30),
                         contact_number varchar(10),
                         FOREIGN KEY (facility_id) REFERENCES facility(facility_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE user(
                     user_id varchar(15)PRIMARY KEY,
                     employee_id varchar(15),
                     first_name varchar(15),
                     last_name varchar(15),
                     password varchar(8),
                     role varchar(20),
                     email varchar(30),
                     FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE vaccine(
                        vaccine_id varchar(15)PRIMARY KEY,
                        facility_id varchar(15),
                        employee_id varchar(15),
                        vaccine_name varchar(30),
                        vaccine_date varchar(20),
                        manufacture varchar(30),
                        quantity varchar(30),
                        FOREIGN KEY (facility_id) REFERENCES facility(facility_id) ON UPDATE CASCADE ON DELETE CASCADE,
                        FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);




CREATE TABLE patient(
                        patient_id varchar(100)PRIMARY KEY,
                        first_name varchar(15),
                        last_name varchar(15),
                        gender varchar(10),
                        birth_day varchar(20),
                        email varchar(25),
                        address varchar(30),
                        contact_number varchar(10),
                        date_administer varchar(20),
                        adverse_reaction varchar(50),
                        vaccine_name varchar(30),
                        weight_value varchar(10),
                        hight_value varchar(15)
);




CREATE TABLE vaccination(
                            patient_id varchar(100),
                            vaccine_id varchar(15),
                            vaccine_name varchar(30),
                            date varchar(30),
                            FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON UPDATE CASCADE ON DELETE CASCADE,
                            FOREIGN KEY (vaccine_id) REFERENCES vaccine(vaccine_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE information_system(
                                   system_id varchar(15)PRIMARY KEY,
                                   employee_id varchar(15),
                                   system_name varchar(15),
                                   system_type varchar(15),
                                   FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);






CREATE TABLE payment(
                        payment_id varchar(15)PRIMARY KEY,
                        patient_id varchar(100),
                        amount varchar(20),
                        payment_date varchar(20),
                        FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE appoiment(
                          appoiment_id varchar(15)PRIMARY KEY,
                          employee_id varchar(15),
                          patient_id varchar(100),
                          payment_id varchar(15),
                          appoiment_date varchar(15),
                          appoiment_time varchar(15),
                          FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE,
                          FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON UPDATE CASCADE ON DELETE CASCADE,
                          FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON UPDATE CASCADE ON DELETE CASCADE
);






CREATE TABLE attendance(
                           attendance_id varchar(15)PRIMARY KEY,
                           employee_id varchar(15),
                           date varchar(20),
                           in_time varchar(20),
                           out_time varchar(20),
                           attendance varchar(5),
                           FOREIGN KEY (employee_id) REFERENCES employee(employee_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE report(
                       system_id varchar(15),
                       patient_id varchar(100),
                       vaccine_name varchar(30),
                       weight_value varchar(10),
                       hight_value varchar(15),
                       adverse_reaction varchar(50),
                       FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON UPDATE CASCADE ON DELETE CASCADE,
                       FOREIGN KEY (system_id) REFERENCES information_system(system_id) ON UPDATE CASCADE ON DELETE CASCADE
);





INSERT INTO RegulatoryBody (regulatory_id, regulatory_name) VALUES
                                                                ('RB001', 'FDA'),
                                                                ('RB002', 'EMA'),
                                                                ('RB003', 'MHRA'),
                                                                ('RB004', 'TGA');



INSERT INTO facility (facility_id, regulatory_id, address, capacity, facility_name) VALUES
                                                                                        ('F001', 'RB001', '123 Main St', '100', 'Medical Center A'),
                                                                                        ('F002', 'RB002', '456 Elm St', '150', 'Health Clinic B'),
                                                                                        ('F003', 'RB003', '789 Oak St', '200', 'Hospital C'),
                                                                                        ('F004', 'RB004', '101 Pine St', '120', 'Medical Center D');

INSERT INTO inventory (inventory_id, facility_id, inventory_name, quantity) VALUES
                                                                                ('INV001', 'F001', 'Vaccine A', 1000),
                                                                                ('INV002', 'F002', 'Vaccine B', 800),
                                                                                ('INV003', 'F003', 'Vaccine C', 1200),
                                                                                ('INV004', 'F004', 'Vaccine D', 950);

INSERT INTO employee (employee_id, facility_id, first_name, last_name, role, email, address, contact_number) VALUES
                                                                                                                 ('E001', 'F001', 'Sahan', 'Prabodha', 'Manager', 'sprabodha63@gmail.com', '123 Main St', 0760508895),
                                                                                                                 ('E002', 'F002', 'Nimash', 'Pereis', 'DataAnalist', 'nimash076050@gamail.com', '456 Elm St',0779169876),
                                                                                                                 ('E003', 'F003', 'Tashila', 'Dilshan', 'Administer', 'tashila077916@gmail.com', '789 Oak St', 0760559678),
                                                                                                                 ('E004', 'F004', 'Bob', 'Brown', 'Pharmacist', 'bobbrown@gamil.com', '101 Pine St', 0779189977);

INSERT INTO user (user_id, employee_id, first_name, last_name, password, role, email) VALUES
                                                                                          ('U001', 'E001', 'Sahan', 'Prabodha', '076050', 'Manager', 'sprabodha63@gmail.com'),
                                                                                          ('U002', 'E002', 'Nimash', 'Pereis', '077916', 'DataAnalist',  'nimash077916@gmail.com');

INSERT INTO vaccine (vaccine_id, facility_id, employee_id, vaccine_name, vaccine_date, manufacture, quantity) VALUES
                                                                                                                  ('V001', 'F001', 'E001', 'BCG', '2024-04-29', 'Evans ', 500),
                                                                                                                  ('V002', 'F002', 'E002', 'OPV1', '2024-04-30', 'Sanofi Pasteur', 300),
                                                                                                                  ('V003', 'F003', 'E003', 'DTP', '2024-05-01', 'Merck', 600),
                                                                                                                  ('V004', 'F004', 'E004', 'MMR', '2024-05-02', 'Merck & Co. Inc', 400);



INSERT INTO patient (patient_id, first_name, last_name, gender, birth_day, email, address, contact_number, date_administer, adverse_reaction,vaccine_name,weight_value,hight_value) VALUES
                                                                                                                                                                                        ('2008503033', 'Hirushi', 'Ravindya', 'Female', '2020-01-01', 'hiru1234@gmail.com', '789 Oak St', 0780555345, '2024-04-29', 'None','BCG','4kg','50cm'),
                                                                                                                                                                                        ('2002118044', 'Yasintha', 'Dilruwan', 'Male', '2019-05-15', 'yasintha3421@gmail.com', '101 Pine St', 0774065678, '2024-04-30', 'None', 'OPV1','3.5kg','40cm');




INSERT INTO vaccination (patient_id, vaccine_id,vaccine_name, date) VALUES
                                                                        ('2008503033', 'V001','BCG','2024-04-29'),
                                                                        ('2002118044', 'V002', 'OPV1','2024-04-30');

INSERT INTO information_system (system_id,  employee_id, system_name, system_type) VALUES
                                                                                       ('IS001', 'E001', 'email', 'IMAP'),
                                                                                       ('IS002', 'E002', 'post', 'POP');



INSERT INTO payment (payment_id,patient_id, amount, payment_date) VALUES
                                                                      ('PM001','2008503033', 'RS.2000', '2024-04-29'),
                                                                      ('PM002','2002118044','RS.3000', '2024-04-30');

INSERT INTO appoiment (appoiment_id, employee_id, patient_id, payment_id, appoiment_date, appoiment_time) VALUES
                                                                                                              ('A001', 'E001', '2008503033', 'PM001', '2024-04-29', '10:00 AM'),
                                                                                                              ('A002', 'E002', '2002118044', 'PM002', '2024-04-30', '11:00 AM');


INSERT INTO attendance (attendance_id, employee_id, date, in_time, out_time,attendance) VALUES
                                                                                            ('AT001', 'E001', '2024-04-29', '08:00 AM', '05:00 PM','1'),
                                                                                            ('AT002', 'E002', '2024-04-30', '08:30 AM', '04:30 PM','1'),
                                                                                            ('AT003', 'E003', '2024-05-01', '09:00 AM', '03:00 PM','0'),
                                                                                            ('AT004', 'E004', '2024-05-02', '09:30 AM', '02:30 PM','1');

