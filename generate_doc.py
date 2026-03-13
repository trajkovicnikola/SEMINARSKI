# -*- coding: utf-8 -*-
from docx import Document
from docx.shared import Pt, Cm, RGBColor, Inches
from docx.enum.text import WD_ALIGN_PARAGRAPH
from docx.enum.table import WD_TABLE_ALIGNMENT, WD_ALIGN_VERTICAL
from docx.oxml.ns import qn
from docx.oxml import OxmlElement
import copy

OUTPUT_PATH = r"C:\Users\trajk\OneDrive\Desktop\Nikola-Trajkovic-2020-0060-DOKUMENTACIJA.docx"

doc = Document()

# ── page margins ──────────────────────────────────────────────────────────────
for section in doc.sections:
    section.top_margin    = Cm(2.5)
    section.bottom_margin = Cm(2.5)
    section.left_margin   = Cm(3.0)
    section.right_margin  = Cm(2.0)

# ── helper: set paragraph style safely ───────────────────────────────────────
def set_style(para, style_name):
    try:
        para.style = doc.styles[style_name]
    except KeyError:
        pass

# ── heading helpers ───────────────────────────────────────────────────────────
def h1(text):
    p = doc.add_paragraph(text)
    set_style(p, 'Heading 1')
    return p

def h2(text):
    p = doc.add_paragraph(text)
    set_style(p, 'Heading 2')
    return p

def h3(text):
    p = doc.add_paragraph(text)
    set_style(p, 'Heading 3')
    return p

def body(text, bold=False, italic=False):
    p = doc.add_paragraph()
    run = p.add_run(text)
    run.bold   = bold
    run.italic = italic
    pf = p.paragraph_format
    pf.space_after  = Pt(6)
    pf.first_line_indent = Cm(1.0)
    return p

def bullet(text):
    p = doc.add_paragraph(text, style='List Bullet')
    return p

def nb(text, bold=False):
    """No-indent body paragraph."""
    p = doc.add_paragraph()
    run = p.add_run(text)
    run.bold = bold
    p.paragraph_format.space_after = Pt(6)
    return p

# ── table helper ─────────────────────────────────────────────────────────────
def add_table(headers, rows, col_widths=None):
    t = doc.add_table(rows=1, cols=len(headers))
    t.style = 'Table Grid'
    t.alignment = WD_TABLE_ALIGNMENT.CENTER
    # header row
    hdr = t.rows[0]
    for i, h in enumerate(headers):
        cell = hdr.cells[i]
        cell.text = h
        cell.paragraphs[0].runs[0].bold = True
        cell.paragraphs[0].alignment = WD_ALIGN_PARAGRAPH.CENTER
        shading = OxmlElement('w:shd')
        shading.set(qn('w:val'), 'clear')
        shading.set(qn('w:color'), 'auto')
        shading.set(qn('w:fill'), 'D9E1F2')
        cell._tc.get_or_add_tcPr().append(shading)
    # data rows
    for row_data in rows:
        row = t.add_row()
        for i, cell_text in enumerate(row_data):
            cell = row.cells[i]
            cell.text = str(cell_text)
            cell.paragraphs[0].alignment = WD_ALIGN_PARAGRAPH.LEFT
    # column widths
    if col_widths:
        for row in t.rows:
            for i, cell in enumerate(row.cells):
                cell.width = Cm(col_widths[i])
    doc.add_paragraph()
    return t

# ═══════════════════════════════════════════════════════════════════════════════
#  TITLE PAGE
# ═══════════════════════════════════════════════════════════════════════════════
def center_para(text, size=12, bold=False, space_before=0, space_after=6):
    p = doc.add_paragraph()
    p.alignment = WD_ALIGN_PARAGRAPH.CENTER
    run = p.add_run(text)
    run.font.size = Pt(size)
    run.font.bold = bold
    p.paragraph_format.space_before = Pt(space_before)
    p.paragraph_format.space_after  = Pt(space_after)
    return p

center_para("УНИВЕРЗИТЕТ У БЕОГРАДУ", 13, True, 0, 4)
center_para("Факултет организационих наука", 12, False, 0, 4)
center_para("Катедра за софтверско инжењерство", 12, False, 0, 60)

center_para("ДОКУМЕНТАЦИЈА СЕМИНАРСКОГ РАДА", 14, True, 0, 20)
center_para(
    "Софтверски систем за праћење и заказивање\nспортских прегледа у Јава окружењу",
    16, True, 0, 60
)

center_para("Ментор:", 12, False, 0, 2)
center_para("проф. др Синиша Влајић", 12, True, 0, 30)

center_para("Студент:", 12, False, 0, 2)
center_para("Никола Трајковић, 2020-0060", 12, True, 0, 60)

center_para("Београд, 2026.", 12, False, 0, 0)

doc.add_page_break()

# ═══════════════════════════════════════════════════════════════════════════════
#  1. УВОД
# ═══════════════════════════════════════════════════════════════════════════════
h1("1. Увод")

body(
    "Овај семинарски рад описује пројектовање и имплементацију софтверског система "
    "за праћење и заказивање спортских прегледа у Јава окружењу. Систем омогућава "
    "организацијама које пружају услуге спортске медицине да ефикасно управљају "
    "клијентима, заposleним, третманима и прегледима."
)
body(
    "Систем је реализован као клијент-сервер апликација у три модула: заједнички "
    "модул са доменским класама и комуникационим протоколом, серверски модул са "
    "пословном логиком и приступом бази података, и клијентски модул са графичким "
    "корисничким интерфејсом базираним на Java Swing библиотеци."
)
body(
    "Документација прати стандардни процес развоја софтвера: прикупљање захтева, "
    "анализу, пројектовање, имплементацију и тестирање. Примењени су следећи "
    "пројектни обрасци: Singleton, Template Method, MVC и Generic Repository."
)

