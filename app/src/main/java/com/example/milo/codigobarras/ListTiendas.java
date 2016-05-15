package com.example.milo.codigobarras;

/**
 * Created by MILO on 15/05/2016.
 */
public class ListTiendas {
    public String UrlImage;
    public String NombreTienda;
    public String CodTienda;
    public ListTiendas()
    {
        super();
    }
    public ListTiendas(String UrlImage, String NombreTienda, String CodTienda) {
        super();
        this.UrlImage=UrlImage;
        this.NombreTienda=NombreTienda;
        this.CodTienda=CodTienda;
    }
}
