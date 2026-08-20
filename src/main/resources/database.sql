CREATE TYPE account_type AS ENUM ('STANDARD', 'PREMIUM', 'GOLD');
CREATE TYPE transaction_type AS ENUM ('IN', 'OUT');

CREATE TABLE account (
                         id VARCHAR(255) PRIMARY KEY,
                         account_type account_type NOT NULL
);

CREATE TABLE transaction_table (
                                   id VARCHAR(255) PRIMARY KEY,
                                   created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                   transaction_type transaction_type NOT NULL,
                                   amount NUMERIC(19, 4) NOT NULL,
                                   reason VARCHAR(500),
                                   account_id VARCHAR(255) NOT NULL,
                                   CONSTRAINT fk_account
                                       FOREIGN KEY (account_id)
                                           REFERENCES account(id)
                                           ON DELETE CASCADE
);