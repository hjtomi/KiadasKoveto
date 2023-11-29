# hasonlo mukodes a nyugtas_kiadas_felvetellel csak mindent mi magunk adunk meg
# bolt, datum, penz_fiok, termek_nev, ar, kategoria (javaslattal)
import datetime

from Adatbazis import adatok_lekerese, adat_, Tranzakcio, adatok_hozzadasa, adat_modositas
from Kategoria import KategoriaAjanlas
from datetime import date


class ManualisKiadasFelvetel:
    def __init__(self):
        bekert_adatok = self._adatok_bekerese()
        osszeg = bekert_adatok['osszeg']
        osszegek = self._uj_felhasznalo_osszegek_kalkulalasa('Jancsi', bekert_adatok['penz_fiok'], osszeg)
        uj_elemek = self._uj_adatbazis_elemek_keszitese(bekert_adatok)
        adatok_hozzadasa(uj_elemek)
        adat_modositas('Jancsi', neve='osszegek', adat=osszegek)

    @staticmethod
    def _adatok_bekerese():
        Felhasznalo = adat_()
        penz_fiokok = Felhasznalo.query.filter_by(felhasznalonev='Jancsi').first().penz_fiokok.split(';')

        kategoria_kezelo = KategoriaAjanlas('Jancsi')

        bolt = input('Bolt: ')
        if not bolt:
            bolt = 'xbolt'
        datum = input('Datum: ')
        if not datum:
            datum = date.today()
        penz_fiok = input(f'{" ".join(penz_fiokok)} : ')
        if not penz_fiok:
            penz_fiok = 'keszpenz'

        termek_nevek = []
        termek_arak = []
        kategoriak = []
        osszeg = 0
        while True:
            termek_nev = input('Termek nev: ')
            if not termek_nev:
                break
            termek_ar = int(input('Termek ar: '))
            osszeg += termek_ar

            ajanlott_kategoriak = kategoria_kezelo.uj_ajanlas(bolt, termek_nev)
            kategoria = input(f'{", ".join(ajanlott_kategoriak)}\n')
            if not kategoria:
                kategoria = ajanlott_kategoriak[0]

            termek_nevek.append(termek_nev)
            termek_arak.append(termek_ar)
            kategoriak.append(kategoria)

        return {
            'bolt': bolt,
            'datum': datum,
            'penz_fiok': penz_fiok,
            'felhasznalonev': 'Jancsi',
            'termek_nevek': termek_nevek,
            'termek_arak': termek_arak,
            'kategoriak': kategoriak,
            'osszeg': osszeg
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
    def _uj_adatbazis_elemek_keszitese(bekert_adatok):
        tranzakcio_idk = list(map(lambda x: x.tranzakcio_id, adatok_lekerese()))
        if tranzakcio_idk:
            kovetkezo_id = max(tranzakcio_idk) + 1
        else:
            kovetkezo_id = 0

        uj_elemek = []
        for (termek_nev, termek_ar, kategoria) in zip(bekert_adatok['termek_nevek'],
                                                      bekert_adatok['termek_arak'],
                                                      bekert_adatok['kategoriak']):
            uj_elemek.append(
                Tranzakcio(
                    felhasznalonev="Jancsi",
                    tranzakcio_id=kovetkezo_id,
                    tipus=kategoria,
                    ertek=termek_ar,
                    datum=bekert_adatok['datum'],
                    bolti_aru_nev='-',
                    sajat_aru_nev=termek_nev,
                    bolt=bekert_adatok['bolt']
                )
            )

        return uj_elemek
