
import unittest

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
    def __init__(self, termekek:list, bolt, felhasznalonev, kategoriak, tranzakciok): # termekek[[bolti_aru_nev, sajat_aru_nev], []]
        self.javaslatok = []
        self.termekek = termekek
        self.bolt = bolt
        self.felhasznalo = felhasznalonev
        self.kategoriak = kategoriak
        self.tranzakciok = tranzakciok
        for termek in self.termekek:
            self.javaslatok.append(self.kategoriaJavaslat(termek))

        self.javaslatKeres()

    def kategoriaJavaslat(self, termek) -> list:
        """összefűzi a különboző javaslatokat"""
        javaslat = []
        javaslat.append(self.kategoriaFelhasznaloSzerint(termek))
        javaslat.append(self.kategoriaMasFelhasznaloSzerint(termek))
        javaslat.append(self.kategoriaStandardSzerint(termek))
        javaslat.append(self.kategoriaBoltSzerint(termek))

        self.javaslatok.append(javaslat)

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
            if tranzakcio[0] == self.felhasznalo and (tranzakcio[2] == termek or tranzakcio[3] == termek):
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
        return self.javaslatok
