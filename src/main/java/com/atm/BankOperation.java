package com.atm;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankOperation {



    public void debit_money(String card_number, String pin_code, double nowBalance, double debit_money) throws SQLException{
        String debMoney = "update atm_user set balance = ? \n" +
                "where card_number = ? and pin_code = ?";
        double newBalance = nowBalance - debit_money;
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(debMoney);
        preparedStatement.setDouble(1, newBalance);
        preparedStatement.setString(2, card_number);
        preparedStatement.setString(3, pin_code);
        preparedStatement.executeUpdate();

    }

    public void depositing_money(String card_number, String pin_code,double nowBalance, double deposit) throws SQLException {
        String depMoney = "update atm_user set balance = ? \n" +
                "where card_number = ? and pin_code = ?";
        double newBalance = nowBalance + deposit;
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(depMoney);
        preparedStatement.setDouble(1, newBalance);
        preparedStatement.setString(2, card_number);
        preparedStatement.setString(3, pin_code);
        preparedStatement.executeUpdate();
    }
    public void depositing_money(String card_number, double nowBalance, double deposit) throws SQLException {
        String depMoney = "update atm_user set balance = ? \n" +
                "where card_number = ?";
        double newBalance = nowBalance + deposit;
        PreparedStatement preparedStatement = DataBase.connection().prepareStatement(depMoney);
        preparedStatement.setDouble(1, newBalance);
        preparedStatement.setString(2, card_number);
        preparedStatement.executeUpdate();
    }

    public void transaction_money(String your_card_number, String your_pin_code, double yourBalance, String person_card_number,
                                  double person_Balance,double transaction_money) throws SQLException{
        debit_money(your_card_number, your_pin_code, yourBalance, transaction_money);
        depositing_money(person_card_number,person_Balance, transaction_money);

    }


}
