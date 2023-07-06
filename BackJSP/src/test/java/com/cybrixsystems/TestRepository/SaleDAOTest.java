package com.cybrixsystems.TestRepository;

import com.cybrixsystems.backjsp.Model.Sale;
import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.SaleDAO;
import com.cybrixsystems.backjsp.Repository.SaleDAOIMP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SaleDAOTest {
    private SaleDAO sd;

    @BeforeEach
    void setUp(){
        sd = new SaleDAOIMP();
    }

    @Test
    void findAllSales_test(){
        List<Sale> sales = sd.findAllSales();

        sales.forEach(System.out :: println);
        assertFalse(sales.isEmpty());
    }

    @Test
    void findAllSalesByUser_test(){
        List<Sale> sales = sd.findAllSalesByEmail("carlos@gmail.com");

        sales.forEach(System.out :: println);
        assertFalse(sales.isEmpty());
    }

    @Test
    void findSaleById_test(){
        Long idFind = 2L;

        Optional<Sale> saleOpt = sd.findSaleById(idFind);

        assertTrue(saleOpt.isPresent());
        assertEquals(saleOpt.orElseThrow().getId(),idFind);

        System.out.println(saleOpt.orElseThrow());
    }

    @Test
    void saveSale_test(){
        User user = new User();user.setId(3L);
        Sale saleToSave = new Sale(0L, (int) (Math.random() * 999999999 + 1),3,750,user);

        Sale saleSaved = sd.saveSale(saleToSave);

        assertNotNull(saleSaved);
        assertNotNull(saleSaved.getId());
        assertEquals(saleToSave.getSaleNum(),saleSaved.getSaleNum());
        assertEquals(saleToSave.getAmount(),saleSaved.getAmount());
        assertEquals(saleToSave.getFinalPrice(),saleSaved.getFinalPrice());
    }

    @Test
    void saveSaleGenNum_test(){
        User user = new User();user.setId(3L);
        Sale saleToSave = new Sale();
        saleToSave.setAmount(3);
        saleToSave.setFinalPrice(750f);
        saleToSave.setUser(user);

        Sale saleSaved = sd.saveSale(saleToSave);

        assertNotNull(saleSaved);
        assertNotNull(saleSaved.getId());
        assertEquals(saleToSave.getAmount(),saleSaved.getAmount());
        assertEquals(saleToSave.getFinalPrice(),saleSaved.getFinalPrice());
    }

    @Test
    void deleteSaleByID_test(){
        Long idDel = 4L;

        sd.deleteSaleById(idDel);

        Optional<Sale> saleOpt = sd.findSaleById(idDel);

        assertNotNull(saleOpt);
        assertTrue(saleOpt.isEmpty());
    }


}
