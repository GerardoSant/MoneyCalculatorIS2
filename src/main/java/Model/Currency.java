package Model;

public class Currency {

    private String currencyName;
    private String currencySymbol;
    private String ID;

    public Currency(String currencyName, String currencySymbol, String ID) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyName='" + currencyName + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    public String getID() {
        return ID;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }
}