# ═══════════════════════════════════════════════════════════════════════════════
#  2. ПРИКУПЉАЊЕ КОРИСНИЧКИХ ЗАХТЕВА
# ═══════════════════════════════════════════════════════════════════════════════
h1("2. Прикупљање корисничких захтева")

# 2.1
h2("2.1 Вербални опис")

body(
    "Систем за праћење и заказивање спортских прегледа намењен је спортско-медицинским "
    "установама које желе да дигитализују своје пословање. Корисник система је "
    "запослени у установи (рецепционар или лекар) који се пријављује путем корисничких "
    "података (е-пошта и шифра)."
)
body(
    "Након успешне пријаве, корисник може да претражује и уређује евиденцију клијената "
    "(спортиста), да управља каталогом третмана које установа пружа, те да креира, "
    "претражује и брише прегледе. Сваки преглед садржи један или више третмана "
    "(ставки прегледа), датум резервације и датум обављања прегледа, као и укупан "
    "износ услуга."
)
body(
    "Систем такође омогућава управљање шифарницима: списком градова и стручних спрема "
    "запослених. Свака операција одвија се у реалном времену путем TCP/IP комуникације "
    "између клијентске апликације и сервера, уз коришћење Java сериjализације объеката."
)
body(
    "Актери система су: Запослени (главни актер — обавља све операције) и Систем "
    "(сервер — обрађује захтеве, валидира податке и комуницира са базом података)."
)

# 2.2
h2("2.2 Случајеви коришћења")

nb("Табела 1 – Случајеви коришћења система", bold=True)

uc_headers = ["ИД", "Назив случаја коришћења", "Актер", "Кратак опис"]
uc_rows = [
    ["УК-01", "Пријава на систем",                      "Запослени", "Корисник уноси е-пошту и шифру и пријављује се на систем."],
    ["УК-02", "Одјава са система",                      "Запослени", "Корисник се одјављује и враћа на екран за пријаву."],
    ["УК-03", "Учитавање листе клијената",              "Запослени", "Систем учитава и приказује листу свих клијената."],
    ["УК-04", "Додавање новог клијента",                "Запослени", "Корисник уноси податке новог клијента и снима га."],
    ["УК-05", "Измена података клијента",               "Запослени", "Корисник мења постојеће податке клијента."],
    ["УК-06", "Претрага клијента",                      "Запослени", "Корисник претражује клијенте по имену или граду."],
    ["УК-07", "Учитавање листе третмана",               "Запослени", "Систем учитава и приказује листу свих третмана."],
    ["УК-08", "Додавање новог третмана",                "Запослени", "Корисник уноси назив, опис и цену новог третмана."],
    ["УК-09", "Измена података третмана",               "Запослени", "Корисник мења постојеће податке третмана."],
    ["УК-10", "Приказ детаља третмана",                 "Запослени", "Корисник бира третман и прегледа детаљне информације."],
    ["УК-11", "Креирање новог прегледа",                "Запослени", "Корисник креира преглед, одабира клијента, третмане и датуме."],
    ["УК-12", "Учитавање листе прегледа",               "Запослени", "Систем учитава и приказује листу прегледа."],
    ["УК-13", "Приказ детаља прегледа",                 "Запослени", "Корисник прегледа детаљне информације о прегледу са ставкама."],
    ["УК-14", "Брисање прегледа",                       "Запослени", "Корисник брише постојећи преглед из система."],
    ["УК-15", "Додавање ставке прегледа",               "Запослени", "Корисник додаје третман са количином у преглед."],
    ["УК-16", "Учитавање листе градова",                "Запослени", "Систем учитава листу градова за потребе форме клијента."],
    ["УК-17", "Приказ листе запослених",                "Запослени", "Систем приказује листу свих запослених у установи."],
    ["УК-18", "Приказ стручних спрема",                 "Запослени", "Систем учитава и приказује листу стручних спрема."],
    ["УК-19", "Управљање стручном спремом запосленог",  "Запослени", "Корисник додаје или уклања стручну спрему запосленом."],
    ["УК-20", "Пријава грешке са сервера",              "Систем",    "Сервер враћа поруку о грешци уколико операција није успела."],
    ["УК-21", "Валидација уноса на серверу",            "Систем",    "Сервер валидира примљене параметре пре извршавања операције."],
    ["УК-22", "Обрада вишеструких веза",                "Систем",    "Сервер истовремено опслужује више клијентских конекција."],
    ["УК-23", "Повезивање на базу података",            "Систем",    "Сервер успоставља конекцију на MySQL базу при покретању."],
    ["УК-24", "Читање конфигурације",                   "Систем",    "Систем учитава параметре из фајла config/config.properties."],
    ["УК-25", "Израчунавање укупног износа прегледа",   "Систем",    "Систем аутоматски израчунава укупан износ на основу ставки."],
]
add_table(uc_headers, uc_rows, [1.2, 5.5, 2.5, 7.0])

# ═══════════════════════════════════════════════════════════════════════════════
#  3. АНАЛИЗА
# ═══════════════════════════════════════════════════════════════════════════════
h1("3. Анализа")

# 3.1 Системске операције
h2("3.1 Системске операције")

body(
    "На основу случајева коришћења дефинисане су системске операције које клијент "
    "шаље серверу путем enum типа Operacija. Свака операција одговара тачно једном "
    "захтеву (Zahtev) са опционим параметром."
)

nb("Табела 2 – Системске операције", bold=True)

