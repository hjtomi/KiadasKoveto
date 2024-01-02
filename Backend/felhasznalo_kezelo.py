from Adatbazis import Felhasznalo
import Adatbazis
from alap_kategoriak import alap_kategoriak
from Adatbazis import Tranzakcio
import json


def penz_tranzakciok(felhasznalo_nev, fiok_nev, muvelet, osszeg):
    felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

    penz_fiokok = ""
    penz_osszegek = ""
    for adatok in felhasznalo_adatok:
        if adatok.felhasznalonev == felhasznalo_nev:
            penz_fiokok = adatok.penz_fiokok
            penz_osszegek = adatok.osszegek

    szetszedett_penz_fiokok = penz_fiokok.split(";")
    penz_fiok_indexe = szetszedett_penz_fiokok.index(fiok_nev)

    szetszedett_penz_osszegek = penz_osszegek.split(";")

    if muvelet == "-":
        szetszedett_penz_osszegek[penz_fiok_indexe] = str(int(szetszedett_penz_osszegek[penz_fiok_indexe]) - int(osszeg))
    elif muvelet == "+":
        szetszedett_penz_osszegek[penz_fiok_indexe] = str(int(szetszedett_penz_osszegek[penz_fiok_indexe]) + int(osszeg))
    elif muvelet == "torles":
        szetszedett_penz_osszegek.pop(penz_fiok_indexe)
        szetszedett_penz_fiokok.pop(penz_fiok_indexe)
    elif muvelet == "penz_atiras":
        szetszedett_penz_osszegek[penz_fiok_indexe] = osszeg
    elif muvelet == "new":
        szetszedett_penz_fiokok.append(fiok_nev)
        penz_fiok_indexe = szetszedett_penz_fiokok.index(fiok_nev)
        szetszedett_penz_osszegek[penz_fiok_indexe] = osszeg

    veg_fiok = ""
    for fiok in szetszedett_penz_fiokok:
        veg_fiok = veg_fiok + ";" + fiok

    Adatbazis.adat_modositas(felhasznalo_nev, "penz_fiokok", veg_fiok[1:])

    veg_osszegek = ""
    for penz in szetszedett_penz_osszegek:
        veg_osszegek = veg_osszegek + ";" + penz

    Adatbazis.adat_modositas(felhasznalo_nev, "osszegek", veg_osszegek[1:])

class Regisztracio():
    """
    def __init__(self):
        self.felhasznalonev = None
        self.jelszo = None
        self.email = None
        self.kategoriak = None
        self.penz_fiokok = None
        self.osszegek = None

        self.regist()
    """
    def regist(self, felhasznalonev, jelszo, email, kategoriak, penz_fiokok, osszegek):

        kategoria = ""
        for x in alap_kategoriak:
            kategoria += f";{x}"
        kategoria = kategoria[1:]
        """
        felhasznalonev = input("Nev")
        jelszo = input("jelszo")
        email = input("email")
                osszegek = input("penz_mennyisegek")
        """
        penz_fiokok = "kézpénz"
        kategoriak = kategoria

        felhasznalo = Felhasznalo(
                        felhasznalonev=felhasznalonev,
                        jelszo=jelszo,
                        email=email,
                        kategoriak=kategoriak,
                        penz_fiokok=penz_fiokok,
                        osszegek=osszegek
                        )

        Adatbazis.adatok_hozzadasa([felhasznalo])

class Login():
    def __init__(self, felhasznalo_nev, password):

        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        self.megfelelo_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev and adatok.jelszo == password:
                self.megfelelo_adatok = adatok

    def vizsgalat(self):
        if self.megfelelo_adatok == "":
            return json.dumps({"helytelen":1})
        else:
            return json.dumps({"helytelen":0})

class Modositasok():
    def __init__(self, felhasznalo_nev, jelszo, email, penz_fiokok, osszeghez_penzfiok, osszegek):

        if jelszo:
            Adatbazis.adat_modositas(felhasznalo_nev, "jelszo", jelszo)

        if email:
            Adatbazis.adat_modositas(felhasznalo_nev, "email", email)

        if penz_fiokok:
            Adatbazis.adat_modositas(felhasznalo_nev, "penz_fiokok", penz_fiokok)

        if osszegek:
            penz_tranzakciok(felhasznalo_nev, osszeghez_penzfiok, "penz_atiras", osszegek)

class Adat_torles():
    def __init__(self, felhasznalo_nev, penz_fiok):
        """
        penz_fiok = input(f"törölni kivant penzfiok")
        """
        penz_tranzakciok(felhasznalo_nev, penz_fiok, "torles", 0)



class Kategoria_hozzaadas():
    def __init__(self, felhasznalo_nev, new_kategoria):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        kateg_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                kateg_adatok = adatok.kategoriak

        osszes_kategoria = kateg_adatok + ";" + new_kategoria

        if new_kategoria:
            Adatbazis.adat_modositas(felhasznalo_nev, "kategoriak", osszes_kategoria)

class penz_fiok_hozzaadas():
    def __init__(self, felhasznalo_nev, new_fiok, new_osszeg):
        penz_tranzakciok(felhasznalo_nev, new_fiok, "new", new_osszeg)


class Bevetel_hozzaadas():
    def __init__(self, felhasznalo_nev, penz_fiok_bevetel, osszeg):
        """
        penz_fiok_bevetel = input(f"bevetel melyik penz_fiokba")
        osszeg = input(f"osszeg")
        """
        penz_tranzakciok(felhasznalo_nev, penz_fiok_bevetel, "+", osszeg)

class Egyeb_kiadas():
    """
    def __init__(self, felhasznalo_nev):
        self.felhasznalo_nev = felhasznalo_nev
        self.tranzakcio_id = None,
        self.tipus = None,
        self.ertek = None,
        self.datum = None,
        self.bolti_aru_nev = None,
        self.sajat_aru_nev = None,
        self.bolt = None

        self.kiadas_felvetel()
    """
    def kiadas_felvetel(self, felhasznalo_nev, tipus, ertek, datum, sajat_aru_nev, bolt, penz_fiok):
        """
        self.tipus = input(f"kategoria")
        self.ertek = input(f"összeg")
        self.datum = input(f"datum")
        self.sajat_aru_nev = input(f"aru neve")
        self.bolt = input(f"bolt")
        """
        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        utolso_tranzakcio_id = 0
        for adatok in tranzakcio_adatok:
            utolso_tranzakcio_id = adatok.tranzakcio_id

        feltoltes = Tranzakcio(
                felhasznalonev=felhasznalo_nev,
                tranzakcio_id=utolso_tranzakcio_id+1,
                tipus=tipus,
                ertek=ertek,
                datum=datum,
                bolti_aru_nev=None,
                sajat_aru_nev=sajat_aru_nev,
                bolt=bolt
        )
        Adatbazis.adatok_hozzadasa([feltoltes])

        penz_tranzakciok(felhasznalo_nev, penz_fiok, "-", ertek)
