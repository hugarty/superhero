INSERT INTO power_stats (id, strength, agility, dexterity, intelligence) 
VALUES ('e2f066ca-5973-4581-8b2b-a062d7d76f72',4,3,2,1)
-- ON CONFLICT DO NOTHING
;


INSERT INTO hero (id, name, race, power_stats_id)
VALUES ('e2f066ca-5973-4581-8b2b-a062d7d76f72', 'jose carlos', 'DIVINE', 'e2f066ca-5973-4581-8b2b-a062d7d76f72')
-- ON CONFLICT DO NOTHING
;
