import random
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import NyugtasKiadas.nyugtas_kiadas as nyk
import Kategoria

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

db = SQLAlchemy()

db.init_app(app)


class Felhasznalo(db.Model):
    felhasznalonev = db.Column(db.String(25), primary_key=True, unique=True)
    jelszo = db.Column(db.String(64), unique=True, nullable=False)
    email = db.Column(db.String(30), unique=True, nullable=False)
    kategoriak = db.Column(db.String, unique=False, nullable=True)
    egyenleg = db.Column(db.Integer, unique=False, nullable=True)


class Tranzakcio(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    felhasznalonev = db.Column(db.String(25))
    tranzakcio_id = db.Column(db.Integer, nullable=False)
    tipus = db.Column(db.String(50), nullable=False)
    ertek = db.Column(db.Integer)
    datum = db.Column(db.String(25))
    bolti_aru_nev = db.Column(db.String(50))
    sajat_aru_nev = db.Column(db.String(50))
    bolt = db.Column(db.String(50))


with app.app_context():
    db.create_all()


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    felhasznalonev = "Proba1"
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

    felhasznalo_kategoriaiak = Felhasznalo.query.filter_by(felhasznalonev=felhasznalonev).first().kategoriak

    tranzakciok_db_reszlet = []
    tranzakciok = Tranzakcio.query.all()
    for tranzakcio in tranzakciok:
        tranzakciok_db_reszlet.append([])
        tranzakciok_db_reszlet[-1].append(tranzakcio.felhasznalonev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.tipus)
        tranzakciok_db_reszlet[-1].append(tranzakcio.bolti_aru_nev)
        tranzakciok_db_reszlet[-1].append(tranzakcio.sajat_aru_nev)

    kategoriak = Kategoria.KategoriaAjanlasok(
        termekek=nyugta_adatok["bolti_termek_nevek"],
        bolt=nyugta_adatok["bolt_nev"],
        felhasznalonev=felhasznalonev,
        kategoriak=felhasznalo_kategoriaiak,
        tranzakciok=tranzakciok_db_reszlet,
    )

    print(kategoriak.javaslatKeres())

    for (sajat_termek_ar, bolti_termek_nev, sajat_termek_nev) in zip(kesz_adatok['sajat_termek_arak'],
                                                                     kesz_adatok['bolti_termek_nevek'],
                                                                     kesz_adatok['sajat_termek_nevek']):
        uj_tranzakcio = Tranzakcio(
            id=tranzakciok[-1].id+1,
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
