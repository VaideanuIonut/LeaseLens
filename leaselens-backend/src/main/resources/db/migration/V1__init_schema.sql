-- V1__init_schema.sql - LeaseLens Complete Database Schema

CREATE SCHEMA IF NOT EXISTS public;
SET search_path TO public;

-- 1. Tabele de referință / independente
CREATE TABLE property_types (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tenants (
                         id SERIAL PRIMARY KEY,
                         company_name VARCHAR(150) NOT NULL,
                         contact_person VARCHAR(100),
                         email VARCHAR(150) NOT NULL,
                         phone VARCHAR(50),
                         tax_id VARCHAR(50) UNIQUE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabelul properties (depinde de property_types și users)
CREATE TABLE properties (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(150) NOT NULL,
                            address VARCHAR(255) NOT NULL,
                            city VARCHAR(100) NOT NULL,
                            property_type_id INT REFERENCES property_types(id),
                            asset_manager_id INT REFERENCES users(id),
                            total_area NUMERIC(10, 2),
                            number_of_units INT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Tabela units (depinde de properties)
CREATE TABLE units (
                       id SERIAL PRIMARY KEY,
                       property_id INT REFERENCES properties(id) ON DELETE CASCADE,
                       unit_number VARCHAR(50) NOT NULL,
                       floor INT,
                       area NUMERIC(10, 2),
                       status VARCHAR(50) DEFAULT 'AVAILABLE'
);

-- 4. Tabela leases (depinde de properties și tenants)
CREATE TABLE leases (
                        id SERIAL PRIMARY KEY,
                        property_id INT REFERENCES properties(id) ON DELETE CASCADE,
                        tenant_id INT REFERENCES tenants(id) ON DELETE CASCADE,
                        start_date DATE NOT NULL,
                        end_date DATE NOT NULL,
                        initial_rent_amount NUMERIC(12, 2) NOT NULL,
                        deposit_amount NUMERIC(12, 2),
                        status VARCHAR(50) DEFAULT 'ACTIVE',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Tabele asociative și de urmărire (dependente de leases / units)
CREATE TABLE lease_units (
                             id SERIAL PRIMARY KEY,
                             lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                             unit_id INT REFERENCES units(id) ON DELETE CASCADE
);

CREATE TABLE rent_roll (
                           id SERIAL PRIMARY KEY,
                           lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                           due_date DATE NOT NULL,
                           amount_due NUMERIC(12, 2) NOT NULL,
                           amount_paid NUMERIC(12, 2) DEFAULT 0,
                           payment_date DATE,
                           status VARCHAR(50) DEFAULT 'PENDING'
);

CREATE TABLE break_deadline (
                                id SERIAL PRIMARY KEY,
                                lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                                window_start_date DATE,
                                window_end_date DATE,
                                effective_date DATE,
                                status VARCHAR(50)
);

CREATE TABLE renewal_deadline (
                                  id SERIAL PRIMARY KEY,
                                  lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                                  decision_deadline DATE,
                                  renewal_effective_date DATE,
                                  status VARCHAR(50)
);

CREATE TABLE lease_expiry (
                              id SERIAL PRIMARY KEY,
                              lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                              expiry_date DATE,
                              action_status VARCHAR(50)
);

CREATE TABLE indexation_due (
                                id SERIAL PRIMARY KEY,
                                lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                                notification_deadline DATE,
                                indexation_date DATE,
                                applied_percentage NUMERIC(5, 2),
                                new_rent_amount NUMERIC(12, 2),
                                status VARCHAR(50)
);

CREATE TABLE rent_arrears (
                              id SERIAL PRIMARY KEY,
                              lease_id INT REFERENCES leases(id) ON DELETE CASCADE,
                              default_date DATE,
                              days_overdue INT,
                              total_amount_owed NUMERIC(12, 2),
                              cure_deadline DATE,
                              status VARCHAR(50)
);
-- Prevent the same unit from being attached to a lease more than once.
ALTER TABLE lease_units ADD CONSTRAINT uq_lease_units_lease_unit UNIQUE (lease_id, unit_id);
