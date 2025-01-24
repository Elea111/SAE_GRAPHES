package graphe;

import java.util.*;

public class Dijkstra {
    public static void dijkstra(IGraphe g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        // Initialiser les distances et les précédents
        for (String noeud : g.getSommets()) {
            dist.put(noeud, Integer.MAX_VALUE);
            prev.put(noeud, null);  // Initialiser le précédent de chaque nœud à null
        }
        dist.put(source, 0);// La distance de la source à elle-même est 0

        // PriorityQueue pour sélectionner le nœud avec la distance minimale
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.add(source);

        while (!queue.isEmpty()) {
            String currentNoeud = queue.poll();
            int currentDistance = dist.get(currentNoeud);

            // Si la distance actuelle est infinie, sortir de la boucle
            if (currentDistance == Integer.MAX_VALUE) {
                break;
            }

            // Parcourir les successeurs du noeud courant
            for (String successeur : g.getSucc(currentNoeud)) {
                // Calculer la nouvelle distance vers le successeur
                int newDistance = currentDistance + g.getValuation(currentNoeud, successeur);

                // Si la nouvelle distance est inférieure à la distance connue
                if (newDistance < dist.get(successeur)) {
                    dist.put(successeur, newDistance);// Mettre à jour la distance minimale
                    prev.put(successeur, currentNoeud);// Mettre à jour le précédent

                    // Ajouter le successeur à la queue pour traitement futur
                    queue.add(successeur);
                }
            }
        }

        // Supprimer les nœuds avec une distance infinie
        dist.entrySet().removeIf(entry -> entry.getValue() == Integer.MAX_VALUE);
    }
}
