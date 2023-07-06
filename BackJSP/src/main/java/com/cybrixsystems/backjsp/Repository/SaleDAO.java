package com.cybrixsystems.backjsp.Repository;

import com.cybrixsystems.backjsp.Model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleDAO {
    List<Sale> findAllSales();

    List<Sale> findAllSalesByEmail(String email);

    Optional<Sale> findSaleById(Long idSale);
    Optional<Sale> findSaleByNum(int saleNum);
    Sale saveSale(Sale sale);
    public Sale registerSale(Sale sale);
    Sale updateSale(Sale sale);
    void deleteSaleById(Long idSale);
}
