public class Service {
    int id;
    String title;
    double cost;
    int durationInSeconds;
    String description;
    double discount;

    public Service(int id, String title, double cost, int durationInSeconds, String description, double discount) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.durationInSeconds = durationInSeconds;
        this.description = description;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", durationInSeconds=" + durationInSeconds +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                '}';
    }
}
