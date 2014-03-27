create table kilpailu (
	id 		SERIAL 		PRIMARY KEY,
	nimi		varchar(30)					NOT NULL
);

create table kilpailija (
	id 		SERIAL		PRIMARY KEY,
	nimi		varchar(50)					NOT NULL
);

create table valiaikapiste (
	id		SERIAL		PRIMARY KEY,
	kilpailu	int references kilpailu(ID)			NOT NULL
);

create table tulos (
	id		SERIAL		PRIMARY KEY,
	aika		time						NOT NULL,
	kilpailija	int references kilpailija(ID)			NOT NULL,
	valiaikapiste	int references valiaikapiste(ID)		NOT NULL
);

create table osallistuja (
	kilpailu	int references kilpailu(ID)			NOT NULL,
	kilpailija	int references kilpailija(ID)			NOT NULL
);

create table kayttaja (
	tunnus		varchar(20)	PRIMARY KEY,
	salasana	varchar(20)					NOT NULL
);
