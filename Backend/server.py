from flask import Flask, request, jsonify
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
    print("Juhuuuu! Az Android app csatlakozott!")
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
        NyugtasKiadas().felvetel(debug=True)
    return "<p>Nyugtas kiadas felveve</p>"

@app.route("/nyugtas_kiadas_megtekintese")
def nyugta_megtekintese():
    NyugtaMegtekintese(tranzakcio_id=0)
    return "<p>Nyugtas megtekintheto</p>"

@app.route("/manualis_kiadas_felvetel")
def manualis_kiadas_felvetel():
    ManualisKiadasFelvetel()
    return "<p>Manualis kiadas felveve</p>"

@app.route("/proba",methods=['GET'])
def proba():
    d = {}
    d['Query'] = str(request.args['Query'])

    return jsonify(d)
    #return "siker"

@app.route('/frontend/nyugtas_kiadas_felvetel')
def frontend_nyugtas_kiadas_felvetel():
    debug = request.args.get('debug', 'False').lower() == "true"
    felhasznalonev = request.args.get('felhasznalonev', '')
    return jsonify(NyugtasKiadas().fronted_felvetel(felhasznalonev=felhasznalonev, debug=debug))

@app.route("/regisztralt-felhasznalok", methods=['GET'])
def regisztralt_felhasznalok():
    john = {
            "nev": "John Doe",
            "felhasznalonev": "jdoe32",
            "email": "jdoe@gmail.com"
        }
    john_json = json.dumps(john)
    jack = {
            "nev": "Jack Doe",
            "felhasznalonev": "doejack1996",
            "email": "doejack@gmail.com"
        }
    jack_json = json.dumps(jack)
    return [john_json, jack_json]

@app.route("/regisztralas", methods=['POST'])
def regisztralas():
    adatok = request.json
    nev =  adatok['nev']
    jelszo = adatok['jelszo']
    felhasznalo = User(nev, jelszo, "felhasznalo@gmail.com")
    return felhasznalo.to_json()

class User():
    def __init__(self, nev, jelszo, email_cim) :
        self.name = nev
        self.password = jelszo
        self.email = email_cim

    def to_json(self):
        return json.dumps({
            'name': self.name,
            'password': self.password,
            'email': self.email
        })

    @classmethod
    def from_json(cls, json_string):
        json_data = json.loads(json_string)
        return cls(json_data['name'], json_data['password'], json_data['email'])


if __name__ == '__main__':
    app.run(debug=False)