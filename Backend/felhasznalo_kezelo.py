from Adatbazis import Felhasznalo, adatok_hozzadasa
import Adatbazis
from alap_kategoriak import alap_kategoriak
from  Adatbazis import Tranzakcio



debug_nyers_nyugta_adatok = {
        'felhasznalonev': "apu",
        'jelszo': "123",
        'email': "apu@gmail.com",
        'kategoriak': "Albérlet;Törlesztés",
        'penz_fiokok': "otp;erste;készpénz",
        'osszegek': "5000;80000;12"
    }

class Regisztracio():
    def __init__(self):
        self.felhasznalonev = None
        self.jelszo = None
        self.email = None
        self.kategoriak = None
        self.penz_fiokok = None
        self.osszegek = None

        self.regist()

    def regist(self):
        kategoria = ""
        for x in alap_kategoriak:
            kategoria += f";{x}"
        kategoria = kategoria[1:]

        self.felhasznalonev = input("Nev")
        self.jelszo = input("jelszo")
        self.email = input("email")
        self.kategoriak = kategoria
        self.penz_fiokok = input("penz_fiokok")
        self.osszegek = input("penz_mennyisegek")

        felhasznalo = Felhasznalo(
                        felhasznalonev=self.felhasznalonev,
                        jelszo=self.jelszo,
                        email=self.email,
                        kategoriak=self.kategoriak,
                        penz_fiokok=self.penz_fiokok,
                        osszegek=self.osszegek
                        )

        Adatbazis.adatok_hozzadasa([felhasznalo])

class Login():
    def __init__(self, felhasznalo_nev, password):

        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        megfelelo_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev and adatok.jelszo == password:
                megfelelo_adatok = adatok
        print(megfelelo_adatok.kategoriak)

class Modositasok():
    def __init__(self, felhasznalo_nev):

        jelszo = input(f"jelszo")
        if jelszo:
            Adatbazis.adat_modositas(felhasznalo_nev, "jelszo", jelszo)

        email = input(f"email")
        if email:
            Adatbazis.adat_modositas(felhasznalo_nev, "email", email)

        penz_fiokok = input(f"penz_fiokok")
        if penz_fiokok:
            Adatbazis.adat_modositas(felhasznalo_nev, "penz_fiokok", penz_fiokok)

        osszegek = input(f"osszegek")
        if osszegek:
            Adatbazis.adat_modositas(felhasznalo_nev, "osszegek", osszegek)



class Kategoria_hozzaadas():
    def __init__(self, felhasznalo_nev):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        kateg_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                kateg_adatok = adatok.kategoriak

        new_kategoriak = input(f"osszegek")
        osszes_kategoria = kateg_adatok + ";" + new_kategoriak

        if new_kategoriak:
            Adatbazis.kategoria_hozzaadas(felhasznalo_nev, osszes_kategoria)

class Bevetel_hozzaadas():
    def __init__(self, felhasznalo_nev):
        penz_fiok_bevetel = input(f"bevetel melyik penz_fiokba")
        osszeg = input(f"osszeg")

        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)
        penz_fiokok = ""
        penz_osszegek = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                penz_fiokok = adatok.penz_fiokok
                penz_osszegek = adatok.osszegek

        szetszedett_penz_fiokok = penz_fiokok.split(";")
        penz_fiok_indexe = szetszedett_penz_fiokok.index(penz_fiok_bevetel)

        szetszedett_penz_osszegek = penz_osszegek.split(";")
        szetszedett_penz_osszegek[penz_fiok_indexe] = str(int(szetszedett_penz_osszegek[penz_fiok_indexe]) + int(osszeg))

        veg_osszegek = ""
        for penz in szetszedett_penz_osszegek:
            veg_osszegek = veg_osszegek + ";" + penz

        Adatbazis.bevetel_hozzaadas(felhasznalo_nev, veg_osszegek[1:])

class Egyeb_kiadas():
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
    def kiadas_felvetel(self):
        self.tipus = input(f"kategoria")
        self.ertek = input(f"összeg")
        self.datum = input(f"datum")
        self.sajat_aru_nev = input(f"aru neve")
        self.bolt = input(f"bolt")

        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        utolso_tranzakcio_id = ""
        for adatok in tranzakcio_adatok:
            utolso_tranzakcio_id = adatok.tranzakcio_id

        feltoltes = Tranzakcio(
                felhasznalonev=self.felhasznalo_nev,
                tranzakcio_id=int(utolso_tranzakcio_id)+1,
                tipus=self.tipus,
                ertek=self.ertek,
                datum=self.datum,
                bolti_aru_nev=None,
                sajat_aru_nev=self.sajat_aru_nev,
                bolt=self.bolt
        )
        Adatbazis.adatok_hozzadasa([feltoltes])