from waitress import serve
from server import app


serve(app, host="172.16.8.109", port=52349)
