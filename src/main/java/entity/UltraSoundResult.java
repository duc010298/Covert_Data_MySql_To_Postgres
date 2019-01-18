package entity;

public class UltraSoundResult {
    private int id;
    private String name;
    private int orderNumber;
    private String content;

    public UltraSoundResult(int id, String name, int orderNumber, String content) {
        this.id = id;
        this.name = name;
        this.orderNumber = orderNumber;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
