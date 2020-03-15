BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Znamenitost " (
  "id"	INTEGER,
	"naziv"	TEXT,
	"slika"	TEXT,
	"grad_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("grad_id") REFERENCES "grad"
  );
CREATE TABLE IF NOT EXISTS "grad" (
  "id"	INTEGER,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	INTEGER,
	"postanski_broj"	INTEGER,
	FOREIGN KEY("drzava") REFERENCES "drzava",
	PRIMARY KEY("id")
  );
CREATE TABLE IF NOT EXISTS "drzava" (
  "id"	INTEGER,
	"naziv"	TEXT,
	"glavni_grad"	INTEGER,
	PRIMARY KEY("id")
  );
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (1,'Pariz',2206488,1,71000);
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (2,'London',15000,2,71000);
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (3,'Beč',1899055,3,71000);
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (4,'Manchester',545500,2,71000);
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (5,'Graz',280200,3,71000);
INSERT INTO "grad" ("id","naziv","broj_stanovnika","drzava","postanski_broj") VALUES (6,'Sarajevo',350000,3,71000);
INSERT INTO "drzava" ("id","naziv","glavni_grad") VALUES (1,'Francuska',1);
INSERT INTO "drzava" ("id","naziv","glavni_grad") VALUES (2,'Velika Britanija',2);
INSERT INTO "drzava" ("id","naziv","glavni_grad") VALUES (3,'Austrija',3);
COMMIT;
