INSERT INTO account (id, account_type) VALUES
                                           ('acc-001', 'STANDARD'),
                                           ('acc-002', 'PREMIUM'),
                                           ('acc-003', 'GOLD');

INSERT INTO transaction_table (id, created_at, transaction_type, amount, reason, account_id) VALUES
                                                                                                 ('tx-101', '2026-08-20 10:00:00+00', 'IN', 1500.0000, 'Salaires', 'acc-001'),
                                                                                                 ('tx-102', '2026-08-20 12:30:00+00', 'OUT', 45.5000, 'Restaurant', 'acc-001'),
                                                                                                 ('tx-201', '2026-08-20 14:15:00+00', 'IN', 5000.0000, 'Prime annuelle', 'acc-002'),
                                                                                                 ('tx-301', '2026-08-20 16:00:00+00', 'OUT', 1200.0000, 'Voyage', 'acc-003');