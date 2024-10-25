Semestralka o knihovně her.

Funguje jako aplikace s hrami, kde každý uživatel může si koupit hru, která má svou cenu a parametry,
uložit do své knohovny a později hrát.

Uživatel také má svoje atributy jako jmeno, přijmeni, login a heslo.

Knihovna bude mít počet her, uloženných v ní.

Aplikace bude fungovat jako klient-server, kde uživatel bude posílat dotazy na server, který bude vrácet
parametry her, nebo umožní nákup a bude ukladát hry do knihovny uživatele.

Příkladem business operaci v klientu může být dotaz jako "Ukáž všechny hry s moje knihovny" nebo "Přidej hru Witcher do knihovny", jako komplexní dotaz k M:N vazbě v serverové časti (kterou mám u vazby mezi knihovny a hrami) bude například "Zobrazi všechny hry, které má knihovna s id 123".

Jako komplexní dotaz ke všem třem entitám může sloužit doplněný dotaz M:N "Zobrazí hry v žanru action, které jsou v knihovně uživatele s loginem faizuram"