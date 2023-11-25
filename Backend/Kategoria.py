
import Adatbazis
import operator

def felhasznalo_kategoriak(felhasznalonev):
    Felhasznalo = Adatbazis.adat_()
    felhasznalo_kategoriak = Felhasznalo.query.filter_by(felhasznalonev=felhasznalonev).first().kategoriak
    return felhasznalo_kategoriak

def tranzakciok_db_szures():
    tranzakciok_db_reszlet = []
    tranzakciok = Adatbazis.adatok_lekerese()
    for tranzakcio in tranzakciok:
        tranzakciok_db_reszlet.append([])
        tranzakciok_db_reszlet[-1].append(tranzakcio.felhasznalonev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.tipus)
        tranzakciok_db_reszlet[-1].append(tranzakcio.bolti_aru_nev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.bolt)

    return tranzakciok_db_reszlet

class KategoriaAjanlas:
    def __init__(self, felhasznalonev):
        self.tranzakciok = tranzakciok_db_szures()
        self.felhasznalo = felhasznalonev
        self.kategoriak = felhasznalo_kategoriak(felhasznalonev).split(";")

    def uj_ajanlas(self, bolt, termek):

        return self.javaslatok_osszefuzese(termek, bolt)

    def kategoriaFelhasznaloSzerint(self, termek) -> dict:
        """javaslat készítés a felhasználó előzményéből"""
        javaslat = {}
        db = 0
        for tranzakcio in self.tranzakciok:
            if tranzakcio[0] == self.felhasznalo and tranzakcio[2] == termek and tranzakcio[1] in self.kategoriak:
                if javaslat.get(tranzakcio[1]):
                    javaslat[tranzakcio[1]] += 1
                    db += 1
                else:
                    javaslat[tranzakcio[1]] = 1
                    db += 1

        javaslat_ = {k:v/db for k, v in javaslat.items()}

        return javaslat_

    def kategoriaMasFelhasznaloSzerint(self, termek) -> dict:
        """javaslat készítés a többi felhasználó előzményéből"""
        javaslat = {}
        db = 0
        for tranzakcio in self.tranzakciok:
            if tranzakcio[0] != self.felhasznalo and tranzakcio[2] == termek and tranzakcio[1] in self.kategoriak:
                if javaslat.get(tranzakcio[1]):
                    javaslat[tranzakcio[1]] += 1
                    db += 1
                else:
                    javaslat[tranzakcio[1]] = 1
                    db += 1

        javaslat_ = {k:v/db for k, v in javaslat.items()}

        return javaslat_

    def kategoriaStandardSzerint(self, termek) -> dict:
        """javaslat készítés előre megadott adatok alapján"""
        #Standard adatok elkészítése
        javaslat_ = {}
        '''db = 0
        for adat in standard.adatok():
            if adat[0] == termek  and adat[1] in self.kategoriak:
                if javaslat.get(adat[1]):
                    javaslat[adat[1]] += 1
                    db += 1
                else:
                    javaslat[adat[1]] = 1
                    db += 1

        javaslat_ = {k: v / db for k, v in javaslat.items()}'''

        return javaslat_

    def kategoriaBoltSzerint(self, termek, bolt) -> dict:
        """javaslat készítés a bolt alapján"""
        javaslat = {}
        db = 0
        for tranzakcio in self.tranzakciok:
            if tranzakcio[3] == bolt and tranzakcio[2] == termek and tranzakcio[1] in self.kategoriak:
                if javaslat.get(tranzakcio[1]):
                    javaslat[tranzakcio[1]] += 1
                    db += 1
                else:
                    javaslat[tranzakcio[1]] = 1
                    db += 1

        javaslat_ = {k:v/db for k, v in javaslat.items()}

        return javaslat_

    def javaslatok_osszefuzese(self, termek, bolt):
        javaslatok = {}

        javaslat = self.kategoriaFelhasznaloSzerint(termek)
        for k, v in javaslat.items():
            if javaslatok.get(k):
                javaslatok[k] += v*3
            else:
                javaslatok[k] = v*3

        javaslat = self.kategoriaMasFelhasznaloSzerint(termek)
        for k, v in javaslat.items():
            if javaslatok.get(k):
                javaslatok[k] += v*2
            else:
                javaslatok[k] = v*2

        javaslat = self.kategoriaStandardSzerint(termek)
        for k, v in javaslat.items():
            if javaslatok.get(k):
                javaslatok[k] += v*2
            else:
                javaslatok[k] = v*2

        javaslat = self.kategoriaBoltSzerint(termek, bolt)
        for k, v in javaslat.items():
            if javaslatok.get(k):
                javaslatok[k] += v
            else:
                javaslatok[k] = v

        rendezett_javaslatok = dict(sorted(javaslatok.items(), key=operator.itemgetter(1), reverse=True))
        javaslat = [k for k in rendezett_javaslatok.keys()]


        i = 0
        while len(javaslat) < 8:
            if self.kategoriak[i] not in javaslat:
                javaslat.append(self.kategoriak[i])
            else:
                i += 1

        return javaslat