DROP DATABASE IF EXISTS tourease_db;
CREATE DATABASE tourease_db;
USE tourease_db;

CREATE TABLE users (
    userId VARCHAR(20) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE customers (
    customerId VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(50),
    nidOrPassport VARCHAR(30)
);

CREATE TABLE hotels (
    hotelId VARCHAR(20) PRIMARY KEY,
    hotelName VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    pricePerNight DOUBLE NOT NULL
);

CREATE TABLE transport (
    transportId VARCHAR(20) PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    providerName VARCHAR(100) NOT NULL
);

CREATE TABLE packages (
    packageId VARCHAR(20) PRIMARY KEY,
    destination VARCHAR(100) NOT NULL,
    category VARCHAR(20) NOT NULL,
    price DOUBLE NOT NULL,
    duration VARCHAR(50) NOT NULL,
    hotelId VARCHAR(20),
    transportId VARCHAR(20),
    FOREIGN KEY (hotelId) REFERENCES hotels(hotelId) ON DELETE SET NULL,
    FOREIGN KEY (transportId) REFERENCES transport(transportId) ON DELETE SET NULL
);

CREATE TABLE bookings (
    bookingId VARCHAR(20) PRIMARY KEY,
    customerId VARCHAR(20),
    packageId VARCHAR(20),
    bookingDate DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES customers(customerId) ON DELETE CASCADE,
    FOREIGN KEY (packageId) REFERENCES packages(packageId) ON DELETE CASCADE
);

CREATE TABLE payments (
    paymentId VARCHAR(20) PRIMARY KEY,
    bookingId VARCHAR(20),
    amountPaid DOUBLE NOT NULL,
    paymentDate DATE NOT NULL,
    paymentMode VARCHAR(30) NOT NULL,
    FOREIGN KEY (bookingId) REFERENCES bookings(bookingId) ON DELETE CASCADE
);



-- 1. Users Table (System Access Accounts)
INSERT INTO users (userId, username, password, role) VALUES 
('U001', 'admin', '1234', 'Admin'),
('U002', 'employee', '1234', 'Employee'),
('U003', 'sajid_dev', '1234', 'Admin'),
('U004', 'jessica_ops', '1234', 'Employee'),
('U005', 'tanvir_sales', '1234', 'Employee'),
('U006', 'manager_hq', '1234', 'Admin');

-- 2. Customers Table (Client Base)
INSERT INTO customers (customerId, name, phone, email, nidOrPassport) VALUES 
('C001', 'Rahim Ali', '01711223344', 'rahim@mail.com', 'NID123456'),
('C002', 'Fahim Ahmed', '01822334455', 'fahim.ahmed@yahoo.com', 'NID987654'),
('C003', 'Nusrat Jahan', '01933445566', 'nusrat.j@gmail.com', 'PASS_E001122'),
('C004', 'Zayan Karim', '01544556677', 'zayan.karim@outlook.com', 'NID456123'),
('C005', 'Anika Tabassum', '01655667788', 'anika.t@mail.com', 'NID789321'),
('C006', 'Imran Khan', '01366778899', 'imran.khan@gmail.com', 'PASS_A998877'),
('C007', 'Sabina Yasmin', '01777889900', 'sabina.y@hotmail.com', 'NID654987'),
('C008', 'Mahbubur Rahman', '01888990011', 'mahbub.r@gmail.com', 'NID321654'),
('C009', 'Sadia Afrin', '01999001122', 'sadia.afrin@live.com', 'PASS_B554433'),
('C010', 'Asif Iqbal', '01555112233', 'asif.iqbal@tech.com', 'NID159753');

-- 3. Hotels Table (Accommodations)
INSERT INTO hotels (hotelId, hotelName, location, pricePerNight) VALUES 
('H001', 'Ocean Paradise', 'Cox\'s Bazar', 5000.0),
('H002', 'Sayeman Beach Resort', 'Cox\'s Bazar', 7500.0),
('H003', 'Grand Sultan Tea Resort', 'Sreemangal', 9000.0),
('H004', 'Hotel Rose View', 'Sylhet', 4500.0),
('H005', 'The Peninsula', 'Chittagong', 6500.0),
('H006', 'Marina Bay Sands', 'Singapore', 35000.0),
('H007', 'Amari Watergate', 'Bangkok', 12000.0),
('H008', 'Swissôtel Al Maqam', 'Makkah', 22000.0),
('H009', 'Burj Al Arab', 'Dubai', 85000.0),
('H010', 'Hilton Tokyo', 'Japan', 28000.0);

-- 4. Transport Table (Logistics Providers)
INSERT INTO transport (transportId, type, providerName) VALUES 
('T001', 'Bus', 'Green Line Paribahan'),
('T002', 'Bus', 'Hanif Enterprise'),
('T003', 'Train', 'Subarna Express (Bangladesh Railway)'),
('T004', 'Flight', 'Biman Bangladesh Airlines'),
('T005', 'Flight', 'US-Bangla Airlines'),
('T006', 'Flight', 'Singapore Airlines'),
('T007', 'Flight', 'Emirates'),
('T008', 'Flight', 'Thai Airways'),
('T009', 'Flight', 'Saudia Airlines'),
('T010', 'Train', 'Shinkansen Bullet Train');

-- 5. Packages Table (Available Tours)
INSERT INTO packages (packageId, destination, category, price, duration, hotelId, transportId) VALUES 
('P001', 'Cox\'s Bazar Escape', 'Domestic', 12000.0, '3 Days / 2 Nights', 'H001', 'T001'),
('P002', 'Cox\'s Bazar Luxury', 'Domestic', 22000.0, '4 Days / 3 Nights', 'H002', 'T004'),
('P003', 'Sreemangal Nature Tour', 'Domestic', 15000.0, '3 Days / 2 Nights', 'H003', 'T002'),
('P004', 'Sylhet Heritage Tour', 'Domestic', 11000.0, '3 Days / 2 Nights', 'H004', 'T003'),
('P005', 'Singapore City Splendor', 'International', 65000.0, '5 Days / 4 Nights', 'H006', 'T006'),
('P006', 'Bangkok Budget Getaway', 'International', 38000.0, '4 Days / 3 Nights', 'H007', 'T005'),
('P007', 'Holy Umrah Package', 'International', 165000.0, '10 Days / 9 Nights', 'H008', 'T009'),
('P008', 'Dubai Desert Adventure', 'International', 95000.0, '6 Days / 5 Nights', 'H009', 'T007'),
('P009', 'Tokyo Cherry Blossom', 'International', 18000.0, '7 Days / 6 Nights', 'H010', 'T010'),
('P010', 'Chittagong Business Deal', 'Domestic', 9500.0, '2 Days / 1 Night', 'H005', 'T003');

-- 6. Bookings Table (Reservations Log)
INSERT INTO bookings (bookingId, customerId, packageId, bookingDate, status) VALUES 
('B001', 'C001', 'P001', '2026-05-10', 'Confirmed'),
('B002', 'C002', 'P002', '2026-05-15', 'Confirmed'),
('B003', 'C003', 'P005', '2026-05-20', 'Confirmed'),
('B004', 'C004', 'P007', '2026-05-22', 'Pending'),
('B005', 'C005', 'P003', '2026-05-25', 'Confirmed'),
('B006', 'C006', 'P006', '2026-06-01', 'Cancelled'),
('B007', 'C007', 'P008', '2026-06-02', 'Confirmed'),
('B008', 'C008', 'P004', '2026-06-04', 'Confirmed'),
('B009', 'C009', 'P009', '2026-06-05', 'Pending'),
('B010', 'C010', 'P001', '2026-06-08', 'Confirmed');

-- 7. Payments Table (Financial Accounts Ledger)
INSERT INTO payments (paymentId, bookingId, amountPaid, paymentDate, paymentMode) VALUES 
('PAY001', 'B001', 12000.0, '2026-05-10', 'bKash'),
('PAY002', 'B002', 22000.0, '2026-05-16', 'Bank Transfer'),
('PAY003', 'B003', 65000.0, '2026-05-20', 'Credit Card'),
('PAY004', 'B004', 50000.0, '2026-05-22', 'Cash'), -- Partial/Pending payment
('PAY005', 'B005', 15000.0, '2026-05-26', 'bKash'),
('PAY006', 'B007', 95000.0, '2026-06-02', 'Credit Card'),
('PAY007', 'B008', 11000.0, '2026-06-04', 'Nagad'),
('PAY008', 'B010', 12000.0, '2026-06-08', 'Cash');