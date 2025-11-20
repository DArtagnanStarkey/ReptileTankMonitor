-- Clear existing data in correct order
DELETE FROM reading;
DELETE FROM tank_maintenance;
DELETE FROM species_equipment;
DELETE FROM tank;
DELETE FROM equipment;
DELETE FROM species;

-- Insert Species with their environmental requirements
INSERT INTO species (name, min_temp, max_temp, min_humidity, max_humidity) VALUES
('Ball Python', 80.0, 90.0, 50.0, 60.0),
('Crested Gecko', 72.0, 75.0, 70.0, 80.0),
('White''s Tree Frog', 82.0, 84.0, 60.0, 70.0);

-- Insert Equipment
INSERT INTO equipment (name, type, status) VALUES
('Reptile Heater', 'HEATER', 'ACTIVE'),
('Reptile Humidifier', 'HUMIDIFIER', 'ACTIVE'),
('Digital Thermostat', 'THERMOSTAT', 'ACTIVE'),
('UVB Light', 'LIGHTING', 'ACTIVE'),
('Mist System', 'MISTING', 'ACTIVE');

-- Insert Tanks
INSERT INTO tank (name, location, description, species_id) VALUES
('Python Paradise', 'Main Room', '40 gallon glass tank', 1),
('Gecko Garden', 'Main Room', '20 gallon vertical tank', 2),
('Frog Forest', 'Main Room', '30 gallon bioactive tank', 3);

-- MANY-TO-MANY: Species <-> Equipment
INSERT INTO species_equipment (species_id, equipment_id) VALUES
-- Ball Python equipment
(1, 1), (1, 3),
-- Crested Gecko equipment  
(2, 2), (2, 4), (2, 5),
-- White's Tree Frog equipment
(3, 1), (3, 2), (3, 5);

-- Insert Maintenance Records
INSERT INTO tank_maintenance (tank_id, equipment_id, maintenance_date, maintenance_type, notes) VALUES
(1, 1, NOW() - INTERVAL 7 DAY, 'CLEANING', 'Routine heater cleaning and inspection'),
(2, 2, NOW() - INTERVAL 3 DAY, 'FILTER_CHANGE', 'Changed humidifier filter'),
(1, 3, NOW() - INTERVAL 2 DAY, 'CALIBRATION', 'Calibrated thermostat settings'),
(3, 4, NOW() - INTERVAL 14 DAY, 'BULB_REPLACEMENT', 'Replaced UVB bulb'),
(2, 5, NOW() - INTERVAL 5 DAY, 'NOZZLE_CLEANING', 'Cleaned misting nozzles');

-- Insert Sample Readings for Ball Python (Tank 1)
INSERT INTO reading (temperature, humidity, timestamp, tank_id, temperature_status, humidity_status) VALUES
(85.5, 55.0, NOW() - INTERVAL 2 HOUR, 1, 'OPTIMAL', 'OPTIMAL'),
(87.2, 52.3, NOW() - INTERVAL 4 HOUR, 1, 'OPTIMAL', 'OPTIMAL'),
(83.1, 58.7, NOW() - INTERVAL 6 HOUR, 1, 'OPTIMAL', 'OPTIMAL'),
(89.8, 49.5, NOW() - INTERVAL 8 HOUR, 1, 'OPTIMAL', 'TOO_LOW'),
(91.2, 61.8, NOW() - INTERVAL 10 HOUR, 1, 'TOO_HIGH', 'TOO_HIGH'),
(79.5, 56.2, NOW() - INTERVAL 12 HOUR, 1, 'TOO_LOW', 'OPTIMAL');

-- Insert Sample Readings for Crested Gecko (Tank 2)
INSERT INTO reading (temperature, humidity, timestamp, tank_id, temperature_status, humidity_status) VALUES
(73.8, 76.5, NOW() - INTERVAL 2 HOUR, 2, 'OPTIMAL', 'OPTIMAL'),
(74.2, 78.9, NOW() - INTERVAL 4 HOUR, 2, 'OPTIMAL', 'OPTIMAL'),
(71.5, 82.3, NOW() - INTERVAL 6 HOUR, 2, 'TOO_LOW', 'TOO_HIGH'),
(76.1, 74.8, NOW() - INTERVAL 8 HOUR, 2, 'TOO_HIGH', 'OPTIMAL'),
(73.0, 79.2, NOW() - INTERVAL 10 HOUR, 2, 'OPTIMAL', 'OPTIMAL'),
(72.5, 68.7, NOW() - INTERVAL 12 HOUR, 2, 'OPTIMAL', 'TOO_LOW');

-- Insert Sample Readings for White's Tree Frog (Tank 3)
INSERT INTO reading (temperature, humidity, timestamp, tank_id, temperature_status, humidity_status) VALUES
(83.2, 65.8, NOW() - INTERVAL 2 HOUR, 3, 'OPTIMAL', 'OPTIMAL'),
(82.7, 67.3, NOW() - INTERVAL 4 HOUR, 3, 'OPTIMAL', 'OPTIMAL'),
(85.1, 62.5, NOW() - INTERVAL 6 HOUR, 3, 'TOO_HIGH', 'OPTIMAL'),
(81.3, 71.8, NOW() - INTERVAL 8 HOUR, 3, 'OPTIMAL', 'TOO_HIGH'),
(83.8, 64.2, NOW() - INTERVAL 10 HOUR, 3, 'OPTIMAL', 'OPTIMAL'),
(79.8, 66.7, NOW() - INTERVAL 12 HOUR, 3, 'TOO_LOW', 'OPTIMAL');

-- Insert some historical data (past days)
INSERT INTO reading (temperature, humidity, timestamp, tank_id, temperature_status, humidity_status) VALUES
-- Yesterday's readings
(86.2, 56.8, NOW() - INTERVAL 1 DAY - INTERVAL 9 HOUR, 1, 'OPTIMAL', 'OPTIMAL'),
(74.1, 77.2, NOW() - INTERVAL 1 DAY - INTERVAL 9 HOUR, 2, 'OPTIMAL', 'OPTIMAL'),
(83.5, 66.1, NOW() - INTERVAL 1 DAY - INTERVAL 9 HOUR, 3, 'OPTIMAL', 'OPTIMAL'),

-- Two days ago
(84.8, 54.2, NOW() - INTERVAL 2 DAY - INTERVAL 11 HOUR, 1, 'OPTIMAL', 'OPTIMAL'),
(73.8, 75.9, NOW() - INTERVAL 2 DAY - INTERVAL 11 HOUR, 2, 'OPTIMAL', 'OPTIMAL'),
(82.9, 68.3, NOW() - INTERVAL 2 DAY - INTERVAL 11 HOUR, 3, 'OPTIMAL', 'OPTIMAL');