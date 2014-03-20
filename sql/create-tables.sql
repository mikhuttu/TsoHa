create table kilpailu (
	ID 		SERIAL 		PRIMARY KEY,
	Nimi		varchar(30)					NOT NULL
);

create table kilpailija (
	ID 		SERIAL		PRIMARY KEY,
	Nimi		varchar(50)					NOT NULL
);

create table valiaikapiste (
	ID		SERIAL		PRIMARY KEY,
	Kilpailu	int references kilpailu(ID)			NOT NULL
);

create table tulos (
	ID		SERIAL		PRIMARY KEY,
	Aika		time						NOT NULL,
	Kilpailija	int references kilpailija(ID)			NOT NULL,
	Valiaikapiste	int references valiaikapiste(ID)		NOT NULL
);

create table osallistuja (
	Kilpailu	int references kilpailu(ID)			NOT NULL,
	Kilpailija	int references kilpailija(ID)			NOT NULL
);
