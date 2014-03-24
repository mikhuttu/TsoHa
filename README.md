TsoHa
=====

Tietokantasovellus (Hiihtokisojen tulospalvelu)


ConnectionTest on lakkasi toimimasta sunnuntaipäivällä yhtäkkiä sen jälkeen kun olin tehnyt tietokantaan muutoksia käyttäen
"psql < *.sql" lauseita. Ongelmana tulee virheilmoitus: 

FATAL: too many connections for too many connections for role "mikhuttu"org.postgresql.util.PSQLException: 
FATAL: too many connections for role "mikhuttu".

Sain kuitenkin tietokannan tätä ennen toimimaan oikein ja en nyt tiedä yhtään, missä on vika.