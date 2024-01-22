from Adatbazis import Felhasznalo
import Adatbazis
from alap_kategoriak import alap_kategoriak
from Adatbazis import Tranzakcio
import json
from datetime import date


def penz_tranzakciok(felhasznalo_nev, fiok_nev, muvelet, osszeg):
    felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

    penz_fiokok = ""
    penz_osszegek = ""
    for adatok in felhasznalo_adatok:
        if adatok.felhasznalonev == felhasznalo_nev:
            penz_fiokok = adatok.penz_fiokok
            penz_osszegek = adatok.osszegek

    szetszedett_penz_fiokok = penz_fiokok.split(";")
    penz_fiok_indexe = szetszedett_penz_fiokok.index(fiok_nev)

    szetszedett_penz_osszegek = penz_osszegek.split(";")

    if muvelet == "-":
        szetszedett_penz_osszegek[penz_fiok_indexe] = str(int(szetszedett_penz_osszegek[penz_fiok_indexe]) - int(osszeg))
    elif muvelet == "+":
        szetszedett_penz_osszegek[penz_fiok_indexe] = str(int(szetszedett_penz_osszegek[penz_fiok_indexe]) + int(osszeg))
    elif muvelet == "torles":
        szetszedett_penz_osszegek.pop(penz_fiok_indexe)
        szetszedett_penz_fiokok.pop(penz_fiok_indexe)
    elif muvelet == "penz_atiras":
        szetszedett_penz_osszegek[penz_fiok_indexe] = osszeg
    elif muvelet == "new":
        szetszedett_penz_fiokok.append(fiok_nev)
        penz_fiok_indexe = szetszedett_penz_fiokok.index(fiok_nev)
        szetszedett_penz_osszegek[penz_fiok_indexe] = osszeg

    veg_fiok = ""
    for fiok in szetszedett_penz_fiokok:
        veg_fiok = veg_fiok + ";" + fiok

    Adatbazis.adat_modositas(felhasznalo_nev, "penz_fiokok", veg_fiok[1:])

    veg_osszegek = ""
    for penz in szetszedett_penz_osszegek:
        veg_osszegek = veg_osszegek + ";" + penz

    Adatbazis.adat_modositas(felhasznalo_nev, "osszegek", veg_osszegek[1:])

class Regisztracio():
    def regist(self, felhasznalonev, jelszo, email, osszegek):

        kategoria = ""
        for x in alap_kategoriak:
            kategoria += f";{x}"
        kategoria = kategoria[1:]

        penz_fiokok = "kézpénz"
        kategoriak = kategoria

        felhasznalo = Felhasznalo(
                        felhasznalonev=felhasznalonev,
                        jelszo=jelszo,
                        email=email,
                        kategoriak=kategoriak,
                        penz_fiokok=penz_fiokok,
                        osszegek=osszegek
                        )
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        felhaszn_megfelelo = 0
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalonev:
                felhaszn_megfelelo = 1

        email_megfelelo = 1
        for x in email:
            if x == "@":
                email_megfelelo = 0

        if email_megfelelo == 0 and felhaszn_megfelelo == 0:
            Adatbazis.adatok_hozzadasa([felhasznalo])
        return json.dumps({"nev":felhaszn_megfelelo, "email":email_megfelelo, "jelszo":0})

class Login():
    def __init__(self, felhasznalo_nev, password):

        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        self.megfelelo_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev and adatok.jelszo == password:
                self.megfelelo_adatok = adatok

    def vizsgalat(self):
        if self.megfelelo_adatok == "":
            return json.dumps({"helytelen": 1})
        else:
            return json.dumps({"helytelen": 0})

