from flask import Flask, request, jsonify
import Adatbazis
from nyugtas_kiadas import NyugtasKiadas
from nyugtas_kiadas_megtekintese import NyugtaMegtekintese
import Kategoria
from felhasznalo_kezelo import *
from manualis_kiadas_felvetel import ManualisKiadasFelvetel, ManualisKiadasFelvetelFrontend
import json
import struct
from typing import List
import datetime


app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

Adatbazis.app_(app)
Adatbazis.db_creater(app)

adatok = {}

print("A szerver elindult", datetime.datetime.now().strftime("%Y.%m.%d. %H:%M:%S"))

@app.route("/")
def hello_world():
    print("Juhuuuu! Az Android app csatlakozott!")
    return "<p>Udv a KiadasKoveto applikacioban</p>"

'''@app.route("/regisztracio", methods=['POST'])
def regisztracio():
    a =  Regisztracio()
    a.regist()'''
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

@app.route("/tranzakcio_modositas", methods=['POST'])
def tranzakcio_modositas():
    return Tranzakcio_mododitas()

@app.route("/bevetel_hozzaadas", methods=['POST'])
def bevetel_hozzaadas():
    return Bevetel_hozzaadas()

@app.route("/egyeb_kiadas_felvetel", methods=['POST'])
def egyeb_kiadas_felvetel():
    return Egyeb_kiadas()

@app.route("/nyugtas_kiadas_felvetel", methods=['GET', 'POST'])
def nyugtas_kiadas():
    with open('nyugta3.jpg', 'rb') as file:
        NyugtasKiadas().felvetel(debug=True)
    return "<p>Nyugtas kiadas felveve</p>"

@app.route("/nyugtas_kiadas_megtekintese", methods=['GET'])
def nyugta_megtekintese():
    NyugtaMegtekintese(tranzakcio_id=0)
    return "<p>Nyugtas megtekintheto</p>"

@app.route("/manualis_kiadas_felvetel", methods=['POST'])
def manualis_kiadas_felvetel():
    ManualisKiadasFelvetel()
    return "<p>Manualis kiadas felveve</p>"

@app.route("/statisztika", methods=['GET'])
def statisztika():
    s = Statisztika()
    s.statisztika("Jancsi", "2024.01.01", "2024.02.24", "kör")
    return "<p>ALMA</p>"

@app.route("/proba",methods=['GET'])
def proba():
    d = {}
    d['Query'] = str(request.args['Query'])
    print(d)
    nyugta = nyugtas_kiadas

    return jsonify(d)
    #return "siker"

@app.route('/frontend/nyugtas_kiadas_felvetel', methods=['GET'])
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

@app.route("/regisztral", methods=['POST'])
def regisztral():
    adatok = request.json
    nev = adatok['nev']
    email = adatok['email']
    jelszo = adatok['jelszo']
    egyenleg = adatok['egyenleg']
    reg = Regisztracio()
    return reg.regist(nev, jelszo, email, egyenleg)

@app.route("/bejelenzkez", methods=['POST'])
def bejelenzkez():
    adatok = request.json
    nev = adatok['nev']
    jelszo = adatok['jelszo']
    print(nev, jelszo)
    log = Login(nev, jelszo)

    return log.vizsgalat()

@app.route("/kategoriak", methods=['GET'])
def kategoriak():
    nev = str(request.args['nev'])
    #ToDo nev alapján kiszűrni a kategóriáit és egy listában visszaadni
    return json.dumps(alap_kategoriak)

@app.route("/kategoria_hozzaadasa", methods=['POST'])
def kategoria_hozzaadasa():
    adatok = request.json
    nev = adatok['nev']
    kategoria = adatok['kategoria']
    print(f"{nev} felhasználó {kategoria} kategóriát adott hozzá")
    # ToDo Hozzáadni a kategóriát a felhasználóhoz az adatbázisban
    return json.dumps({'hiba':0})

