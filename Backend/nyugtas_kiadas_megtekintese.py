import Adatbazis


class NyugtaMegtekintese:
    def __init__(self, app, tranzakcio_id):
        tranzakcio_adatok = Adatbazis.adatok_lekerese(app=app, tranzakcio=True)
        print(tranzakcio_adatok)


if __name__ == '__main__':
    NyugtaMegtekintese(0)