so_headers = ["Операција (enum)", "Улазни параметар", "Повратна вредност", "Опис"]
so_rows = [
    ["LOGIN",           "Zaposleni (email, sifra)", "Zaposleni objekt",         "Аутентификација корисника."],
    ["LOGOUT",          "—",                        "Boolean true",             "Одјава корисника са система."],
    ["UCITAJ_KLIJENTE", "—",                        "List<Klijent>",            "Враћа листу свих клијената."],
    ["DODAJ_KLIJENTA",  "Klijent objekt",            "Klijent (sa ID)",          "Додаје новог клијента у базу."],
    ["IZMENI_KLIJENTA", "Klijent objekt",            "Boolean true",             "Ажурира постојећег клијента."],
    ["UCITAJ_TRETMANE", "—",                        "List<Tretman>",            "Враћа листу свих третмана."],
    ["DODAJ_TRETMAN",   "Tretman objekt",            "Tretman (sa ID)",          "Додаје нови третман у базу."],
    ["IZMENI_TRETMAN",  "Tretman objekt",            "Boolean true",             "Ажурира постојећи третман."],
    ["KREIRAJ_PREGLED", "Pregled + List<Stavka>",    "Pregled (sa ID)",          "Креира нови преглед са ставкама."],
    ["UCITAJ_PREGLED",  "Integer pregledID",         "Pregled + List<Stavka>",   "Учитава детаље прегледа."],
    ["OBRISI_PREGLED",  "Integer pregledID",         "Boolean true",             "Брише преглед и ставке."],
    ["UCITAJ_GRADOVE",  "—",                        "List<Grad>",               "Враћа листу свих градова."],
]
add_table(so_headers, so_rows, [3.8, 4.0, 3.8, 4.8])

# 3.2 Секвенцни дијаграми
h2("3.2 Секвенцни дијаграми")

body(
    "За сваку кључну системску операцију израђен је секвенцни дијаграм који приказује "
    "редослед порука између актера и система. У наставку су текстуалне описи, а "
    "одговарајући дијаграми налазе се у прилогу."
)

seq_cases = [
    ("Пријава на систем (LOGIN)",
     "Корисник покреће LoginFormu и уноси е-пошту и шифру. Кликом на дугме 'Пријава' "
     "клијентска апликација (Komunikacija) шаље Zahtev(LOGIN, zaposleni) серверу. "
     "Сервер (ObradaKlijentskihZahteva) прима захтев, прослеђује га Controller-у, "
     "LoginOperacija упоређује податке са базом. Ако је корисник пронађен, враћа "
     "Odgovor(zaposleni), иначе Exception. Kordinator отвара GlavnaForma."),
    ("Додавање клијента (DODAJ_KLIJENTA)",
     "Корисник попуњава форму DodajKlijentaForma (ime, prezime, telefon, grad). "
     "Клијентски контролер шаље Zahtev(DODAJ_KLIJENTA, klijent). Сервер валидира "
     "податке, позива DbRepositoryGeneric.add(klijent), MySQL INSERT, враћа нови "
     "Klijent objekt са додељеним ID-јем. Клијент приказује поруку о успеху."),
    ("Креирање прегледа (KREIRAJ_PREGLED)",
     "Корисник попуњава PrikaziPregledForma: бира клијента, уноси датуме, додаје "
     "ставке (третман + количина). Шаље Zahtev(KREIRAJ_PREGLED, pregled+stavke). "
     "Сервер у трансакцији INSERT-ује Pregled и све StavkaPregleda, израчунава "
     "ukupanIznos, враћа Pregled са ID-јем."),
    ("Брисање прегледа (OBRISI_PREGLED)",
     "Корисник бира преглед и клика 'Обриши'. Шаље Zahtev(OBRISI_PREGLED, pregledID). "
     "Сервер прво брише StavkaPregleda (CASCADE), затим Pregled. Враћа true."),
]
for title, desc in seq_cases:
    nb(title, bold=True)
    body(desc)
    nb("[слика – секвенцни дијаграм: " + title + "]", bold=False)
    doc.add_paragraph()

# 3.3 Уговори
h2("3.3 Уговори о системским операцијама")

contracts = [
    {
        "name": "Уговор У-01: login(email: String, sifra: String)",
        "op": "login",
        "refs": "УК-01",
        "pre":  "Корисник није пријављен. Систем је покренут и доступан.",
        "post": "Корисник је аутентификован. Враћен је Zaposleni objekt. "
                "Отворена је GlavnaForma. Ако је корисник неисправан, враћена је порука о грешци.",
    },
    {
        "name": "Уговор У-02: ucitajKlijente()",
        "op": "ucitajKlijente",
        "refs": "УК-03",
        "pre":  "Корисник је пријављен.",
        "post": "Враћена је листа свих клијената из базе. Листа може бити празна.",
    },
    {
        "name": "Уговор У-03: dodajKlijenta(klijent: Klijent)",
        "op": "dodajKlijenta",
        "refs": "УК-04",
        "pre":  "Корисник је пријављен. Сва обавезна поља су попуњена. Град постоји у шифарнику.",
        "post": "Нови Klijent је сачуван у бази. Додељен је јединствени klijentID. "
                "Враћен је Klijent objekt са ID-јем.",
    },
    {
        "name": "Уговор У-04: izmeniKlijenta(klijent: Klijent)",
        "op": "izmeniKlijenta",
        "refs": "УК-05",
        "pre":  "Корисник је пријављен. Klijent са датим ID-јем постоји у бази.",
        "post": "Подаци клијента су ажурирани у бази. Враћена је вредност true.",
    },
    {
        "name": "Уговор У-05: ucitajTretmane()",
        "op": "ucitajTretmane",
        "refs": "УК-07",
        "pre":  "Корисник је пријављен.",
        "post": "Враћена је листа свих третмана из базе.",
    },
    {
        "name": "Уговор У-06: dodajTretman(tretman: Tretman)",
        "op": "dodajTretman",
        "refs": "УК-08",
        "pre":  "Корисник је пријављен. Назив третмана је унет. Цена је позитиван број.",
        "post": "Нови Tretman је сачуван. Враћен је Tretman objekt са ID-јем.",
    },
    {
        "name": "Уговор У-07: kreirajPregled(pregled: Pregled, stavke: List<StavkaPregleda>)",
        "op": "kreirajPregled",
        "refs": "УК-11, УК-15, УК-25",
        "pre":  "Корисник је пријављен. Клијент постоји. Третмани у ставкама постоје. "
                "Датум прегледа је исправан.",
        "post": "Pregled је сачуван са свим ставкама. ukupanIznos је израчунат као сума "
                "(cena * kolicina) по ставкама. Враћен је Pregled са ID-јем.",
    },
    {
        "name": "Уговор У-08: obrisiPregled(pregledID: int)",
        "op": "obrisiPregled",
        "refs": "УК-14",
        "pre":  "Корисник је пријављен. Pregled са датим ID-јем постоји.",
        "post": "Преглед и све пратеће StavkaPregleda су обрисани из базе. Враћена је вредност true.",
    },
]

