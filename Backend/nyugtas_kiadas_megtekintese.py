import Adatbazis


class NyugtaMegtekintese:
    def __init__(self, app, tranzakcio_id):
        adatbazis_tranzakcio_adatok = Adatbazis.adatok_lekerese(app=app, tranzakcio=True)
        megfelelo_tranzakcio_adatok = list(filter(lambda x: x.tranzakcio_id == tranzakcio_id, adatbazis_tranzakcio_adatok))
        for megfelelo_tranzakcio_adat in megfelelo_tranzakcio_adatok:
            print(megfelelo_tranzakcio_adat.sajat_aru_nev)

