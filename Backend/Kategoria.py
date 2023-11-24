
import unittest
from flask_sqlalchemy import SQLAlchemy

class KategoriaAjanlasok:
    """
    A beadott termékekre küld vissza egy-egy nyolc részből álló javaslatot,
    melyek sorrendje egyben valószínűségi sorrend is.

    Kategória javaslat típusok szorzói:
    Felhasználó előzményei: 3x
    Más felhasználók előzményei: 2x
    Standard adatok: 2x
    Bolt: 1x

    Amennyiben a kategória javaslat típusokba több elem is tartozik, azon belüli valószínűségeket is számolunk.
    Pl: A felhasználók 99%-a szerint az alma gyümölcs, 1%-a szerint zöldség, akkor a javaslatban
        {gyümölcs:1.98, zöldség:0.02} jelenik meg.

    @:param:
        termekek: list [bolti áru név, saját áru név]
        bolt: string
        felhasznalonev: string
        kategoriak: string
        tranzakciok: list || az eddigi tranzakciók azon elemei, amik információval szolgálnak a kategória javaslathoz

     """
    def __init__(self, bolt, felhasznalonev, kategoriak, tranzakciok): # termekek[[bolti_aru_nev, sajat_aru_nev], []]
        self.javaslat = []
        self.bolt = bolt
        self.felhasznalo = felhasznalonev
        self.kategoriak = kategoriak
        self.tranzakciok = tranzakciok


    def javaslat(self, termek):
        javaslat = self.kategoriaJavaslat(termek)
        for k, v in javaslat.items():
            self.javaslat.append([k, v])


    def kategoriaJavaslat(self, termek) -> dict:
        """összefűzi a különboző javaslatokat"""
        javaslat = {}
        for k, v in self.kategoriaFelhasznaloSzerint(termek).items():
            if javaslat.get(k):
                javaslat[k] += v
            else:
                javaslat[k] = v
        for k, v in self.kategoriaMasFelhasznaloSzerint(termek).items():
            if javaslat.get(k):
                javaslat[k] += v
            else:
                javaslat[k] = v
        for k, v in self.kategoriaStandardSzerint(termek).items():
            if javaslat.get(k):
                javaslat[k] += v
            else:
                javaslat[k] = v
        for k, v in self.kategoriaBoltSzerint(termek).items():
            if javaslat.get(k):
                javaslat[k] += v
            else:
                javaslat[k] = v

        return javaslat

    def kategoriaFelhasznaloSzerint(self, termek) -> dict:
        """javaslat készítés felhasználói előzményekből"""

        reszlet = []
        for tranzakcio in self.tranzakciok:
            if tranzakcio[0] == self.felhasznalo and (tranzakcio[2] == termek or tranzakcio[3] == termek):
                reszlet.append(tranzakcio[1])

        javaslat = {}

        for resz in reszlet:
            if javaslat.get(resz):
                javaslat[resz] += 1
            else:
                javaslat[resz] = 1

        return javaslat

    def kategoriaMasFelhasznaloSzerint(self, termek) -> dict:
        """javaslat készítés a többi felhasználó előzményéből"""
        reszlet = []
        for tranzakcio in self.tranzakciok:
            if tranzakcio[0] != self.felhasznalo and (tranzakcio[2] == termek or tranzakcio[3] == termek):
                reszlet.append(tranzakcio[1])

        javaslat = {}

        for resz in reszlet:
            if javaslat.get(resz):
                javaslat[resz] += 1
            else:
                javaslat[resz] = 1

        return javaslat

    def kategoriaStandardSzerint(self, termek) -> dict:
        """javaslat készítés előre megadott adatok alapján"""
        javaslat = {}

        return javaslat

    def kategoriaBoltSzerint(self, termek) -> dict:
        """javaslat készítés a bolt alapján"""
        javaslat = {}

        return javaslat

    def javaslatKeres(self) -> list:
        return self.javaslat

def felhasznalo_kategoriak(Felhasznalo, felhasznalonev):
    felhasznalo_kategoriak = Felhasznalo.query.filter_by(felhasznalonev=felhasznalonev).first().kategoriak

def tranzakciok_db_szures(Tranzakcio):
    tranzakciok_db_reszlet = []
    tranzakciok = Tranzakcio.query.all()
    for tranzakcio in tranzakciok:
        tranzakciok_db_reszlet.append([])
        tranzakciok_db_reszlet[-1].append(tranzakcio.felhasznalonev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.tipus)
        tranzakciok_db_reszlet[-1].append(tranzakcio.bolti_aru_nev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.sajat_aru_nev)


"""kategoriak = Kategoria.KategoriaAjanlasok(
        bolt=nyugta_adatok["bolt_nev"],
        felhasznalonev=felhasznalonev,
        kategoriak=felhasznalo_kategoriak,
        tranzakciok=tranzakciok_db_reszlet,
    )"""