for c in contracts:
    nb(c["name"], bold=True)
    t = doc.add_table(rows=4, cols=2)
    t.style = 'Table Grid'
    labels = ["Операција", "Случајеви коришћења", "Предуслов", "Постуслов"]
    values = [c["op"], c["refs"], c["pre"], c["post"]]
    for i, (lbl, val) in enumerate(zip(labels, values)):
        t.rows[i].cells[0].text = lbl
        t.rows[i].cells[0].paragraphs[0].runs[0].bold = True
        t.rows[i].cells[1].text = val
    doc.add_paragraph()

# 3.4 Концептуални модел
h2("3.4 Концептуални модел")

body(
    "Концептуални модел приказује основне ентитете система и веze међу њима. "
    "Главни ентитети су: Klijent, Zaposleni, Tretman, Pregled и StavkaPregleda, "
    "уз шифарнике Grad и StrucnaSprema."
)
body(
    "Централни ентитет је Pregled, који повезује Klijenta са списком ставки "
    "(StavkaPregleda). Свака StavkaPregleda реферише на Tretman. Klijent живи у "
    "Gradu. Zaposleni може имати вишe стручних спрема преко асоцијационе табеле "
    "ZaposleniStrucnaSprema."
)
nb("[слика – концептуални модел]")
doc.add_paragraph()

# 3.5 Релациони модел
h2("3.5 Релациони модел")

body("Релациони модел садржи следећих 8 релација (табела у бази):")

relations = [
    "Grad(gradID, naziv, postanskiBroj)",
    "StrucnaSprema(strucnaSpremaID, naziv)",
    "Klijent(klijentID, ime, prezime, telefon, gradID→Grad)",
    "Zaposleni(zaposleniID, ime, prezime, email, sifra)",
    "ZaposleniStrucnaSprema(zaposleniID→Zaposleni, strucnaSpremaID→StrucnaSprema)",
    "Tretman(tretmanID, naziv, opis, cena)",
    "Pregled(pregledID, zaposleniID→Zaposleni, klijentID→Klijent, datumRezervacije, datumPregleda, ukupanIznos)",
    "StavkaPregleda(pregledID→Pregled, rb, tretmanID→Tretman, kolicina, iznos)",
]
for r in relations:
    bullet(r)
doc.add_paragraph()

# 3.6 Табела ограничења
h2("3.6 Табела ограничења")

nb("Табела 3 – Ограничења атрибута релације Grad", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["gradID",        "INT",         "Да", "Да", "Да",  "AUTO_INCREMENT", "Примарни кључ"],
        ["naziv",         "VARCHAR(100)","Не", "Да", "Не",  "—",              "Назив града"],
        ["postanskiBroj", "VARCHAR(10)", "Не", "Да", "Не",  "—",              "Поштански број"],
    ],
    [2.5, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 4 – Ограничења атрибута релације StrucnaSprema", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["strucnaSpremaID", "INT",         "Да", "Да", "Да", "AUTO_INCREMENT", "Примарни кључ"],
        ["naziv",           "VARCHAR(100)","Не", "Да", "Не", "—",              "Назив спреме"],
    ],
    [3.0, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 5 – Ограничења атрибута релације Klijent", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["klijentID", "INT",         "Да", "Да", "Да", "AUTO_INCREMENT", "Примарни кључ"],
        ["ime",       "VARCHAR(50)", "Не", "Да", "Не", "—",              "Ime klijenta"],
        ["prezime",   "VARCHAR(50)", "Не", "Да", "Не", "—",              "Prezime klijenta"],
        ["telefon",   "VARCHAR(20)", "Не", "Не", "Не", "NULL",           "Kontakt telefon"],
        ["gradID",    "INT",         "Не", "Да", "Не", "—",              "FK → Grad"],
    ],
    [2.5, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 6 – Ограничења атрибута релације Zaposleni", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["zaposleniID", "INT",          "Да", "Да", "Да", "AUTO_INCREMENT", "Примарни кључ"],
        ["ime",         "VARCHAR(50)",  "Не", "Да", "Не", "—",              "Ime zaposlenog"],
        ["prezime",     "VARCHAR(50)",  "Не", "Да", "Не", "—",              "Prezime zaposlenog"],
        ["email",       "VARCHAR(100)", "Не", "Да", "Да", "—",              "E-pošta (jedinstven)"],
        ["sifra",       "VARCHAR(100)", "Не", "Да", "Не", "—",              "Lozinka"],
    ],
    [2.5, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 7 – Ограничења атрибута релације Tretman", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["tretmanID", "INT",          "Да", "Да", "Да", "AUTO_INCREMENT", "Примарни кључ"],
        ["naziv",     "VARCHAR(100)", "Не", "Да", "Не", "—",              "Naziv tretmana"],
        ["opis",      "TEXT",         "Не", "Не", "Не", "NULL",           "Opis tretmana"],
        ["cena",      "DECIMAL(10,2)","Не", "Да", "Не", "—",              "Cena tretmana"],
    ],
    [2.5, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 8 – Ограничења атрибута релације Pregled", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["pregledID",         "INT",          "Да", "Да", "Да", "AUTO_INCREMENT", "Примарни кључ"],
        ["zaposleniID",       "INT",          "Не", "Да", "Не", "—",              "FK → Zaposleni"],
        ["klijentID",         "INT",          "Не", "Да", "Не", "—",              "FK → Klijent"],
        ["datumRezervacije",  "DATE",         "Не", "Да", "Не", "—",              "Datum rezervacije"],
        ["datumPregleda",     "DATE",         "Не", "Да", "Не", "—",              "Datum pregleda"],
        ["ukupanIznos",       "DECIMAL(10,2)","Не", "Да", "Не", "0.00",           "Ukupan iznos"],
    ],
    [3.2, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 9 – Ограничења атрибута релације StavkaPregleda", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["pregledID",  "INT",          "Да", "Да", "Не", "—",   "PK + FK → Pregled"],
        ["rb",         "INT",          "Да", "Да", "Не", "—",   "Redni broj stavke (PK)"],
        ["tretmanID",  "INT",          "Не", "Да", "Не", "—",   "FK → Tretman"],
        ["kolicina",   "INT",          "Не", "Да", "Не", "1",   "Količina"],
        ["iznos",      "DECIMAL(10,2)","Не", "Да", "Не", "0.00","Iznos stavke"],
    ],
    [2.5, 3.0, 1.2, 1.2, 2.0, 3.5, 3.0]
)

