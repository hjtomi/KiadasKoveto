
from flask_sqlalchemy import SQLAlchemy
db = SQLAlchemy()

def app_(app):
    db.init_app(app)

class Felhasznalo(db.Model):
    felhasznalonev = db.Column(db.String(25), primary_key=True, unique=True)
    jelszo = db.Column(db.String(64), unique=False, nullable=False)
    email = db.Column(db.String(30), unique=True, nullable=False)
    kategoriak = db.Column(db.String, unique=False, nullable=True)
    penz_fiokok = db.Column(db.String(100), unique=False, nullable=False)
    osszegek = db.Column(db.String(100), unique=False, nullable=True)

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

def db_creater(app):
    with app.app_context():
        db.create_all()

def adatok_hozzadasa(adatok:list):
    db.session.add_all(adatok)
    db.session.commit()

def adat_():
    return Felhasznalo

def adatok_lekerese(tranzakcio=True):
    if tranzakcio:
        tranzakciok = Tranzakcio.query.all()
        return tranzakciok
    else:
        felhasznalo = Felhasznalo.query.all()
        return felhasznalo

def adat_modositas(felhasznalo_nev, neve, adat):
    admin = Felhasznalo.query.filter_by(felhasznalonev={felhasznalo_nev}).first()
    if neve == "jelszo":
        admin.jelszo = adat
    if neve == "email":
        admin.email = adat
    if neve == "penz_fiokok":
        admin.penz_fiokok = adat
    if neve == "osszegek":
        admin.osszegek = adat
    db.session.commit()

def kategoria_hozzaadas(felhasznalo_nev, adat):
    admin = Felhasznalo.query.filter_by(felhasznalonev={felhasznalo_nev}).first()
    admin.kategoriak = adat
    db.session.commit()