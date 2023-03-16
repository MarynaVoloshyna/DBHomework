package org.flats;

import shared.Flat;

import java.util.List;

public interface FlatsDAO {
void createTable();
void addFlat(String district, String street, Double square, Integer rooms, Double price );
    List <Flat> getByFilter(Double min, Double max );
    List <Flat> getByFilter(Integer rooms, Double price);
    List <Flat> getAll();
    long count();

}
