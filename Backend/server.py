from flask import Flask
import Adatbazis
import nyugtas_kiadas as nyk

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///kiadaskoveto.db'

Adatbazis.app_(app)
Adatbazis.db_creater(app)


@app.route("/")
def hello_world():
    return "<p>Udv a KiadasKoveto applikacioban</p>"


@app.route("/nyugtas_kiadas_felvetel")
def nyugtas_kiadas():
    nyk.NyugtasKiadas(debug=True)
    return "<p>Nyugtas kiadas felveve</p>"


if __name__ == '__main__':
    app.run(debug=False)
