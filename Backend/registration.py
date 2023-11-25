import requests
from Adatbazis import Felhasznalo, adatok_hozzadasa


class Regisztacio():
    def __init__(self, felhasznalonev, jelszo, email, kategoriak, egyenleg):
        self.felhasznalonev = felhasznalonev
        self.jelszo = jelszo
        self.email = email
        self.kategoriak = kategoriak
        self.egyenleg = egyenleg

    @staticmethod
    def regist(self):
        felhasznalonev = input()