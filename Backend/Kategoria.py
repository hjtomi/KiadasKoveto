
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
        [[alma, 1.98][zöldség, 0.02] jelenik meg.

    @:param:
        termekek: list [bolti áru név, saját áru név]
        bolt: string
        felhasznalonev: string

     """
    def __init__(self, termekek:list, bolt, felhasznalonev): # termekek[[bolti_aru_nev, sajat_aru_nev], []]
        self.javaslatok = []
        self.termekek = termekek
        self.bolt = bolt
        self.felhasznalo = felhasznalonev
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

    def kategoriaFelhasznaloSzerint(self, termek) -> list:
        """javaslat készítés felhasználói előzményekből"""
        javaslat = []

        return javaslat

    def kategoriaMasFelhasznaloSzerint(self, termek) -> list:
        """javaslat készítés a többi felhasználó előzményéből"""
        javaslat = []

        return javaslat

    def kategoriaStandardSzerint(self, termek) -> list:
        """javaslat készítés előre megadott adatok alapján"""
        javaslat = []

        return javaslat

    def kategoriaBoltSzerint(self, termek) -> list:
        """javaslat készítés a bolt alapján"""
        javaslat = []

        return javaslat

    def javaslatKeres(self) -> list:
        return self.javaslatok
