package model.skala;

public class Skala {
    private int skalaId;
    private String skalaName;

    public Skala() {
    }

    public Skala(int skalaId, String skalaName) {
        this.skalaId = skalaId;
        this.skalaName = skalaName;
    }

    public int getSkalaId() {
        return skalaId;
    }

    public void setSkalaId(int skalaId) {
        this.skalaId = skalaId;
    }

    public String getSkalaName() {
        return skalaName;
    }

    public void setSkalaName(String skalaName) {
        this.skalaName = skalaName;
    }

    @Override
    public String toString() {
        return skalaName;
    }
}