class Modositasok():
        def jelszo_mod(self, felhasznalo_nev, old_jelszo, new_jelszo):
            felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

            jelszo = ""
            for adatok in felhasznalo_adatok:
                if adatok.felhasznalonev == felhasznalo_nev and adatok.jelszo == old_jelszo:
                    jelszo = adatok.jelszo
            if jelszo == old_jelszo:
                Adatbazis.adat_modositas(felhasznalo_nev, "jelszo", new_jelszo)
                return json.dumps({"helytelen":0})
            else:
                return json.dumps({"helytelen":1})

        def email_mod(self, felhasznalo_nev, old_email, new_email):
            felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

            email = ""
            for adatok in felhasznalo_adatok:
                if adatok.felhasznalonev == felhasznalo_nev:
                    email = adatok.email
            if email == old_email:
                Adatbazis.adat_modositas(felhasznalo_nev, "email", new_email)
                return json.dumps({"helytelen": 0})
            else:
                return json.dumps({"helytelen": 1})


        def penz_fiokok_mod(self, felhasznalo_nev, old_penz_fiok, new_penz_fiok):
            felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

            penz_fiokok = ""
            for adatok in felhasznalo_adatok:
                if adatok.felhasznalonev == felhasznalo_nev:
                    penz_fiokok = adatok.penz_fiokok
            szetszedett_penz_fiokok = penz_fiokok.split(";")

            if old_penz_fiok in szetszedett_penz_fiokok:
                szetszedett_penz_fiokok[old_penz_fiok] = new_penz_fiok
                veg_fiok = ""
                for fiok in szetszedett_penz_fiokok:
                    veg_fiok = veg_fiok + ";" + fiok

                Adatbazis.adat_modositas(felhasznalo_nev, "penz_fiokok", veg_fiok[1:])
                return json.dumps({"helytelen": 0})
            else:
                return json.dumps({"helytelen": 1})

        def osszeg_mod(self, felhasznalo_nev, osszeghez_penzfiok, osszegek):
            felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

            penz_fiokok = ""
            for adatok in felhasznalo_adatok:
                if adatok.felhasznalonev == felhasznalo_nev:
                    penz_fiokok = adatok.penz_fiokok
            szetszedett_penz_fiokok = penz_fiokok.split(";")

            if osszeghez_penzfiok in szetszedett_penz_fiokok:
                penz_tranzakciok(felhasznalo_nev, osszeghez_penzfiok, "penz_atiras", osszegek)
                return json.dumps({"helytelen": 0})
            else:
                return json.dumps({"helytelen": 1})

class Tranzakcio_mododitas():
    def tipus_mod(self, felhasznalo_nev, id, old_tipus, new_tipus):
        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        tipus = ""
        for adatok in tranzakcio_adatok:
            if adatok.id == id:
                tipus = adatok.tipus
        kateg = []
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                kateg.append(adatok.kategoriak)

        if tipus == old_tipus and new_tipus in kateg:
            Adatbazis.tranz_modositas(id, "tipus", new_tipus)
            return json.dumps({"helytelen": 0})
        else:
            return json.dumps({"helytelen": 1})

    def datum(self, id, old_datum, new_datum):
        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)

        datum = ""
        for adatok in tranzakcio_adatok:
            if adatok.id == id:
                datum = adatok.datum
        #mai datum
        today = date.today()
        #helyes datum vizsgalas
        if datum == old_datum and today.strftime("%Y") > new_datum[:4]:
            Adatbazis.tranz_modositas(id, "datum", new_datum)
        elif datum == old_datum and today.strftime("%Y") == new_datum[:4]:
            if today.strftime("%m") > new_datum[5:7]:
                Adatbazis.tranz_modositas(id, "datum", new_datum)
            elif today.strftime("%m") == new_datum[5:7]:
                if today.strftime("%d") >= new_datum[8:10]:
                    Adatbazis.tranz_modositas(id, "datum", new_datum)
                    return json.dumps({"helytelen": 0})
                return json.dumps({"helytelen": 1})
            else:
                return json.dumps({"helytelen": 1})
        else:
            return json.dumps({"helytelen": 1})

    def ertek(self, felhasznalo_nev, penz_fiok, id, old_ertek, new_ertek,):
        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)

        ertek = ""
        for adatok in tranzakcio_adatok:
            if adatok.id == id:
                ertek = adatok.ertek

        #regi es uj penz kozotti kulonbseg-----penzfiokbol valo elvétele/hozzaadasa
        kulonbseg = new_ertek - old_ertek
        if kulonbseg > 0:
            penz_tranzakciok(felhasznalo_nev, penz_fiok, "-", kulonbseg)
        elif kulonbseg < 0:
            penz_tranzakciok(felhasznalo_nev, penz_fiok, "+", (kulonbseg * -1))

        if ertek == old_ertek and new_ertek >= 0 and new_ertek == int:
            Adatbazis.tranz_modositas(id, "ertek", new_ertek)
            return json.dumps({"helytelen": 0})
        else:
            return json.dumps({"helytelen": 1})


    def bolti_aru_nev_mod(self, id, new_aru_nev):
        Adatbazis.tranz_modositas(id, "bolti_aru_nev", new_aru_nev)
        return json.dumps({"helytelen": 0})

    def sajat_aru_nev_mod(self, id, new_sajat_aru_nev):
        Adatbazis.tranz_modositas(id, "sajat_aru_nev", new_sajat_aru_nev)
        return json.dumps({"helytelen": 0})

    def bolt_mod(self, id, new_bolt):
        Adatbazis.tranz_modositas(id, "bolt", new_bolt)
        return json.dumps({"helytelen": 0})


