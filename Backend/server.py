from flask import Flask
import Adatbazis
import nyugtas_kiadas as nyk

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

Adatbazis.app_(app)
Adatbazis.db_creater(app)


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    nyers_nyugta_adatok = {
    'bolt_nev': "Spar",
    'datum': None,
    'bolti_termek_nevek': ['Fanta 0,5L', 'B00 db DORMI EPRES 30G 149 Ft/db', 'A00 CSIRKEMELLF. SZCS', 'A00 CSIRKEMELLE SZCS', 'CO0 GYE.ZABFAL.CS02244G', 'CO0 MILKA.MAZS.MOGY 100G', 'BO0 db PANTASTICO RETRO 489 Ft/db PUF', 'CO0 BEVASARLO TASKA', 'COO SZT.KIRALYI BABA 1L', 'COO AN BACON 2X200G', 'CO0 KOCKAZOTT BACON 500G', 'AOO AN SERTES-MARHA MIX', 'CO0 PF. KACSAZSIR 200G', 'CO0 THYMOS CHILT OROLT25', 'CO0 HR SZEGFUSZEG.10 G.', 'COO KEREKANYA KERESZTK', 'COO HOBBY-SZALAG, EZUST.', 'COO MC PROF400G SZARNY', 'db 479 Ft/db', 'COO MORANDO PROFESSIONAL', 'BO0 CHED.LAPKA SAJT 150G'],
    'bolti_termek_arak': [399.0, 298.0, 220.0, 436.0, 529.0, 549.0, 978.0, 299.0, 169.0, 899.0, 999.0, 385.0, 199.0, 499.0, 249.0, 339.0, 699.0, 479.0, 958.0, 479.0, 659.0]
}
    nyk.NyugtasKiadas(nyers_nyugta_adatok=nyers_nyugta_adatok)
    return "<p>Nyugtas kiadas felveve</p>"


if __name__ == '__main__':
    print(len("a4232c55ba718564b614564076384eaf0600b537bb1ef30b9b92f8d7f806804a"))
    app.run(debug=False)
