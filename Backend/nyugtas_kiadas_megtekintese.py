import Adatbazis


class NyugtaMegtekintese:
    def __init__(self, tranzakcio_id=0):
        adatbazis_tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)

        felhasznalonev = input("Felhasznalonev: ")

        megfelelo_tranzakcio_adatok = list(filter(lambda x: x.felhasznalonev == felhasznalonev, adatbazis_tranzakcio_adatok))
        ids = set(adat.tranzakcio_id for adat in megfelelo_tranzakcio_adatok)
        megadott_id = int(input(f"Tranzakcio id {ids} : "))

        kivalasztott_tranzakcio_sorai = list(filter(lambda x: x.tranzakcio_id == megadott_id, megfelelo_tranzakcio_adatok))
        for sor in kivalasztott_tranzakcio_sorai:
            print(f"{sor.sajat_aru_nev.ljust(50, '.')} {sor.ertek}Ft {sor.tipus}")
        print(f"{kivalasztott_tranzakcio_sorai[0].datum.rjust(25, '.')}  ---  {kivalasztott_tranzakcio_sorai[0].bolt.ljust(25, '.')}")
