# Nyugtas kiadas felvetel

import requests
from Adatbazis import Tranzakcio, adatok_hozzadasa, adatok_lekerese, adat_, adat_modositas
import Kategoria
from alap_kategoriak import alap_kategoriak
import copy
import datetime
import base64
from PIL import Image
from io import BytesIO

"""
    Ezt a nyugta beolvasásakor, csak a felhasználót kell megadni hozzá
    kategoria = Kategoria.KategoriaAjanlas("Proba1")
    
    Ezt minden termékre
    Ilyet ad vissza: ['Ital', 'Étel', 'Öltözködés', 'Elektronika', 'Háztartás', 'Testápolás', 'Szórakozás', 'Albérlet']
    kategoria.uj_ajanlas("Spar", "C00 FANTA N. 0,5L")
"""

API_KEY = "d2692ea9750aa3db29e8489cdd9b97f0"
URL_INV = "https://api.mindee.net/v1/products/mindee/invoices/v4/predict"
URL_REC = "https://api.mindee.net/v1/products/mindee/expense_receipts/v5/predict"

debug_nyers_nyugta_adatok = {
        'bolt_nev': "Spar",
        'datum': "2023-12-18",
        'bolti_termek_nevek': ['Fanta 0,5L', 'B00 db DORMI EPRES 30G 149 Ft/db', 'A00 CSIRKEMELLF. SZCS',
                               'A00 CSIRKEMELLE SZCS', 'CO0 GYE.ZABFAL.CS02244G', 'CO0 MILKA.MAZS.MOGY 100G',
                               'BO0 db PANTASTICO RETRO 489 Ft/db PUF', 'CO0 BEVASARLO TASKA',
                               'COO SZT.KIRALYI BABA 1L', 'COO AN BACON 2X200G', 'CO0 KOCKAZOTT BACON 500G',
                               'AOO AN SERTES-MARHA MIX', 'CO0 PF. KACSAZSIR 200G', 'CO0 THYMOS CHILT OROLT25',
                               'CO0 HR SZEGFUSZEG.10 G.', 'COO KEREKANYA KERESZTK', 'COO HOBBY-SZALAG, EZUST.',
                               'COO MC PROF400G SZARNY', 'db 479 Ft/db', 'COO MORANDO PROFESSIONAL',
                               'BO0 CHED.LAPKA SAJT 150G'],
        'bolti_termek_arak': [399.0, 298.0, 220.0, 436.0, 529.0, 549.0, 978.0, 299.0, 169.0, 899.0, 999.0, 385.0, 199.0,
                              499.0, 249.0, 339.0, 699.0, 479.0, 958.0, 479.0, 659.0]
    }


