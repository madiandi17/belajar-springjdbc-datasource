DROP TABLE IF EXISTS produk;

CREATE TABLE produk (
  id int(11) NOT NULL AUTO_INCREMENT,
  kode varchar(15) NOT NULL,
  nama varchar(45) NOT NULL,
  harga decimal(19,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

        -- test simpan data
        Produk p = new Produk();
        p.setKode("P-005");
        p.setNama("Produk 005");
        p.setHarga(new BigDecimal(1000000.00));
        
        pd.simpan(p);

        -- test update data
        Produk p = new Produk();
        p.setKode("P-006");
        p.setNama("Produk 006");
        p.setHarga(new BigDecimal(1000000.00));
        p.setId(1);

        pd.simpan(p);

        -- test getById data
        List<Produk> produks = pd.getById(1);
        for (Produk p : produks) {
            System.out.println("id : " + p.getId());
            System.out.println("kode : " + p.getKode());
            System.out.println("nama : " + p.getNama());
            System.out.println("harga : " + p.getHarga());
        }

        -- test getByNama data
        List<Produk> produks = pd.getByNama("Produk 001");
        for (Produk p : produks) {
            System.out.println("id : " + p.getId());
            System.out.println("kode : " + p.getKode());
            System.out.println("nama : " + p.getNama());
            System.out.println("harga : " + p.getHarga());
        }

        -- test getAll data
        List<Produk> produks= pd.getAll();
        for(Produk p : produks){
            System.out.println("id : " + p.getId());
            System.out.println("kode : " + p.getKode());
            System.out.println("nama : " + p.getNama());
            System.out.println("harga : " + p.getHarga());
        }

        -- test delete data
        Produk p = new Produk();
        p.setId(1);
        pd.hapus(p);