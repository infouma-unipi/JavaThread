# JavaThread

Esercizio "vino" è parzialmente sbagliato:
1) non dovrebbe esserci if (posti > 0 && litri > 0), else 
    doveva essere: while ((posti > 0 && litri > 0) == false) { condizioni dell'else }, condizioni dell'if
2) l'oggetto botte non dovrebbe essere synchronized
3) i wait((tempo)) dovrebbero essere sleep e inseriti tutti in degustatore, non degustazione
