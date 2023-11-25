import requests
from Adatbazis import Felhasznalo, adatok_hozzadasa
import Adatbazis

"""
debug_nyers_nyugta_adatok = {
        'felhasznalonev': "apu",
        'jelszo': "123",
        'email': "apu@gmail.com"
        'kategoriak': "Albérlet; Törlesztés",
        'penz_fiokok': "otp; erste; készpénz",
        'osszegek': "5000; 80000; 12"
    }
"""
class Regisztracio():
    def __init__(self, felhasznalonev, jelszo, email, kategoriak, penz_fiokok, osszegek):
        self.felhasznalonev = felhasznalonev
        self.jelszo = jelszo
        self.email = email
        self.kategoriak = kategoriak
        self.penz_fiokok = penz_fiokok
        self.osszegek = osszegek


    def regist(self):
        self.felhasznalonev = input()
        self.jelszo = input()
        self.email = input()
        self.kategoriak = input()
        self.penz_fiokok = input()
        self.osszegek = input()

        #adatok_hozzadasa()
        return {
                "felhasznalonev": self.felhasznalonev,
                "jelszo": self.jelszo,
                "email": self.email,
                "kategoriak": self.kategoriak,
                "penz_fiokok": self.penz_fiokok,
                "osszegek ": self.osszegek,
        }

class Login():
    def __init__(self, app, felhasznalo_nev, password):
        #self.password = input()
        #felhasznalo_nev = input()

        felhasznalo_adatok = Adatbazis.adatok_lekerese(app=app, tranzakcio=False)
        megfelelo_adatok = list(
            filter(lambda x: x.felhasznalo_nev == felhasznalo_nev and x.password == password, felhasznalo_adatok))

        print(megfelelo_adatok)
