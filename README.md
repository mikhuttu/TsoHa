TsoHa
=====

Tietokantasovellus (Hiihtokisojen tulospalvelu)


ConnectionTest on lakkasi toimimasta sunnuntaip�iv�ll� yht�kki� sen j�lkeen kun olin tehnyt tietokantaan muutoksia k�ytt�en
"psql < *.sql" lauseita. Ongelmana tulee virheilmoitus: 

FATAL: too many connections for too many connections for role "mikhuttu"org.postgresql.util.PSQLException: 
FATAL: too many connections for role "mikhuttu".

Sain kuitenkin tietokannan t�t� ennen toimimaan oikein ja en nyt tied� yht��n, miss� on vika.