package graphe;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class GrapheLAdj extends Graphe{

    private final Map<String, Map<String, Integer>> adj;

    public GrapheLAdj() {
        adj = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud))
            adj.put(noeud, new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientSommet(source)) {
            ajouterSommet(source);
        }
        if (!contientSommet(destination)) {
            ajouterSommet(destination);
        }
        if (contientArc(source,destination)) {
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        }
        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);
        adj.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        adj.remove(noeud);
        for (Map<String, Integer> arcs: adj.values()) {
            arcs.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source, destination)) {
            throw new IllegalArgumentException("Aucun arc entre " + source + " et " + destination);
        }

        if (contientSommet(source) && contientSommet(destination)) {
            adj.get(source).remove(destination);
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(adj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        Map<String, Integer> arcs = adj.get(sommet);
        if (arcs != null)
            succ.addAll(arcs.keySet());
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        Map<String, Integer> arcs = adj.get(src);
        if (arcs != null && arcs.containsKey(dest)) {
            return arcs.get(dest);
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return adj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        Map<String, Integer> arcs = adj.get(src);
        return arcs != null && arcs.containsKey(dest);
    }
}
