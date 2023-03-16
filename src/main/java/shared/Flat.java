package shared;

import shared.ID;

public class Flat {
    @ID
    private int id;
    private String district;
    private String street;
    private Double square;
    private Integer rooms;
    private Double price;

    public Flat(String district, String street, Double square, Integer rooms, Double price) {
        this.district = district;
        this.street = street;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public Flat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", square=" + square +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