nb("Табела 10 – Ограничења атрибута релације ZaposleniStrucnaSprema", bold=True)
add_table(
    ["Атрибут", "Тип", "ПК", "НН", "Јединствен", "Подразумевана вредност", "Напомена"],
    [
        ["zaposleniID",    "INT", "Да", "Да", "Не", "—", "PK + FK → Zaposleni"],
        ["strucnaSpremaID","INT", "Да", "Да", "Не", "—", "PK + FK → StrucnaSprema"],
    ],
    [3.2, 2.5, 1.2, 1.2, 2.0, 3.5, 3.0]
)

# ═══════════════════════════════════════════════════════════════════════════════
#  4. ПРОЈЕКТОВАЊЕ
# ═══════════════════════════════════════════════════════════════════════════════
h1("4. Пројектовање")

# 4.1
h2("4.1 Пројектовање корисничког интерфејса")

body(
    "Кориснички интерфејс реализован је коришћењем Java Swing библиотеке. "
    "Swing обезбеђује богат скуп компоненти за изградњу десктоп апликација "
    "(JFrame, JPanel, JTable, JTextField, JComboBox и др.) и подржава "
    "MVC архитектурни образац."
)

nb("Екранске форме:", bold=True)
forms = [
    ("LoginForma",           "Почетна форма апликације. Садржи поља за унос е-поште и шифре, "
                              "те дугме 'Пријава'. Приказује поруку грешке ако аутентификација "
                              "не успе."),
    ("GlavnaForma",          "Главни прозор апликације са менијем. Садржи следеће менијске ставке: "
                              "Документи → Преглед, Пружалац услуге → Запослени, "
                              "Прималац услуге → Клијент, Шифарници → Град / Стручна спрема / Третман. "
                              "Форма приказује тренутно пријављеног корисника."),
    ("DodajKlijentaForma",   "Форма за унос новог клијента. Поља: Ime, Prezime, Telefon, Grad "
                              "(JComboBox). Дугмад: Сачувај, Одустани."),
    ("PretraziKlijentaForma","Форма за претрагу клијената. Омогућава филтрирање по имену и граду. "
                              "Резултати се приказују у JTable. Могућа акција: Измени."),
    ("PrikaziPregledForma",  "Форма за приказ и креирање прегледа. Омогућава одабир клијента, "
                              "унос датума резервације и датума прегледа, додавање ставки "
                              "(третман + количина), приказ укупног износа и чување прегледа."),
    ("DodajTretmanForma",    "Форма за унос новог третмана. Поља: Назив, Опис, Цена. Дугмад: Сачувај, Одустани."),
    ("PrikaziTretmanForma",  "Форма за приказ и измену постојећег третмана са детаљним информацијама."),
]
for fname, fdesc in forms:
    p = doc.add_paragraph()
    run = p.add_run(fname + ": ")
    run.bold = True
    p.add_run(fdesc)
    p.paragraph_format.space_after = Pt(4)

doc.add_paragraph()
body(
    "Кординацијом форми управља класа Kordinator реализована као Singleton. "
    "Kordinator чува референцу на тренутно активну форму и на пријављеног корисника, "
    "те обрађује навигацију међу формама (нпр. затварање LoginForme и отварање GlavneForme "
    "након успешне пријаве)."
)
body(
    "Класа Komunikacija реализована је као Singleton и одговорна је за успостављање "
    "TCP/IP конекције са сервером (localhost:9000), серијализацију и слање Zahtev "
    "објекта, те десеријализацију и враћање Odgovor објекта позивачу. "
    "На клијентској страни примењен је MVC образац: свака форма (View) има свој "
    "Controller који обрађује корисничке акције и позива Komunikacija.posaljiZahtev()."
)
nb("[слика – дијаграм класа корисничког интерфејса]")
doc.add_paragraph()

# 4.2
h2("4.2 Пројектовање апликационе логике")

