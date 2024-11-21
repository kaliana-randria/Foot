-- PURGE RECYCLEBIN;

-- UPDATE nom_table
-- SET colonne1 = nouvelle_valeur1, colonne2 = nouvelle_valeur2, ...
-- WHERE condition;

CREATE TABLE Feu (
    id_Feu VARCHAR(2) PRIMARY KEY,
    Etat VARCHAR(5)
);

CREATE TABLE Secteur (
    id_Secteur VARCHAR(10) PRIMARY KEY,
    Nom VARCHAR(20)
);

CREATE TABLE Placement (
    id_Placement VARCHAR(2) PRIMARY KEY,
    Feu VARCHAR(2),
    Direction INT,
    Secteur VARCHAR(15),
    FOREIGN KEY (Secteur) REFERENCES Secteur(id_Secteur),
    FOREIGN KEY (Feu) REFERENCES Feu(id_Feu)
);

CREATE TABLE Deroulement (
    id_deroulement VARCHAR(2) PRIMARY KEY,
    Debut INT,
    Fin INT,
    Placement VARCHAR(2),
    Duree INT,
    FOREIGN KEY (Placement) REFERENCES Placement(id_Placement)
);

CREATE SEQUENCE seq_Feu start with 1 increment by 1;
INSERT INTO Feu VALUES ('F' || seq_Feu.NEXTVAL, 'vert');
INSERT INTO Feu VALUES ('F' || seq_Feu.NEXTVAL, 'rouge');

CREATE SEQUENCE seq_secteur start with 1 increment by 1;
INSERT INTO Secteur VALUES ('S' || seq_secteur.NEXTVAL, 'Tanjombato');
INSERT INTO Secteur VALUES ('S' || seq_secteur.NEXTVAL, 'Analakely');

CREATE SEQUENCE seq_Placement start with 1 increment by 1;
INSERT INTO Placement VALUES ('P' || seq_Placement.NEXTVAL, 'F1', 'S1', 2);
INSERT INTO Placement VALUES ('P' || seq_Placement.NEXTVAL, 'F2', 'S1', 1);
INSERT INTO Placement VALUES ('P' || seq_Placement.NEXTVAL, 'F2', 'S1', 1);
INSERT INTO Placement VALUES ('P' || seq_Placement.NEXTVAL, 'F1', 'S2', 1);
INSERT INTO Placement VALUES ('P' || seq_Placement.NEXTVAL, 'F2', 'S2', 2);

CREATE SEQUENCE seq_Deroulement start with 1 increment by 1;
-- INSERT INTO Deroulement (Debut, Fin, Placement, Duree) VALUES (18, 19, 'P2', 50);

ALTER TABLE Feu MODIFY id_Feu VARCHAR(10);
ALTER TABLE Placement MODIFY id_Placement VARCHAR(10);
ALTER TABLE Deroulement MODIFY id_deroulement VARCHAR(10);
ALTER TABLE Placement ADD FOREIGN KEY (Secteur) REFERENCES Secteur(id_Secteur);