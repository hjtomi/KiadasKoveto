
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

def app_(app):
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

def db_creater(app):
    with app.app_context():
        db.create_all()


def adat_hozzadas(adatok:list):
    db.session.add_all(adatok)
    db.session.commit()
