
class KategoriaAjanlasok:
    def __init__(self, termekek:list, bolt, felhasznalo): # termekek[[bolti_aru_nev, sajat_aru_nev], []]
        self.javaslatok = []
        self.termekek = termekek
        self.bolt = bolt
        self.felhasznalo = felhasznalo
        for termek in self.termekek:
            self.javaslatok.append(self.kategoriaJavaslat(termek))

    def kategoriaJavaslat(self, termek) -> list:
        javaslat = []
        javaslat.append(self.kategoriaFelhasznaloSzerint(termek))
        javaslat.append(self.kategoriaMasFelhasznaloSzerint(termek))
        javaslat.append(self.kategoriaStandardSzerint(termek))
        javaslat.append(self.kategoriaBoltSzerint(termek))

        self.javaslatok.append(javaslat)

        return javaslat

    def kategoriaFelhasznaloSzerint(self, termek) -> list:
        javaslat = []

        return javaslat

    def kategoriaMasFelhasznaloSzerint(self, termek) -> list:
        javaslat = []

        return javaslat

    def kategoriaStandardSzerint(self, termek) -> list:
        javaslat = []

        return javaslat

    def kategoriaBoltSzerint(self, termek) -> list:
        javaslat = []

        return javaslat

    def javaslatKeres(self) -> list:
        return self.javaslatok
