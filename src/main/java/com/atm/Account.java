package com.atm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private String firstname;
    private String lastname;
    private String numberCard;
    private String pinCode;


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNumberCard(String numberCard) {this.numberCard = numberCard;}

    public void setPin_code(String pinCode) {this.pinCode = pinCode;}

    public String getNumberCard(){return numberCard;}

    public String getPinCode() {return pinCode;}

    public String getFirstname () throws SQLException{
        String getFirst_name = "select first_name from atm_user\n" +
                "where card_number = ? and pin_code = ?";
        String getFN = "";
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getFirst_name);
        preparedStatement.setString(1, numberCard);
        preparedStatement.setString(2, pinCode);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            getFN = result.getString("first_name");
        }
        return getFN;
    }

    public String getLastname() throws SQLException{
        String getLast_name = "select last_name from atm_user\n" +
                "where card_number = ? and pin_code = ?";
        String getPC = "";
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getLast_name);
        preparedStatement.setString(1, numberCard);
        preparedStatement.setString(2, pinCode);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            getPC = result.getString("last_name");
        }
        return getPC;
    }

    public String getNowNumberCard() throws SQLException{
        String getNumber = "select card_number from atm_user\n" +
                "where first_name = ? and last_name = ?";
        String nowNumberCard = "";
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getNumber);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            nowNumberCard = result.getString("card_number");
        }
        return nowNumberCard;
    }

    public String getNowPin_code() throws SQLException{
        String getPin = "select pin_code from atm_user\n" +
                "where first_name = ? and last_name = ?";
        String nowPin_code = "";
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getPin);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            nowPin_code = result.getString("pin_code");
        }
        return nowPin_code;
    }

    public double getBalance() throws SQLException{
        String getBalance = "select balance from atm_user\n" +
                "where card_number = ? and pin_code = ?";
        double getB = 0;
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getBalance);
        preparedStatement.setString(1, numberCard);
        preparedStatement.setString(2, pinCode);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            getB = result.getDouble("balance");
        }
        return getB;
    }

    public double getBalance(String number_Card) throws SQLException{
        String getBalance = "select balance from atm_user\n" +
                "where card_number = ?";
        double getB = 0;
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(getBalance);
        preparedStatement.setString(1, number_Card);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            getB = result.getDouble("balance");
        }
        return getB;
    }

    private String giveNumberCard() {
        String number_Card = "";
        for (int i = 0; i < 8; i++)
            number_Card += (int) (Math.random() * 10);
        return number_Card;
    }

    private String givePin_code() {
        String pin_code = "";
        for (int i = 0; i < 4; i++)
            pin_code += (int) (Math.random() * 10);
        return pin_code;
    }

    public void createUser () throws SQLException {
        String addUser = "insert into atm_user (first_name, last_name, card_number, pin_code, balance)\n" +
                "    values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(addUser);
        preparedStatement.setString(1, firstname);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, giveNumberCard());
        preparedStatement.setString(4, givePin_code());
        preparedStatement.setInt(5,0);
        preparedStatement.executeUpdate();
    }





}