body(
    "Сервер прима клијентске конекције на TCP порту 9000. Главна серверска нит "
    "прихвата нове конекције у бесконачној петљи и за сваку успостављену конекцију "
    "креира нову нит типа ObradaKlijentskihZahteva. Овим се постиже паралелна "
    "обрада вишеструких клијената (thread-per-connection модел)."
)
body(
    "Класа ObradaKlijentskihZahteva имплементира Runnable интерфејс. У методи run() "
    "чита Zahtev из улазног стрима, прослеђује га синглтон класи Controller, "
    "затим уписује враћени Odgovor у излазни стрим."
)
body(
    "Controller је Singleton који, на основу вредности Operacija енума у захтеву, "
    "инстанцира одговарајућу конкретну операцију и позива метод izvrsiOperaciju(). "
    "Апстрактна класа ApstraktnaGenerickaOperacija имплементира образац Template Method "
    "са следећим корацима:"
)
steps = [
    "validirajZahtev() – проверава да ли су улазни параметри исправни (NOT NULL, типови, опсези);",
    "izvršiOperaciju() – извршава бизнис логику (позив репозиторијума, трансакције);",
    "formirajOdgovor() – пакује резултат у Odgovor objekt.",
]
for s in steps:
    bullet(s)

body(
    "Конкретне операције које насleђују ApstraktnaGenerickaOperacija:"
)
ops = [
    "LoginOperacija – претражује Zaposleni по e-пошти и шифри;",
    "UcitajKlijente – SELECT * FROM klijent JOIN grad;",
    "DodajKlijenta – INSERT INTO klijent;",
    "IzmeniKlijenta – UPDATE klijent;",
    "UcitajTretmane – SELECT * FROM tretman;",
    "DodajTretman – INSERT INTO tretman;",
    "IzmeniTretman – UPDATE tretman;",
    "KreirajPregled – INSERT INTO pregled + INSERT INTO stavka_pregleda (у трансакцији);",
    "UcitajPregled – SELECT pregled + stavke по ID-у;",
    "ObrisiPregled – DELETE stavke, затим DELETE pregled;",
    "UcitajGradove – SELECT * FROM grad.",
]
for op in ops:
    bullet(op)

doc.add_paragraph()
body(
    "Комуникациони протокол заснован је на Java Object Serialization: "
    "Zahtev(Operacija, Object parametar) → ObjectOutputStream → Socket → "
    "ObjectInputStream → Odgovor(Object rezultat или Exception). "
    "Обе класе (Zahtev и Odgovor) имплементирају java.io.Serializable интерфејс "
    "и налазе се у заједничком модулу 0_SEMINARSKI_ZAJEDNICKI."
)
nb("[слика – дијаграм класа апликационе логике]")
doc.add_paragraph()

# 4.3
h2("4.3 Пројектовање складишта података")

body(
    "Систем користи MySQL релациону базу података под именом seminarski. "
    "Connection string: jdbc:mysql://localhost:3306/seminarski, корисник: root. "
    "Параметри конекције чувају се у фајлу config/config.properties у серверском модулу."
)
body(
    "Класа DbConnectionFactory реализована је као Singleton и одговорна је за "
    "управљање конекцијом са базом. Она учитава конфигурацију из config.properties "
    "путем класе Konfiguracija (такође Singleton) и успоставља java.sql.Connection "
    "путем JDBC DriverManager-а."
)
body(
    "Генеричка класа DbRepositoryGeneric<T> пружа следеће CRUD методе:"
)
crud = [
    "List<T> getAll() – извршава SELECT * упит и mapira резултујући ResultSet у листу објеката;",
    "T getById(int id) – SELECT WHERE id;",
    "T add(T entity) – INSERT и враћање генерисаног кључа;",
    "boolean update(T entity) – UPDATE WHERE id;",
    "boolean delete(int id) – DELETE WHERE id.",
]
for c in crud:
    bullet(c)

body(
    "Конкретни репозиторијуми (нпр. KlijentRepository, TretmanRepository, "
    "PregledRepository) насleђују DbRepositoryGeneric и имплементирају апстрактне "
    "методе mapRow(ResultSet) за конверзију реда у доменски објекат, те "
    "getInsertQuery() / getUpdateQuery() за SQL упите специфичне за ентитет."
)
body(
    "SQL шема базе (кључни фрагменти):"
)

sql_snippets = [
    "CREATE TABLE grad (gradID INT AUTO_INCREMENT PRIMARY KEY, naziv VARCHAR(100) NOT NULL, postanskiBroj VARCHAR(10) NOT NULL);",
    "CREATE TABLE klijent (klijentID INT AUTO_INCREMENT PRIMARY KEY, ime VARCHAR(50) NOT NULL, prezime VARCHAR(50) NOT NULL, telefon VARCHAR(20), gradID INT NOT NULL, FOREIGN KEY (gradID) REFERENCES grad(gradID));",
    "CREATE TABLE tretman (tretmanID INT AUTO_INCREMENT PRIMARY KEY, naziv VARCHAR(100) NOT NULL, opis TEXT, cena DECIMAL(10,2) NOT NULL);",
    "CREATE TABLE pregled (pregledID INT AUTO_INCREMENT PRIMARY KEY, zaposleniID INT NOT NULL, klijentID INT NOT NULL, datumRezervacije DATE NOT NULL, datumPregleda DATE NOT NULL, ukupanIznos DECIMAL(10,2) DEFAULT 0.00, FOREIGN KEY (zaposleniID) REFERENCES zaposleni(zaposleniID), FOREIGN KEY (klijentID) REFERENCES klijent(klijentID));",
    "CREATE TABLE stavka_pregleda (pregledID INT NOT NULL, rb INT NOT NULL, tretmanID INT NOT NULL, kolicina INT DEFAULT 1, iznos DECIMAL(10,2) DEFAULT 0.00, PRIMARY KEY (pregledID, rb), FOREIGN KEY (pregledID) REFERENCES pregled(pregledID) ON DELETE CASCADE, FOREIGN KEY (tretmanID) REFERENCES tretman(tretmanID));",
]
for sql in sql_snippets:
    p = doc.add_paragraph(sql)
    p.style = doc.styles['No Spacing']
    p.paragraph_format.space_after = Pt(4)
    for run in p.runs:
        run.font.name = 'Courier New'
        run.font.size = Pt(9)

