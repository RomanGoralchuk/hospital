CREATE TABLE complains (
complain_id BIGINT NOT NULL AUTO_INCREMENT,
patient_id VARCHAR(10) NULL DEFAULT NULL,
description VARCHAR(255) NULL DEFAULT NULL,
date_complain DATE NULL DEFAULT NULL,
doctor_id VARCHAR(10) NULL DEFAULT NULL,
treatment VARCHAR(255) NULL DEFAULT NULL,
date_start DATE NULL DEFAULT NULL,
date_complete DATE NULL DEFAULT NULL,
result_treatment VARCHAR(15) NULL DEFAULT NULL,
PRIMARY KEY (complain_id),
FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
