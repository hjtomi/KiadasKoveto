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

@app.route("/regisztracio", methods=['POST'])
def regisztracio():
    return Regisztracio()
"""
@app.route("/belepes", methods=['POST'])
def belepes():
    return Login()
"""
@app.route("/kateg_hozzaadas", methods=['POST'])
def kateg_hozzaadas():
    return Kategoria_hozzaadas()

@app.route("/felhasznalo_modositas", methods=['POST'])
def felhasznalo_modositas():
    return Modositasok()

@app.route("/bevetel_hozzaadas", methods=['POST'])
def bevetel_hozzaadas():
    return Bevetel_hozzaadas()

@app.route("/egyeb_kiadas_felvetel", methods=['POST'])
def egyeb_kiadas_felvetel():
    return Egyeb_kiadas()

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


def vizsgal_r(nev, email, jelszo, egyenleg):
    #1-helytelen adat 0-helyes adat
    return json.dumps({"nev":0, "email":0, "jelszo":1})


@app.route("/regisztral", methods=['POST'])
def regisztral():
    adatok = request.json
    nev = adatok['nev']
    email = adatok['email']
    jelszo = adatok['jelszo']
    egyenleg = adatok['egyenleg']
    print(nev, email, jelszo, egyenleg)
    return vizsgal_r(nev, email, jelszo, egyenleg)

@app.route("/bejelenzkez", methods=['POST'])
def bejelenzkez():
    adatok = request.json
    nev = adatok['nev']
    jelszo = adatok['jelszo']
    print(nev, jelszo)
    log = Login(nev, jelszo)

    return log.vizsgalat()


class User():
    def __init__(self, nev, jelszo, email_cim):
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