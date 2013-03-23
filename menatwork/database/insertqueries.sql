USE kasse;
delete from category_mapping;
DELETE FROM stuff;
DELETE FROM packet_part;
DELETE FROM packet;
DELETE FROM script;
DELETE FROM article;
DELETE FROM lecturer;
DELETE FROM tax_category;
delete from person;
delete from person_group;
delete from category;


INSERT INTO tax_category(tax_category_name, tax_revision, tax_rate)
VALUES('REDU', 0, 0.07);

INSERT INTO tax_category(tax_category_name, tax_revision, tax_rate)
VALUES('NONE', 0, 0.00);

INSERT INTO tax_category(tax_category_name, tax_revision, tax_rate)
VALUES('FULL', 0, 0.19);

/* ----------------------------------------------- */


/* neuer Dozent */
INSERT INTO lecturer( prename, surname, title )
VALUES( 'Gunnar', 'Harms', 'Prof. Dr.' );


/* ----------------------------------------------- */



/* Collegeblock als Stuff */
INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Collegeblock', 130, 'REDU', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
	SELECT article_id, article_revision
	FROM article
	WHERE name = 'Collegeblock';

/* Bratwurst als Stuff */
INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Bratwurst', 150, 'NONE', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
	SELECT article_id, article_revision
	FROM article
	WHERE name = 'Bratwurst';

/* Kugelschreiber als Stuff */
INSERT INTO article( article_revision, name, price, tax_category_name, tax_revision, enabled )
	VALUES(0, 'Kugelschreiber', 150, 'NONE', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
	SELECT article_id, article_revision
	FROM article
	WHERE name = 'Kugelschreiber';

/* ABWL als Skript */
INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Allgemeine BWL', 2285, 'NONE', 0, true);

INSERT INTO script( script_id, script_revision, lecturer_id )
	SELECT article_id, article_revision, (SELECT lecturer_id FROM lecturer WHERE prename = 'Gunnar')
	FROM article
	WHERE name = 'Allgemeine BWL';

/* Paket Religion (lol) */
INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Religion Starterpaket', 330, 'NONE', 0, true);
INSERT INTO packet( packet_id, packet_revision )
	SELECT article_id, article_revision
	FROM article
	WHERE name = 'Religion Starterpaket';

INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Erkenntnistheorie 1', 150, 'NONE', 0, true);
INSERT INTO script( script_id, script_revision, lecturer_id )
	SELECT article_id, article_revision, (SELECT lecturer_id FROM lecturer WHERE prename = 'Gunnar')
	FROM article
	WHERE name = 'Erkenntnistheorie 1';
INSERT INTO packet_part( packet_id, packet_revision, article_id, article_revision )
	SELECT p.packet_id, p.packet_revision, a2.article_id, a2.article_revision
	FROM packet p, article a1, article a2
	WHERE p.packet_id = a1.article_id
	AND a1.name = 'Religion Starterpaket'
	AND a1.article_revision = 0
	AND a2.name = 'Erkenntnistheorie 1'
	AND a2.article_revision = 0;

INSERT INTO article(article_revision, name, price, tax_category_name, tax_revision, enabled)
	VALUES(0, 'Ev. Religion', 180, 'NONE', 0, true);
INSERT INTO script( script_id, script_revision, lecturer_id )
	SELECT article_id, article_revision, (SELECT lecturer_id FROM lecturer WHERE prename = 'Gunnar')
	FROM article
	WHERE name = 'Ev. Religion';
INSERT INTO packet_part( packet_id, packet_revision, article_id, article_revision )
	SELECT p.packet_id, p.packet_revision, a2.article_id, a2.article_revision
	FROM packet p, article a1, article a2
	WHERE p.packet_id = a1.article_id
	AND a1.name = 'Religion Starterpaket'
	AND a1.article_revision = 0
	AND a2.name = 'Ev. Religion'
	AND a2.article_revision = 0;


/* ---------------Cats  ---------------------------*/

INSERT INTO category (category_name) VALUES ('Obst');
INSERT INTO category (category_name) VALUES ('Skripte');
INSERT INTO category (category_name) VALUES ('Bürozeugs');

INSERT INTO category_mapping(category_id, article_id, article_revision)
	SELECT (SELECT category_id FROM category WHERE category_name = 'Skripte'), article_id, article_revision FROM article
	WHERE name = 'Ev. Religion';

INSERT INTO category_mapping(category_id, article_id, article_revision)
	SELECT (SELECT category_id FROM category WHERE category_name = 'Bürozeugs'), article_id, article_revision FROM article
	WHERE name = 'Allgemeine BWL';

/* ----------------------------------------------- */

insert into person_group
values(0, 'default');
values(1, 'Student');

insert into person
values('default', 0, 'default', 'default', NULL, false, 0,            NULL, NULL,          NULL,    NULL,        NULL, NULL,                      NULL);
values('inf9446', 0, 'Wefers', 'Julian',   9446, true,  1, '+491726627997', NULL, 'Schulenhörn', '25421', 'Pinneberg', '4a', 'julian.wefers@gmail.com');