class NyugtasKiadas:
    """Input: Vagy egy foto a nyugtarol vagy debug=True annak
    erdekeben hogy ne fogyjon a max api request"""

    @staticmethod
    def _nyugta_adatfeldolgozas(photo) -> dict:
        """Hasznalja a mindee API-t es a visszakapott adatokbol
        kiszuri a szamunkra fontosakat, ezeket hivjuk nyers_nyugta_adatok-nak"""

        files = {'document': photo}
        headers = {"Authorization": API_KEY}
        response = requests.post(
            url=URL_INV,
            files=files,
            headers=headers,
        )

        nyers_adatok: dict = response.json()
        print(nyers_adatok)

        bolti_termek_nevek = [
            sub['description'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
        ]

        bolti_termek_arak = [
            sub['total_amount'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
        ]

        bolt_nev = nyers_adatok['document']['inference']['prediction']['supplier_name']['value']

        datum = nyers_adatok['document']['inference']['prediction']['date']['value']

        return {
            "bolt_nev": bolt_nev,
            "datum": datum,
            "bolti_termek_nevek": bolti_termek_nevek,
            "bolti_termek_arak": bolti_termek_arak,
        }

    @staticmethod
    def _adatok_megadasa(nyugta_adatok: dict) -> dict:
        """A nyers_nyugta_adatokon vegig megy es mindegyiknel
        lehetoseget ad az ertekek modositasara (es a kategoria megadasara is fog majd)
        Ezeket hivjuk a kesz_adatok-nak"""

        """
            Ezt a nyugta beolvasásakor, csak a felhasználót kell megadni hozzá
            kategoria = Kategoria.KategoriaAjanlas("Proba1")

            Ezt minden termékre
            Ilyet ad vissza: ['Ital', 'Étel', 'Öltözködés', 'Elektronika', 'Háztartás', 'Testápolás', 'Szórakozás', 'Albérlet']
            kategoria.uj_ajanlas("Spar", "C00 FANTA N. 0,5L")

        """

        kategoria = Kategoria.KategoriaAjanlas("Jancsi")
        Felhasznalo = adat_()
        penz_fiokok = list(Felhasznalo.query.filter_by(felhasznalonev="Jancsi").first().penz_fiokok.split(';'))

        # Walrus operator azert hogy ilyen fancy egysoros legyen a bekerdezes es az alap adat megadas
        if uj_bolt_nev := input(f"Helytelen bolt nev eseten ird be a bolt nevet, kulonben hagy uresen ({nyugta_adatok['bolt_nev']}) "): nyugta_adatok['bolt_nev'] = uj_bolt_nev
        if uj_datum := input(f"Helytelen datum eseten ird be a datumot, kulonben hagy uresen ({nyugta_adatok['datum']}) "): nyugta_adatok['datum'] = uj_datum
        penz_fiok = input(f'Melyik penz fiokbol vonjuk le a penzt? {" ".join(penz_fiokok)} ')

        if not nyugta_adatok['bolt_nev']:
            nyugta_adatok['bolt_nev'] = 'xbolt'

        if not nyugta_adatok['datum']:
            nyugta_adatok['datum'] = datetime.date.today()

        if not penz_fiok:
            penz_fiok = 'keszpenz'


        sajat_termek_nevek = []
        sajat_termek_arak = []
        kategoriak = []
        for (bolti_termek_nev, bolti_termek_ar) in zip(nyugta_adatok['bolti_termek_nevek'], nyugta_adatok['bolti_termek_arak']):

            sajat_termek_nev = input(f"{bolti_termek_nev}: ")
            sajat_termek_ar = input(f"{bolti_termek_ar}: ")

            ajanlott_kategoriak = kategoria.uj_ajanlas(uj_bolt_nev, bolti_termek_nev)
            megadott_kategoria = input(f"{', '.join(ajanlott_kategoriak)}\n")
            if megadott_kategoria == "":
                kategoriak.append(ajanlott_kategoriak[0])
            else:
                kategoriak.append(megadott_kategoria)

            if sajat_termek_nev:
                sajat_termek_nevek.append(sajat_termek_nev)
            else:
                sajat_termek_nevek.append(bolti_termek_nev)

            if sajat_termek_ar:
                sajat_termek_arak.append(int(sajat_termek_ar))
            else:
                sajat_termek_arak.append(int(bolti_termek_ar))

        return {
            "felhasznalonev": "Jancsi",
            "bolt_nev": nyugta_adatok['bolt_nev'],
            "datum": nyugta_adatok['datum'],
            "penz_fiok": penz_fiok,
            "bolti_termek_nevek": nyugta_adatok['bolti_termek_nevek'],
            "sajat_termek_nevek": sajat_termek_nevek,
            "sajat_termek_arak": sajat_termek_arak,
            "osszeg": sum(sajat_termek_arak),
            "kategoriak": kategoriak,
        }

    @staticmethod
    def _uj_felhasznalo_osszegek_kalkulalasa(felhasznalonev, penz_fiok, osszeg) -> str:
        Felhasznalo = adat_()
        penz_fiokok = Felhasznalo.query.filter_by(felhasznalonev=felhasznalonev).first().penz_fiokok.split(';')
        osszegek = Felhasznalo.query.filter_by(felhasznalonev=felhasznalonev).first().osszegek.split(';')
        penz_fiok_lista_szama = penz_fiokok.index(penz_fiok)
        osszegek[penz_fiok_lista_szama] = str(int(osszegek[penz_fiok_lista_szama]) - osszeg)

        return ';'.join(osszegek)

    @staticmethod
    def _uj_adatbazis_elemek_keszitese(kesz_adatok):
        """Vegigmegy a kesz adatokon es elmenti oket
        Tranzakcio (adatbazis tabla elem) osztalykent egy listaba"""

        # Megnezzuk, hogy melyik a legnagyobb tranzakcio_id es ennek a vasarlasnak
        # a tranzakcio_id-je egyel nagyobb lesz (ha nincs tranzakcio akkor ez 0 lesz)
        tranzakcio_idk = list(map(lambda x: x.tranzakcio_id, adatok_lekerese()))
        if tranzakcio_idk:
            kovetkezo_id = max(tranzakcio_idk) + 1
        else:
            kovetkezo_id = 0

        uj_elemek = []
        for (sajat_termek_ar, bolti_termek_nev, sajat_termek_nev, kategoria) in zip(kesz_adatok['sajat_termek_arak'],
                                                                                    kesz_adatok['bolti_termek_nevek'],
                                                                                    kesz_adatok['sajat_termek_nevek'],
                                                                                    kesz_adatok['kategoriak']):
            uj_elemek.append(
                Tranzakcio(
                    felhasznalonev="Jancsi",
                    tranzakcio_id=kovetkezo_id,
                    tipus=kategoria,
                    ertek=sajat_termek_ar,
                    datum=kesz_adatok['datum'],
                    bolti_aru_nev=bolti_termek_nev,
                    sajat_aru_nev=sajat_termek_nev,
                    bolt=kesz_adatok['bolt_nev']
                )
            )

        return uj_elemek

    def felvetel(self, photo=None, debug=False):
        if debug:
            kesz_adatok = self._adatok_megadasa(debug_nyers_nyugta_adatok)
            osszegek = self._uj_felhasznalo_osszegek_kalkulalasa(kesz_adatok['felhasznalonev'], kesz_adatok['penz_fiok'], kesz_adatok['osszeg'])
            adat_modositas(kesz_adatok['felhasznalonev'], neve='osszegek', adat=osszegek)
            uj_elemek = self._uj_adatbazis_elemek_keszitese(kesz_adatok)

            adatok_hozzadasa(uj_elemek)

        else:

            nyers_nyugta_adatok = self._nyugta_adatfeldolgozas(photo)
            print(nyers_nyugta_adatok)
            kesz_adatok = self._adatok_megadasa(nyers_nyugta_adatok)
            osszegek = self._uj_felhasznalo_osszegek_kalkulalasa(kesz_adatok['felhasznalonev'],kesz_adatok['penz_fiok'], kesz_adatok['osszeg'])
            adat_modositas(kesz_adatok['felhasznalonev'], neve='osszegek', adat=osszegek)
            uj_elemek = self._uj_adatbazis_elemek_keszitese(kesz_adatok)

            adatok_hozzadasa(uj_elemek)

    @staticmethod
    def _frontend_nyugta_adatfeldolgozas(photo) -> dict:

        files = {'document': photo}
        headers = {"Authorization": API_KEY}
        response = requests.post(
            url=URL_INV,
            files=files,
            headers=headers,
        )

        nyers_adatok: dict = response.json()

        bolti_termek_nevek = [
            sub['description'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
        ]

        bolti_termek_arak = [
            sub['total_amount'] for sub in nyers_adatok['document']['inference']['prediction']['line_items']
        ]

        return {
            "bolti_aru_nevek": bolti_termek_nevek,
            "bolti_aru_ertekek": bolti_termek_arak,
        }

    def fronted_felvetel(self, photo=None, felhasznalonev='Jancsi', bolt='Spar', debug=False):
        """Argumentumkent vagy a fotot, a boltot es a felhasznalonevet adjuk meg
        vagy csak annyit hogy debug=True"""

        kategoria_kezelo = Kategoria.KategoriaAjanlas(felhasznalonev)

        if debug:
            with open('nyugta2.jpg', 'r') as file:
                photo = file

            adatok = self._nyugta_adatfeldolgozas(photo)

            adatok['kategoria_javaslatok'] = [
                kategoria_kezelo.uj_ajanlas(bolt, termek_nev) for (bolt, termek_nev) in zip(adatok['bolti_aru_nevek'],
                                                                                            adatok['bolti_aru_ertekek'])
            ]

            adatok['hiba'] = 0

            print(adatok)

            return adatok

        else:
            photo = base64.b64decode(photo)

            # megmutatja a kepet
            Image.open(BytesIO(photo)).show()

            adatok = self._nyugta_adatfeldolgozas(photo)

            adatok['kategoria_javaslatok'] = [
                kategoria_kezelo.uj_ajanlas(bolt, termek_nev) for (bolt, termek_nev) in zip(adatok['bolti_termek_nevek'],
                                                                                            adatok['bolti_termek_arak'])
            ]

            adatok['hiba'] = 0

            print(adatok)

            return adatok


if __name__ == '__main__':
    # print(NyugtasKiadas.felvetel())
    # file type: io.BufferReader
    pass
