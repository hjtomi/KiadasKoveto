from flask import Flask
import Adatbazis
from nyugtas_kiadas import NyugtasKiadas
from nyugtas_kiadas_megtekintese import NyugtaMegtekintese
import Kategoria
from felhasznalo_kezelo import *
from manualis_kiadas_felvetel import ManualisKiadasFelvetel
import json

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

Adatbazis.app_(app)
Adatbazis.db_creater(app)


@app.route("/")
def hello_world():
    return "<p>Udv a KiadasKoveto applikacioban</p>"

@app.route("/regisztracio")
def regisztracio():
    Regisztracio()
    return "<p>Sikeres regisztracio</p>"

@app.route("/belepes")
def belepes():
    Login(felhasznalo_nev=input("Nev"), password=input("jelszo"))
    return "<p>Sikeres belepes</p>"

@app.route("/kateg_hozzaadas")
def kateg_hozzaadas():
    Kategoria_hozzaadas(felhasznalo_nev=input("Nev"))
    return "<p>Sikeres kategoria hozzaadas</p>"

@app.route("/felhasznalo_modositas")
def felhasznalo_modositas():
    Modositasok(felhasznalo_nev=input("Nev"))
    return "<p>Sikeres modositas</p>"

@app.route("/bevetel_hozzaadas")
def bevetel_hozzaadas():
    Bevetel_hozzaadas(felhasznalo_nev=input("Nev"))
    return "<p>Sikeres bevetel hozzaadas</p>"

@app.route("/egyeb_kiadas_felvetel")
def egyeb_kiadas_felvetel():
    Egyeb_kiadas(felhasznalo_nev=input("Nev"))
    return "<p>Sikeres egyeb kiadas felvétel</p>"

@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    with open('nyugta3.jpg', 'rb') as file:
        NyugtasKiadas(photo=file)
    return "<p>Nyugtas kiadas felveve</p>"

@app.route("/nyugtas_kiadas_megtekintese")
def nyugta_megtekintese():
    NyugtaMegtekintese(tranzakcio_id=0)
    return "<p>Nyugtas megtekintheto</p>"

@app.route("/manualis_kiadas_felvetel")
def manualis_kiadas_felvetel():
    ManualisKiadasFelvetel()
    return "<p>Manualis kiadas felveve</p>"

@app.route("/proba")
def proba():
    adat = {"datum":"2023-12-11", "osszeg":15000}
    return json.dumps(adat)

if __name__ == '__main__':
    app.run(debug=False)
