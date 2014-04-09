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
	numero		int						NOT NULL,
	kilpailu	int references kilpailu(ID)			ON DELETE CASCADE NOT NULL
);

create table tulos (
	id		SERIAL		PRIMARY KEY,
	aika		varchar(10)					NOT NULL,
	kilpailija	int references kilpailija(ID)			ON DELETE CASCADE NOT NULL,
	valiaikapiste	int references valiaikapiste(ID)		ON DELETE CASCADE NOT NULL
);

create table osallistuja (
	kilpailu	int references kilpailu(ID)			ON DELETE CASCADE NOT NULL,
	kilpailija	int references kilpailija(ID)			ON DELETE CASCADE NOT NULL
);

create table kayttaja (
	tunnus		varchar(20)	PRIMARY KEY,
	salasana	varchar(20)					NOT NULL
);