@app.route("/bevetel_hozzaadasa", methods=['POST'])
def bevetel_hozzaadasa():
    adatok = request.json
    nev = adatok['nev']
    fiok = adatok['fiok']
    osszeg = adatok['osszeg']
    print(f"{nev} felhasználó {fiok} fiókjához {osszeg} összeget adott hozzá")
    # ToDo Hozzáadni az adatbázishoz, Ha NEM létezik a FIÓK hiba:1-gyel visszatérni, egyébként hiba:0-val
    return json.dumps({'hiba':0})

@app.route("/fiok_hozzaadasa", methods=['POST'])
def fiok_hozzaadasa():
    adatok = request.json
    nev = adatok['nev']
    fiok = adatok['fiok']
    osszeg = adatok['osszeg']
    print(f"{nev} felhasználó {fiok} fiókot adott hozzá, összeg: {osszeg}")
    # ToDo A fiókot hozzáadni az adatbázishoz
    return json.dumps({'hiba':0})

@app.route("/manualis", methods=['POST'])
def manualis():
    """Adatszerkezet a frontendből:
        {
        tart:n - n = a termékek darabszáma (Int)
        datum:{ev:ev (Int), honap:nap (Int), nap:nap (Int)} (Json)
        bolt:bolt (String)
        1:{nev:termek neve (String), ar:termék ára (Int), kat: termék kategóriája (String)} (Json)
        2:{nev:termek neve (String), ar:termék ára (Int), kat: termék kategóriája (String)} (Json)
        .
        .
        .
        n:{nev:termek neve (String), ar:termék ára (Int), kat: termék kategóriája (String)} (Json)

        } (Json)

        pl: {'datum': {'nap': 5, 'honap': 2, 'ev': 2024}, 'bolt': 'Spar', 'tart': 3, '1': {'nev': 'Ktya', 'ar': '4', 'kat': 'Vga'}, '2': {'nev': 'Gshs', 'ar': '355', 'kat': 'Ggsh'}, '3': {'nev': 'Bsh', 'ar': '646', 'kat': 'Hyh'}}

    """
    adatok = request.json

    try:
        print(adatok)
        ManualisKiadasFelvetelFrontend(adatok)

    except Exception as e:
        print(e)
        return json.dumps({"hiba":"1"})

    return json.dumps({"hiba":"0"})

@app.route("/adatok_", methods=['GET'])
def adatok_():
    nev = str(request.args['nev'])
    print(nev)

    try:
        adat = []
        adat.append(json.dumps({"varj":"0"}))
        adat.append(json.dumps({"hossz":len(adatok[nev]["bolti_termek_nevek"])}))
        print(adatok)
        #for k, v in adatok[nev].items():
        #    adat.append(json.dumps({k:v}))

        for i in range(len(adatok[nev]["bolti_termek_nevek"])):
            adat.append(json.dumps({f"{i}ar":adatok[nev]["bolti_termek_arak"][i], f"{i}nev":adatok[nev]["bolti_termek_nevek"][i], f"{i}kat":adatok[nev]["kategoria_javaslatok"][i]}))

        print(adat)

        adatok.pop(nev)
        print(adatok)
    except:
        print("Várjon")
        return json.dumps([json.dumps({"varj":"1"}), json.dumps({"hiba":"0"})])

    return json.dumps(adat)

@app.route("/nyugta", methods=['POST'])
def nyugta():
    # Fogadd el a POST kérést
    image_data = request.form['kep']
    nev = request.form['nev']
    bolt = request.form['bolt']
    fiok = request.form['fiok']
    print(nev, bolt, fiok)

    # {
    #     'bolti_aru_nevek': List[str],
    #     'bolti_aru_ertekek': List[str],
    #     'kategoria_javaslatok': List[List[str*8]],
    #     'hiba': 0,
    # }

    adatok[nev] = NyugtasKiadas().fronted_felvetel(photo=image_data, felhasznalonev=nev, bolt=bolt, debug=False)

    # Itt végezd el azokat a műveleteket, amiket szeretnél az adattal
    # például elmenteni a képet vagy végrehajtani egy feldolgozást

    # Példa: Kiírjuk a kép hosszát
    image_length = len(image_data)
    print(f"Received an image with length: {image_length}")

    # Itt válaszolj a kérésre, például egy egyszerű üzenettel
    #return json.dumps(adatok)
    return json.dumps({"Sikeres kuldes":"1"})

class User:
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
