package API;

public class RootData {
    public String name;
    public Integer id;
    public String country;



    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public RootData(String name, Integer id, String country) {
        this.name = name;
        this.id = id;
        this.country = country;
    }


}