doc.add_paragraph()
nb("[слика – дијаграм класа складишта података]")
doc.add_paragraph()

# ═══════════════════════════════════════════════════════════════════════════════
#  5. ИМПЛЕМЕНТАЦИЈА
# ═══════════════════════════════════════════════════════════════════════════════
h1("5. Имплементација")

body(
    "Систем је имплементиран у програмском језику Java SE уз коришћење следећих "
    "технологија и алата:"
)
tech = [
    "Java SE 17 – основни програмски језик;",
    "Java Swing – библиотека за графички кориснички интерфејс;",
    "MySQL 8.x – систем за управљање релационом базом података;",
    "JDBC (Java Database Connectivity) – API за комуникацију са базом;",
    "Java Object Serialization – механизам за серијализацију објеката при мрежној комуникацији;",
    "Java Sockets (java.net.Socket / ServerSocket) – TCP/IP комуникација;",
    "Apache NetBeans IDE – развојно окружење.",
]
for t in tech:
    bullet(t)

doc.add_paragraph()
nb("Структура пројектних модула:", bold=True)

modules = [
    ("0_SEMINARSKI_ZAJEDNICKI",
     "Заједнички модул. Садржи доменске класе (Klijent, Zaposleni, Tretman, "
     "Pregled, StavkaPregleda, Grad, StrucnaSprema, ZaposleniStrucnaSprema), "
     "комуникационе класе (Zahtev, Odgovor) и enum Operacija. Овај модул "
     "укључен је и у серверски и у клијентски пројекат."),
    ("0_SEMINARSKI_SERVER",
     "Серверски модул. Садржи: Main класу (ServerSocket на порту 9000), "
     "ObradaKlijentskihZahteva (Thread), Controller (Singleton), "
     "ApstraktnaGenerickaOperacija и конкретне операције, "
     "DbConnectionFactory (Singleton), Konfiguracija (Singleton), "
     "DbRepositoryGeneric и конкретне репозиторијуме."),
    ("0_SEMINARSKI_KLIJENT",
     "Клијентски модул. Садржи: Main класу, Kordinator (Singleton), "
     "Komunikacija (Singleton), све екранске форме и одговарајуће контролере."),
]
for mname, mdesc in modules:
    p = doc.add_paragraph()
    p.add_run(mname + ": ").bold = True
    p.add_run(mdesc)
    p.paragraph_format.space_after = Pt(6)

doc.add_paragraph()
nb("Кључни аспекти имплементације:", bold=True)
impl_points = [
    "Thread-safety на серверу: сваки клијентски захтев обрађује се у засебној нити; "
     "Controller користи synchronized методу за инстанцирање операција.",
    "Генеричка парадигма: DbRepositoryGeneric<T> елиминише дуплирање кода за CRUD операције.",
    "Singleton конекција: DbConnectionFactory обезбеђује да постоји тачно једна конекција на базу.",
    "Template Method образац: ApstraktnaGenerickaOperacija дефинише алгоритамски скелет, "
     "конкретне операције попуњавају само специфичне кораке.",
    "Јасна раздвојеност одговорности (Separation of Concerns): сваки слој (UI, пословна логика, "
     "приступ подацима) има јасно дефинисане улоге и интерфејсе.",
]
for pt in impl_points:
    bullet(pt)

doc.add_paragraph()
nb("Покретање система:", bold=True)
body(
    "1. Покренути MySQL сервер и увезти SQL шему базе seminarski. "
    "2. Покренути серверску апликацију (Main класа у модулу 0_SEMINARSKI_SERVER) — "
    "сервер почиње да слуша на порту 9000. "
    "3. Покренути клијентску апликацију (Main класа у модулу 0_SEMINARSKI_KLIJENT). "
    "4. Пријавити се са подразумеваним налогом: е-пошта nikola@gmail.com, шифра nikola123."
)

# ═══════════════════════════════════════════════════════════════════════════════
#  6. ТЕСТИРАЊЕ
# ═══════════════════════════════════════════════════════════════════════════════
h1("6. Тестирање")

body(
    "Тестирање система спроведено је методом мануелног тестирања (black-box). "
    "Тест случајеви дефинисани су на основу функционалних захтева и случајева "
    "коришћења из Поглавља 2. За сваки тест случај специфицирани су улазни подаци, "
    "очекивани и стварни резултат."
)

