package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Config.DBConnection;
import com.cybrixsystems.backjsp.Model.Sale;
import com.cybrixsystems.backjsp.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaleDAOIMP implements SaleDAO{
    private Connection dbCon = DBConnection.getConnection();
    private UserDAO ud = new UserDAOIMP();

    @Override
    public List<Sale> findAllSales() {
        List<Sale> saleDB = new ArrayList<>();
        try{
            ResultSet rs = dbCon.prepareStatement("SELECT * FROM sale").executeQuery();
            while(rs.next()){
                saleDB.add(
                        new Sale(rs.getLong("id"),rs.getInt("saleNum")
                                ,rs.getInt("amount"),rs.getFloat("finalPrice")
                                ,ud.findUserById(rs.getLong("idUser")).orElseThrow()
                        ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return saleDB;
    }

    @Override
    public List<Sale> findAllSalesByEmail(String email) {
        List<Sale> saleDB = new ArrayList<>();
        try{
            PreparedStatement ps = dbCon.prepareStatement(
                    "SELECT sale.id,sale.saleNum,sale.amount,sale.finalPrice " +
                            "FROM sale INNER JOIN user ON sale.idUser = user.id WHERE user.email= ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                saleDB.add(
                        new Sale(rs.getLong("id"),rs.getInt("saleNum")
                                ,rs.getInt("amount"),rs.getFloat("finalPrice"))
                        );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return saleDB;
    }

    @Override
    public Optional<Sale> findSaleById(Long idSale) {
        Optional<Sale> saleDB = Optional.empty();

        try{
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM sale WHERE id=?");
            ps.setLong(1,idSale);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                saleDB = Optional.of(
                        new Sale(rs.getLong("id"),rs.getInt("saleNum")
                                ,rs.getInt("amount"),rs.getFloat("finalPrice"))
                        );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return saleDB;
    }

    @Override
    public Optional<Sale> findSaleByNum(int saleNum) {
        Optional<Sale> saleDB = Optional.empty();

        try{
            PreparedStatement ps = dbCon.prepareStatement("SELECT * FROM sale WHERE saleNum=?");
            ps.setInt(1,saleNum);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                saleDB = Optional.of(
                        new Sale(rs.getLong("id"),rs.getInt("saleNum")
                                ,rs.getInt("amount"),rs.getFloat("finalPrice"))
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return saleDB;
    }

    @Override
    public Sale saveSale(Sale sale) {
        sale.setSaleNum(this.generateSaleNum());

        try(PreparedStatement ps = dbCon.prepareStatement(
                "INSERT INTO sale (saleNum,amount,finalPrice,idUser) " +
                        "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1,sale.getSaleNum());
            ps.setFloat(2,sale.getAmount());
            ps.setFloat(3,sale.getFinalPrice());
            ps.setFloat(4,sale.getUser().getId());
            int affectedRows = ps.executeUpdate();

            if(affectedRows != 0){
                ResultSet generateKey = ps.getGeneratedKeys();
                if(generateKey.next()){
                    sale.setId(generateKey.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale;
    }

    public Sale registerSale(Sale sale){
        Sale saleSaved = null;
        Optional<User> user = ud.findUserByEmail(sale.getUser().getEmail());
        if(user.isPresent()){
            sale.setUser(user.orElseThrow());
            sale.setSaleNum(this.generateSaleNum());
            saleSaved =  this.saveSale(sale);
        }
        return saleSaved;
    }

    @Override
    public Sale updateSale(Sale sale) {
        return null;
    }

    @Override
    public void deleteSaleById(Long idSale) {
        try{
            PreparedStatement ps = dbCon.prepareStatement("DELETE FROM sale where id = ?");
            ps.setLong(1,idSale);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private int generateSaleNum(){
        int saleNum;
        do{
            saleNum = (int) (Math.random() * 999999999 + 1);
        }while(this.findSaleByNum(saleNum).isPresent());
        return saleNum;
    }
}
