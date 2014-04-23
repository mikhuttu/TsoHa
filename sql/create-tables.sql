create table kilpailu (
	kilpailuId 	SERIAL 		PRIMARY KEY,
	nimi		varchar(30)					NOT NULL
);

create table kilpailija (
	kilpailijaId 	SERIAL		PRIMARY KEY,
	nimi		varchar(50)					NOT NULL
);

create table valiaikapiste (
	valiaikapisteId	SERIAL		PRIMARY KEY,
	numero		int						NOT NULL,
	kilpailu	int references kilpailu				ON DELETE CASCADE NOT NULL
);

create table tulos (
	tulosId		SERIAL		PRIMARY KEY,
	aika		varchar(10)					NOT NULL,
	kilpailija	int references kilpailija			ON DELETE CASCADE NOT NULL,
	valiaikapiste	int references valiaikapiste			ON DELETE CASCADE NOT NULL
);

create table osallistuja (
	kilpailu	int references kilpailu				ON DELETE CASCADE NOT NULL,
	kilpailija	int references kilpailija			ON DELETE CASCADE NOT NULL
);

create table kayttaja (
	tunnus		varchar(20)	PRIMARY KEY,
	salasana	varchar(20)					NOT NULL
);
