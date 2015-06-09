package com.wordpress.bmadi.belajar.springjdbc.datasource.dao;

import com.wordpress.bmadi.belajar.springjdbc.datasource.model.Produk;
import java.util.List;

public interface ProdukDao {

    public void simpan(Produk p);

    public void hapus(Produk p);

    public List<Produk> getById(Integer id);

    public List<Produk> getByNama(String nama);

    public List<Produk> getAll();

}
