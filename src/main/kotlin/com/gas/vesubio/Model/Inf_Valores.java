package com.gas.vesubio.Model;

import javax.persistence.*;

@Entity
public class Inf_Valores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long val_id;
    @Column
    private String var_descripcion;

    public Long getVal_id() {
        return val_id;
    }

    public void setVal_id(Long val_id) {
        this.val_id = val_id;
    }

    public String getVar_descripcion() {
        return var_descripcion;
    }

    public void setVar_descripcion(String var_descripcion) {
        this.var_descripcion = var_descripcion;
    }
}
