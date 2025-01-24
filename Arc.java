package graphe;

public class Arc {
    private String source;
    private String dest;
    private int valuation;

    public Arc(String source, String dest, int valuation) {
        this.source = source;
        this.dest = dest;
        this.valuation = valuation;
    }

    public int getValuation() {
        return valuation;
    }

    public String getDest() {
        return dest;
    }

    public String getSource() {
        return source;
    }
}
