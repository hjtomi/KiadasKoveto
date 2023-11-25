import requests
from Adatbazis import Felhasznalo, adatok_hozzadasa


class Regisztacio():
    def __init__(self, felhasznalonev, jelszo, email, kategoriak, egyenleg):
        self.felhasznalonev = felhasznalonev
        self.jelszo = jelszo
        self.email = email
        self.kategoriak = kategoriak
        self.egyenleg = egyenleg


    def regist(self):
        self.felhasznalonev = input()
        self.jelszo = input()
        self.email = input()
        self.kategoriak = input()
        self.egyenleg = input()