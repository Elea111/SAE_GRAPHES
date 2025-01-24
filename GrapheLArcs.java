package graphe;

import java.util.ArrayList;
import java.util.List;
public class GrapheLArcs extends Graphe {
    private List<Arc> arcs;
    private List<String> lSommet;


    public GrapheLArcs() {
        this.arcs = new ArrayList<>();
        this.lSommet = new ArrayList<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!lSommet.contains(noeud))
            lSommet.add(noeud);
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientSommet(source))
            ajouterSommet(source);
        if (!contientSommet(destination))
            ajouterSommet(destination);

        if (contientArc(source, destination))
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);

        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        for (int i = arcs.size() - 1; i >= 0; i--) {
            Arc arc = arcs.get(i);
            if (arc.getSource().equals(noeud) || arc.getDestination().equals(noeud)) {
                arcs.remove(i);
            }
        }
        lSommet.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                arcs.remove(arc);
                return;
            }
        }
        throw new IllegalArgumentException("Aucun arc trouvé entre " + source + " et " + destination);
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet)) {
                successeurs.add(arc.getDestination());
            }
        }
        return successeurs;
    }

    @Override
    public List<String> getSommets() {
       return new ArrayList<>(lSommet);
    }

    public int getValuation(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return arc.getValuation();
            }
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return lSommet.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs)
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return true;
        return false;
    }
}
