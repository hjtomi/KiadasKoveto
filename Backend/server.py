from flask import Flask
import Adatbazis
from nyugtas_kiadas import NyugtasKiadas
from nyugtas_kiadas_megtekintese import NyugtaMegtekintese
import Kategoria
from  registration import Login
from  registration import Regisztracio

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


@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    NyugtasKiadas(debug=True)
    return "<p>Nyugtas kiadas felveve</p>"


@app.route("/nyugtas_kiadas_megtekintese")
def nyugta_megtekintese():
    NyugtaMegtekintese(app=app, tranzakcio_id=0)
    return "<p>Nyugtas megtekintheto</p>"

if __name__ == '__main__':
    app.run(debug=False)
