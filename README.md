***Aufgabe:***

﻿Berechnung Versicherungssumme Hausratversicherung



Vorgaben:


2 Versicherungsprodukte mit unterschiedlichem Preis pro m²

* Kompakt: 650€ pro m²

* Optimal: 700€ pro m²


Eingabe:

* Produkt: "Kompakt" oder "Optimal"

* Wohnfläche in m²


Ausgabe:

* Versicherungssumme

------------------------------------------

**Erklärung meiner Lösung:**

Ich habe versucht das Problem so generisch wie möglich zu implementieren, damit man später (hoffentlich) leicht neue Produkte hinzufügen kann.

Ich habe die Annahme getroffen, dass jede Versicherungsart (also Hausratversicherung zum Beispiel) immer die gleichen Attribute (in dem Fall jetzt Preis pro Quadratmeter) hat und es keine Hausratversicherung gibt, die irgendwelche extra Attribute hat, die die anderen Versicherungen nicht haben. Das einzige was sich unterscheiden kann ist die Art und Weise der Berechnung des Preises.

Zunächst einmal lag der Fokus für mich darauf es möglichst einfach zu machen neue Produkte der selben Art (in dem konkreten Fall Haushaltversicherung) zu erstellen.

Dazu habe ich ein Data-Model `HouseholdInsurance` erstellt, welches von `Insurance` erbt. `HouseholdModel` enthält konkrete Informationen darüber, was es genau ausmacht eine "Hausratsversicherung" zu sein. In der Aufgabenstellung scheint dies der Preis pro Quadratmeter zu sein, weshalb `HouseholdInsurance` dieses zusätzliche Attribut hat.

Während der Implementierung ist mir aufgefallen, dass man mit Hilfe von Generics relativ einfach - so hoffe ich zu mindestens - auch komplett neue Produktarten, also zum Beispiel KFZ-Versicherung, implementieren könnte.

Da aber jede Versicherung für die Preiskalkulation andere Attribute benutzen könnte (selbst Hausratsversicherungen könnten andere Attribute benutzen), habe ich noch die Container Daten Klasse `InsuranceCalculationStrategyParameters` erstellt. 

Damit sollte es möglich sein, für jede Versicherung komplett eigene Parameter zu bestimmen, die zur Preiskalkulation genutzt werden und man kann trotzdem nur das eine Interface, nämlich `InsurancePriceCalculationStrategy`, implementieren.
Sollte der Fall eintreten, dass eine Hausratversicherung ein zusätzliches Attribut zur Berechnung benötigt, müsste man es in der Container-Klasse hinzufügen (`HouseholdInsuranceCalculationStrategyParameters`) und dann eine neue Berechnungsstrategie erstellen, die von `InsurancePriceCalculationStrategy` erbt und die Strategie dann in der Factory registrieren.

Um die passende Preisberechnungsstrategie auszuwählen, gibt es zusätzlich noch das `StrategyFactory` interface.

Außerdem habe ich noch ein in-memory repository erstellt.

In der Klasse `InsuranceCostCalculator` findet ihr ein Beispiel, wie konkret man dann meine Lösung verwenden würde.

Um mich nicht selbst um Währungen kümmern zu müssen, habe ich mich entschieden die `Joda-Money` Library zu benutzen.