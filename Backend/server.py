
from flask import Flask
import Adatbazis
import NyugtasKiadas.nyugtas_kiadas as nyk

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

Adatbazis.app_(app)
Adatbazis.db_creater(app)


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    print(bool(0))
    # with open('nyugta3.jpg', 'rb') as file:
    #     adatok = nyk.nyugta_adatfeldolgozas(file)
    nyugta_adatok = {
    'bolt_nev': "Spar",
    'datum': None,
    'bolti_termek_nevek': ['Fanta 0,5L', 'B00 db DORMI EPRES 30G 149 Ft/db', 'A00 CSIRKEMELLF. SZCS', 'A00 CSIRKEMELLE SZCS', 'CO0 GYE.ZABFAL.CS02244G', 'CO0 MILKA.MAZS.MOGY 100G', 'BO0 db PANTASTICO RETRO 489 Ft/db PUF', 'CO0 BEVASARLO TASKA', 'COO SZT.KIRALYI BABA 1L', 'COO AN BACON 2X200G', 'CO0 KOCKAZOTT BACON 500G', 'AOO AN SERTES-MARHA MIX', 'CO0 PF. KACSAZSIR 200G', 'CO0 THYMOS CHILT OROLT25', 'CO0 HR SZEGFUSZEG.10 G.', 'COO KEREKANYA KERESZTK', 'COO HOBBY-SZALAG, EZUST.', 'COO MC PROF400G SZARNY', 'db 479 Ft/db', 'COO MORANDO PROFESSIONAL', 'BO0 CHED.LAPKA SAJT 150G'],
    'bolti_termek_arak': [399.0, 298.0, 220.0, 436.0, 529.0, 549.0, 978.0, 299.0, 169.0, 899.0, 999.0, 385.0, 199.0, 499.0, 249.0, 339.0, 699.0, 479.0, 958.0, 479.0, 659.0]
}
    kesz_adatok = nyk.nyugtas_kiadas_felvetel(nyugta_adatok)

    for (sajat_termek_ar, bolti_termek_nev, sajat_termek_nev) in zip(kesz_adatok['sajat_termek_arak'],
                                                                     kesz_adatok['bolti_termek_nevek'],
                                                                     kesz_adatok['sajat_termek_nevek']):
        uj_tranzakcio = Tranzakcio(
            felhasznalonev="Proba",
            tranzakcio_id=0,
            tipus='nyugtas_kiadas',
            ertek=sajat_termek_ar,
            datum=kesz_adatok['datum'],
            bolti_aru_nev=bolti_termek_nev,
            sajat_aru_nev=sajat_termek_nev,
            bolt="Spar"
        )
        db.session.add(uj_tranzakcio)
    db.session.commit()
    return "<p>Nyugtas kiadas felveve</p>"


if __name__ == '__main__':
    print(len("a4232c55ba718564b614564076384eaf0600b537bb1ef30b9b92f8d7f806804a"))
    app.run(debug=False)