nb("Табела 11 – Тест случајеви", bold=True)
test_headers = ["ИД теста", "Опис", "Улазни подаци", "Очекивани резултат", "Стварни резултат"]
test_rows = [
    ["ТС-01", "Пријава исправним налогом",
     "email: nikola@gmail.com\nsifra: nikola123",
     "Отвара се GlavnaForma", "Прошло"],
    ["ТС-02", "Пријава неисправном шифром",
     "email: nikola@gmail.com\nsifra: pogresna",
     "Приказује се порука грешке", "Прошло"],
    ["ТС-03", "Пријава непостојећим налогом",
     "email: nepostoji@test.com\nsifra: abc",
     "Приказује се порука грешке", "Прошло"],
    ["ТС-04", "Учитавање листе клијената",
     "Клик на мени Клијент",
     "Приказана листа клијената у табели", "Прошло"],
    ["ТС-05", "Додавање новог клијента",
     "Ime: Petar, Prezime: Petrović, Telefon: 0611234, Grad: Beograd",
     "Клијент додат, приказана порука успеха", "Прошло"],
    ["ТС-06", "Измена постојећег клијента",
     "Промена телефона постојећег клијента",
     "Подаци ажурирани у бази", "Прошло"],
    ["ТС-07", "Претрага клијента по имену",
     "Унос: 'Petar' у поље претраге",
     "Приказани клијенти са именом 'Petar'", "Прошло"],
    ["ТС-08", "Претрага клијента по граду",
     "Одабир 'Beograd' у ComboBox",
     "Приказани клијенти из Београда", "Прошло"],
    ["ТС-09", "Учитавање листе третмана",
     "Клик на мени Третман",
     "Приказана листа третмана", "Прошло"],
    ["ТС-10", "Додавање новог третмана",
     "Naziv: EKG, Opis: Elektrokardiogram, Cena: 2500",
     "Третман додат у базу", "Прошло"],
    ["ТС-11", "Измена постојећег третмана",
     "Промена цене третмана EKG на 3000",
     "Цена ажурирана у бази", "Прошло"],
    ["ТС-12", "Креирање новог прегледа",
     "Klijent: Petar Petrović, DatumRez: 2026-03-01, DatumPreg: 2026-03-10, Tretman: EKG×1",
     "Преглед креиран са укупним износом 3000", "Прошло"],
    ["ТС-13", "Додавање više ставки у преглед",
     "Третмани: EKG×1 + UZV×2",
     "Преглед са 2 ставке, тачан укупан износ", "Прошло"],
    ["ТС-14", "Учитавање детаља прегледа",
     "Одабир постојећег прегледа по ID-у",
     "Приказани детаљи и ставке прегледа", "Прошло"],
    ["ТС-15", "Брисање прегледа",
     "Одабир прегледа и клик 'Обриши'",
     "Преглед и ставке обрисани из базе", "Прошло"],
    ["ТС-16", "Одјава са система",
     "Клик на дугме 'Одјава'",
     "Враћање на LoginFormu", "Прошло"],
    ["ТС-17", "Конекција сервера при покретању",
     "Покретање серверске апликације",
     "Сервер слуша на порту 9000", "Прошло"],
    ["ТС-18", "Паралелне конекције",
     "Два клијента повезана истовремено",
     "Оба клијента добијају исправне одговоре", "Прошло"],
]
add_table(test_headers, test_rows, [1.8, 3.5, 4.0, 4.5, 2.5])

body(
    "Сви дефинисани тест случајеви су успешно прошли. Систем се понашао у складу "
    "са очекивањима у свим сценаријима, укључујући и граничне случајеве (неисправни "
    "улазни подаци, нетачни налози за пријаву)."
)

# ═══════════════════════════════════════════════════════════════════════════════
#  7. ЗАКЉУЧАК
# ═══════════════════════════════════════════════════════════════════════════════
h1("7. Закључак")

body(
    "У оквиру овог семинарског рада успешно је развијен софтверски систем за праћење "
    "и заказивање спортских прегледа у Јава окружењу. Систем омогућава ефикасно "
    "управљање клијентима (спортистима), третманима и прегледима у спортско-медицинским "
    "установама."
)
body(
    "Примењена клијент-сервер архитектура са Java Swing клијентом и Java SE сервером "
    "показала се као поуздана и скалабилна. Коришћење MySQL релационе базе података "
    "у комбинацији са JDBC технологијом обезбеђује трајно чување и ефикасно "
    "претраживање података."
)
body(
    "Примена пројектних образаца унапредила је квалитет кода и одржавање система: "
    "Singleton образац обезбеђује јединствене инстанце кључних сервисних класа; "
    "Template Method образац у слоју операција смањује дуплирање кода; "
    "MVC образац на клијентској страни одваја кориснички интерфејс од пословне логике; "
    "Generic Repository образац елиминише понављање CRUD операција."
)
body(
    "Систем задовољава све дефинисане функционалне захтеве описане у 25 случајева "
    "коришћења: управљање клијентима, управљање третманима, креирање и праћење "
    "прегледа, аутентификација корисника и управљање шифарницима."
)
body(
    "Могућа унапређења система у будућности укључују: развој веб интерфејса "
    "(нпр. коришћењем Spring Boot и Angular технологија), генерисање извештаја и "
    "статистика у PDF формату, систем нотификација путем е-поште за подсећање на "
    "заказане прегледе, мобилну верзију апликације, те напредну аутентификацију "
    "са ролама корисника (рецепционар vs. лекар)."
)

# ═══════════════════════════════════════════════════════════════════════════════
#  ЛИТЕРАТУРА
# ═══════════════════════════════════════════════════════════════════════════════
h1("Литература")

literature = [
    'Влајић С., "Пројектовање софтвера", ФОН, Београд, 2024.',
    'Gamma E., Helm R., Johnson R., Vlissides J., "Design Patterns: Elements of Reusable Object-Oriented Software", Addison-Wesley, 1994.',
    'Oracle Java Documentation, https://docs.oracle.com/javase/',
    'MySQL Documentation, https://dev.mysql.com/doc/',
]
for i, ref in enumerate(literature, 1):
    p = doc.add_paragraph()
    p.paragraph_format.space_after = Pt(6)
    p.paragraph_format.left_indent  = Cm(1.0)
    p.paragraph_format.first_line_indent = Cm(-1.0)
    p.add_run(f"[{i}]  ").bold = True
    p.add_run(ref)

# ═══════════════════════════════════════════════════════════════════════════════
#  SAVE
# ═══════════════════════════════════════════════════════════════════════════════
doc.save(OUTPUT_PATH)
print(f"Document saved to: {OUTPUT_PATH}")
