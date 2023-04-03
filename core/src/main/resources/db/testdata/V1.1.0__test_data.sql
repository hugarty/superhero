INSERT INTO power_stats (id, strength, agility, dexterity, intelligence) 
VALUES ('e2f066ca-5973-4581-8b2b-a062d7d76f71',1,2,3,4)
-- ON CONFLICT DO NOTHING
;


INSERT INTO hero (id, name, race, power_stats_id)
VALUES ('e2f066ca-5973-4581-8b2b-a062d7d76f71', 'jose', 'HUMAN', 'e2f066ca-5973-4581-8b2b-a062d7d76f71')
-- ON CONFLICT DO NOTHING
;
