DROP TABLE IF EXISTS "android_metadata";
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO "android_metadata" VALUES('en_US');
DROP TABLE IF EXISTS "fruit";
CREATE TABLE "fruit" ("imageID" INTEGER NOT NULL ,"voicePath" VARCHAR,"name" VARCHAR,"_id" INTEGER PRIMARY KEY  NOT NULL );
INSERT INTO "fruit" VALUES('0x7f020001',NULL,'Banana',1);
INSERT INTO "fruit" VALUES('0x7f020000',NULL,'Apple',2);
INSERT INTO "fruit" VALUES('0x7f020002',NULL,'Kiwi',3);
INSERT INTO "fruit" VALUES('0x7f020003',NULL,'Pineapple',4);
INSERT INTO "fruit" VALUES('0x7f020004',NULL,'Strawberry',5);
INSERT INTO "fruit" VALUES('0x7f020005',NULL,'Watermelon',6);
