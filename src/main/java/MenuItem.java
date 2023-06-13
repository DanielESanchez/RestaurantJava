public class MenuItem {
    private String id;
    private String name;
    private float price;
    private String consumable;
    private String cuisineName;
    private String categoryName;
    private String description;

    public Menu(String id, String name, float price, String consumable, String cuisineName, String categoryName, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.consumable = consumable;
        this.cuisineName = cuisineName;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getConsumable() {
        return consumable;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }


}

