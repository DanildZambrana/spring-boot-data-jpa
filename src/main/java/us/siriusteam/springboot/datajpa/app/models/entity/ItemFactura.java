package us.siriusteam.springboot.datajpa.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factura_items")
public class ItemFactura implements Serializable {
    private static final long serialVersionUID = -1114293339186862794L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "producto_id") //Con many to one no es necesario
    private Producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double calcularImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
    }
}

