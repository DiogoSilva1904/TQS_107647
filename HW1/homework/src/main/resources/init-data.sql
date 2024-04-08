-- DATABASE INITIALIZATION SCRIPT

INSERT INTO stop (name,stopOrder) VALUES ('Aveiro',1);
INSERT INTO stop (name,stopOrder) VALUES ('Coimbra',2);
INSERT INTO stop (name,stopOrder) VALUES ('Lisboa',3);
INSERT INTO stop (name,stopOrder) VALUES ('Faro',4);
INSERT INTO stop (name,stopOrder) VALUES ('Porto',1);
INSERT INTO stop (name,stopOrder) VALUES ('Viseu',2);
INSERT INTO stop (name,stopOrder) VALUES ('Guarda',1);
INSERT INTO stop (name,stopOrder) VALUES ('Braga',2);
INSERT INTO stop (name,stopOrder) VALUES ('Viana do Castelo',3);

DECLARE @AveiroId INT, @CoimbraId INT, @LisboaId INT, @FaroId INT;

SELECT @AveiroId = id FROM stop WHERE name = 'Aveiro';
SELECT @CoimbraId = id FROM stop WHERE name = 'Coimbra';
SELECT @LisboaId = id FROM stop WHERE name = 'Lisboa';
SELECT @FaroId = id FROM stop WHERE name = 'Faro';
SELECT @PortoId = id FROM stop WHERE name = 'Porto';
SELECT @ViseuId = id FROM stop WHERE name = 'Viseu';
SELECT @GuardaId = id FROM stop WHERE name = 'Guarda';
SELECT @BragaId = id FROM stop WHERE name = 'Braga';
SELECT @VianaDoCasteloId = id FROM stop WHERE name = 'Viana do Castelo';


INSERT INTO route (departureTime,arrivalTime,stops) VALUES ('2020-01-01 08:00:00','2020-01-01 10:00:00',[@AveiroId,@CoimbraId,@LisboaId,@FaroId]);
INSERT INTO route (departureTime,arrivalTime,stops) VALUES ('2020-01-01 09:00:00','2020-01-01 11:00:00',[@PortoId,@ViseuId]);
INSERT INTO route (departureTime,arrivalTime,stops) VALUES ('2020-01-01 10:00:00','2020-01-01 12:00:00',[@GuardaId,@BragaId,@VianaDoCasteloId]);

