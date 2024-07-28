ALTER TABLE ads
    ADD COLUMN type_of_vehicle VARCHAR(255) NOT NULL ,
    ADD COLUMN traffic_signs VARCHAR(10) NOT NULL,
    ADD COLUMN car_color VARCHAR(50) NOT NULL,
    ADD COLUMN type_of_direction VARCHAR(50) NOT NULL,
    ADD COLUMN gear_box VARCHAR(50) NOT NULL,
    ADD COLUMN engine_power DECIMAL(3,1) NOT NULL