class Adat_torles():
    def adat_torles(self, felhasznalo_nev, penz_fiok):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        penz_fiokok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                penz_fiokok = adatok.penz_fiokok
        szetszedett_penz_fiokok = penz_fiokok.split(";")

        if penz_fiok in szetszedett_penz_fiokok:
            penz_tranzakciok(felhasznalo_nev, penz_fiok, "torles", 0)
            return json.dumps({"helytelen": 0})
        else:
            return json.dumps({"helytelen": 1})



class Kategoria_hozzaadas():
    def kateg_hozzaadas(self, felhasznalo_nev, new_kategoria):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        kateg_adatok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                kateg_adatok = adatok.kategoriak

        osszes_kategoria = kateg_adatok + ";" + new_kategoria

        if new_kategoria:
            Adatbazis.adat_modositas(felhasznalo_nev, "kategoriak", osszes_kategoria)

class Penz_fiok_hozzaadas():
    def penz_fiok_hozzaadas(self, felhasznalo_nev, new_fiok, new_osszeg):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        penz_fiokok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                penz_fiokok = adatok.penz_fiokok
        szetszedett_penz_fiokok = penz_fiokok.split(";")

        if new_fiok in szetszedett_penz_fiokok:
            return json.dumps({"helytelen": 1})
        else:
            penz_tranzakciok(felhasznalo_nev, new_fiok, "new", new_osszeg)
            return json.dumps({"helytelen": 0})


class Bevetel_hozzaadas():
    def bevetel_hozaadas(self, felhasznalo_nev, penz_fiok_bevetel, osszeg):
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        penz_fiokok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                penz_fiokok = adatok.penz_fiokok
        szetszedett_penz_fiokok = penz_fiokok.split(";")

        if penz_fiok_bevetel in szetszedett_penz_fiokok:
            penz_tranzakciok(felhasznalo_nev, penz_fiok_bevetel, "+", osszeg)
            return json.dumps({"helytelen": 0})
        else:
            return json.dumps({"helytelen": 1})

class Egyeb_kiadas():
    def kiadas_felvetel(self, felhasznalo_nev, tipus, ertek, datum, sajat_aru_nev, bolt, penzfiok):

        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        utolso_tranzakcio_id = 0
        for adatok in tranzakcio_adatok:
            utolso_tranzakcio_id = adatok.tranzakcio_id

        feltoltes = Tranzakcio(
                felhasznalonev=felhasznalo_nev,
                tranzakcio_id=utolso_tranzakcio_id+1,
                tipus=tipus,
                ertek=ertek,
                datum=datum,
                bolti_aru_nev=None,
                sajat_aru_nev=sajat_aru_nev,
                bolt=bolt,
                penz_fiok=penzfiok
        )
        Adatbazis.adatok_hozzadasa([feltoltes])

        penz_tranzakciok(felhasznalo_nev, penzfiok, "-", ertek)
        return json.dumps({"helytelen": 0})

def idoszamitas(intervallum, ido):
    today = date.today()
    ev = today.strftime("%Y")
    honap = today.strftime("%m")
    nap = today.strftime("%d")

    ido = ido.split(".")

    if intervallum == "ev":
        if int(ev) == int(ido[0]):
            return True
        else:
            return False
    elif intervallum == "honap":
        if int(ev) == int(ido[0]) and int(honap) == int(ido[1]):
            return True
        else:
            return False
    elif intervallum == "nap":
        if int(ev) == int(ido[0]) and int(honap) == int(ido[1]) and int(nap) == int(ido[2]):
            return True
        else:
            return False

class Statisztika():
    def statisztika(self, felhasznalo_nev, intervallum):
        tranzakcio_adatok = Adatbazis.adatok_lekerese(tranzakcio=True)
        felhasznalo_adatok = Adatbazis.adatok_lekerese(tranzakcio=False)

        penz_fiokok = ""
        for adatok in felhasznalo_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                penz_fiokok = adatok.penz_fiokok
        szetszedett_penz_fiokok = penz_fiokok.split(";")

        kiadasok = []
        for x in range(len(szetszedett_penz_fiokok)):
            kiadasok.append(0)

        for adatok in tranzakcio_adatok:
            if adatok.felhasznalonev == felhasznalo_nev:
                if idoszamitas(intervallum, str(adatok.datum)) == True:
                    kiadasok[szetszedett_penz_fiokok.index(adatok.penz_fiokok)] += adatok.ertek

