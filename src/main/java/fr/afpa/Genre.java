package fr.afpa;

public enum Genre {
    HOMME, FEMME, AUTRE;

    @Override
    public String toString() {
        switch (this) {
            case HOMME:
                return "Homme";
            case FEMME:
                return "Femme";
            default:
                return "Autre";
        }
    }
}
