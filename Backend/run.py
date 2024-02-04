from waitress import serve
from server import app


serve(app, host="157.181.201.13", port=52349)
