package org.flats;

import shared.ConnectionFactory;
import shared.Flat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection();
            Scanner scanner = new Scanner(System.in)){
            FlatsDAO flatsDAO = new FlatImplementsDAO(connection);
            flatsDAO.createTable();

            while(true){
                System.out.println("1: add flat");
                System.out.println("2: view all flats");
                System.out.println("3: filter by square");
                System.out.println("4: filter by price");
                System.out.println("5: view total count");
                System.out.print("-> ");


//                String district, String street, Double square, Integer rooms, Double price
                String userQuery = scanner.next();
                switch (userQuery) {
                    case "1":
                        System.out.print("Enter flat district: ");
                        String district1 = scanner.nextLine(); // глючит сканер, поэтому их тут 2.
                        String district = scanner.nextLine();

                        System.out.print("Enter street: ");
                        String street = scanner.nextLine();

                        System.out.print("Enter square: ");
                        String sq = scanner.nextLine();
                        double square = Double.parseDouble(sq);

                        System.out.print("Enter room's quantity: ");
                        String r = scanner.nextLine();
                        int rooms = Integer.parseInt(r);

                        System.out.print("Enter price: ");
                        String pr = scanner.nextLine();
                        double price = Double.parseDouble(pr);


                        flatsDAO.addFlat(district, street, square, rooms, price);
                        break;
                    case "2":
                        List <Flat> list = flatsDAO.getAll();
                        for (Flat flat : list) {
                            System.out.println(flat);
                        }
                        break;
                    case "3":
                        System.out.println("Enter min square: ");
                        String userMinSquare = scanner.next();
                        System.out.println("Enter max square: ");
                        String userMaxSquare = scanner.next();
                        List <Flat> listBySquare = flatsDAO.getByFilter(Double.parseDouble(userMinSquare), Double.parseDouble(userMaxSquare));
                        for (Flat flat : listBySquare){
                            System.out.println(flat);
                        }
                        break;
                    case "4":
                        System.out.println("Enter room's quantity: ");
                        String userRooms = scanner.next();
                        System.out.println("Enter price: ");
                        String userPrice = scanner.next();
                        List <Flat> listByFilter = flatsDAO.getByFilter(Integer.parseInt(userRooms), Double.parseDouble(userPrice));
                        for (Flat flat : listByFilter){
                            System.out.println(flat);
                        }
                        break;
                    case "5":
                        System.out.println("Count: " + flatsDAO.count());
                        break;
                    default:
                        return;
                }
            }

        }


    }
}
