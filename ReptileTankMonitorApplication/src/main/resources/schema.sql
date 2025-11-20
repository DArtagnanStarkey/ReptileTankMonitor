-- Drop tables if they exist (in correct order due to foreign keys)
DROP TABLE IF EXISTS reading;
DROP TABLE IF EXISTS tank_maintenance;
DROP TABLE IF EXISTS species_equipment;
DROP TABLE IF EXISTS tank;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS species;

-- Create tables
CREATE TABLE species (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    min_temp DOUBLE,
    max_temp DOUBLE,
    min_humidity DOUBLE,
    max_humidity DOUBLE
);

CREATE TABLE equipment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    status VARCHAR(255)
);

CREATE TABLE tank (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    description TEXT,
    species_id BIGINT,
    FOREIGN KEY (species_id) REFERENCES species(id)
);

CREATE TABLE reading (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    temperature DOUBLE NOT NULL,
    humidity DOUBLE NOT NULL,
    timestamp DATETIME(6) NOT NULL,
    tank_id BIGINT,
    temperature_status VARCHAR(255),
    humidity_status VARCHAR(255),
    FOREIGN KEY (tank_id) REFERENCES tank(id)
);

CREATE TABLE tank_maintenance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tank_id BIGINT,
    equipment_id BIGINT,
    maintenance_date DATETIME(6),
    maintenance_type VARCHAR(255),
    notes TEXT,
    FOREIGN KEY (tank_id) REFERENCES tank(id),
    FOREIGN KEY (equipment_id) REFERENCES equipment(id)
);

-- Many-to-Many join table
CREATE TABLE species_equipment (
    species_id BIGINT NOT NULL,
    equipment_id BIGINT NOT NULL,
    PRIMARY KEY (species_id, equipment_id),
    FOREIGN KEY (species_id) REFERENCES species(id),
    FOREIGN KEY (equipment_id) REFERENCES equipment(id)
);