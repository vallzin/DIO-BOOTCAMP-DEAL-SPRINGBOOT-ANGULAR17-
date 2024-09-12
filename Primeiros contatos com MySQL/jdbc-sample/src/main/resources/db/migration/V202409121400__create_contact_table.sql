CREATE TABLE contacts (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    type VARCHAR(30),
    employee_id BIGINT NOT NULL,
    CONSTRAINT fk_contacts_employee FOREIGN KEY (employee_id) REFERENCES employees(id),
    PRIMARY KEY (id)
);