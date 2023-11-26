import Adatbazis


class NyugtaMegtekintese:
    def __init__(self, tranzakcio_id):
        adatbazis_tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        megfelelo_tranzakcio_adatok = list(filter(lambda x: x.tranzakcio_id == tranzakcio_id, adatbazis_tranzakcio_adatok))

        if megfelelo_tranzakcio_adatok:
            print(megfelelo_tranzakcio_adatok[0].bolt)
            print(megfelelo_tranzakcio_adatok[0].datum)
            for megfelelo_tranzakcio_adat in megfelelo_tranzakcio_adatok:
                print(f"{megfelelo_tranzakcio_adat.sajat_aru_nev} --- {megfelelo_tranzakcio_adat.ertek}Ft --- {megfelelo_tranzakcio_adat.tipus}")
        else:
            print("Nincs adat")

