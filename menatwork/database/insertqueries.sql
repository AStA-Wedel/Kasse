DELETE FROM stuff;
DELETE FROM packet;
DELETE FROM article;
DELETE FROM lecturer;
DELETE FROM packet_part;
DELETE FROM script;
DELETE FROM tax_category;
delete from person;
delete from person_group;

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
INSERT INTO article(article_revision, article_name, price, tax_category_name, tax_revision, enabled)
VALUES(0, 'Collegeblock', 130, 'REDU', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
SELECT article_id, article_revision
FROM article
WHERE article_name = 'Collegeblock';

/* Bratwurst als Stuff */
INSERT INTO article(article_revision, article_name, price, tax_category_name, tax_revision, enabled)
VALUES(0, 'Bratwurst', 150, 'NONE', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
SELECT article_id, article_revision
FROM article
WHERE article_name = 'Bratwurst';

/* Kugelschreiber als Stuff */
INSERT INTO article( article_revision, article_name, price, tax_category_name, tax_revision, enabled )
VALUES(0, 'Kugelschreiber', 150, 'NONE', 0, true);

INSERT INTO stuff( stuff_id, stuff_revision )
SELECT article_id, article_revision
FROM article
WHERE article_name = 'Kugelschreiber';

/* ABWL als Skript */
INSERT INTO article(article_revision, article_name, price, tax_category_name, tax_revision, enabled)
VALUES(0, 'Allgemeine BWL', 2285, 'NONE', 0, true);

INSERT INTO script( script_id, script_revision, lecturer_id )
SELECT article_id, article_revision, (SELECT lecturer_id FROM lecturer WHERE prename = 'Gunnar') 
FROM article
WHERE article_name = 'Allgemeine BWL';


/* ----------------------------------------------- */

insert into person_group
values(0, 'Admin', false, false);

insert into person
values('inf9446', 0, 'Wefers', 'Julian', 9446, true, (select group_id from person_group where group_name = 'Admin'));