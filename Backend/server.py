from flask import Flask
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///felhasznalok.db'
app.config['SQLALCHEMY_BINDS'] = {
    'tranzakciok_db': 'sqlite:///tranzakciok.db'
}
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy()

db.init_app(app)


class Felhasznalo(db.Model):
    felhasznalonev = db.Column(db.String(25), primary_key=True, unique=True)
    jelszo = db.Column(db.String(64), unique=True, nullable=False)
    email = db.Column(db.String(30), unique=True, nullable=False)
    kategoriak = db.Column(db.String, unique=False, nullable=True)
    egyenleg = db.Column(db.Integer, unique=False, nullable=True)


class Tranzakcio(db.Model):
    felhasznalonev = db.Column(db.String(25), primary_key=True, unique=True)
    tranzakcio_id = db.Column(db.Integer, unique=False, nullable=False)
    tipus = db.Column(db.String(50), unique=False, nullable=False)
    ertek = db.Column(db.Integer)
    datum = db.Column(db.String(25))
    bolti_aru_nev = db.Column(db.String(50))
    sajat_aru_nev = db.Column(db.String(50))


with app.app_context():
    db.create_all()


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route("/osszegfelvetel")
def osszegf():
    return 52


if __name__ == '__main__':
    print(len("a4232c55ba718564b614564076384eaf0600b537bb1ef30b9b92f8d7f806804a"))
    app.run(debug=True)
