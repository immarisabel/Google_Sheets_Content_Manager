package nl.marisabel.GSContentManagement;


public class SheetModel {
    private String name = "test";
    private String greeting = "hi";

    private String quote = "quote";

    public SheetModel(String name, String greeting, String quote) {
        this.name = name;
        this.greeting = greeting;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public SheetModel() {
    }
}
