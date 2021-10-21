# POO_L5
Laboratoire 4: Matrices binaires

Définir une classe permettant de représenter des matrices de taille quelconque (M × N) contenant des éléments
entre 0 et n - 1 pour un entier n (les entiers sont modulo n) qui réponde aux contraintes ci-dessous.

• Il soit possible de créer une matrice soit en générant son contenu aléatoirement (une fois sa taille et son
modulo connus), soit en passant ses valeurs en paramètre.

• Il soit possible d’afficher le contenu de la matrice.

• Il soit possible d’effectuer les opérations suivantes entre deux matrices: l’addition, la soustraction et le produit composante par composante. Toutes les opérations doivent être effectuées modulo n.

• Le résultat C d’une multiplication composante par composante entre une matrice A et une matrice B est
défini par Ci,j = Ai,j · Bi,j mod n.

• Si l’on effectue une opération entre une matrice A de taille M1 × N1 et une matrice B de taille M2 × N2 et
que les tailles ne correspondent pas, le résultat est une matrice de taille max(M1, M2) × max(N1, N2) où les
Ai,j et Bi,j manquants ont été remplacés par des 0.

• Si les modules n des deux matrices ne correspondent pas, lever une RuntimeException.

• En cas de toute autre erreur, lever une RuntimeException.

• Les méthodes Math.floorMod(), Math.max() et Math.random() seront sûrement utiles.
