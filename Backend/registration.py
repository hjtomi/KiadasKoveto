from Adatbazis import Felhasznalo, adatok_hozzadasa
import Adatbazis
from alap_kategoriak import alap_kategoriak

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
                        felhasznalonev = self.felhasznalonev,
                        jelszo = self.jelszo,
                        email = self.email,
                        kategoriak = self.kategoriak,
                        penz_fiokok = self.penz_fiokok,
                        osszegek = self.osszegek
                        )

        adatok_hozzadasa([felhasznalo])

class Login():
    def __init__(self, felhasznalo_nev, password):

        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)
        #megfelelo_adatok = list(
        #    filter(lambda x: x.felhasznalo_nev == felhasznalo_nev and x.password == password, felhasznalo_adatok))
        megfelelo_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev and adatok.jelszo == password:
                megfelelo_adatok = adatok
        print(megfelelo_adatok.kategoriak)
