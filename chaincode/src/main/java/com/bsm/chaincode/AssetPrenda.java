package com.bsm.chaincode;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.List;
import java.util.Objects;

@DataType()
public final class AssetPrenda {

    @Property()
    private final String id;

    @Property()
    private final String marca;

    @Property()
    private final String categoria;

    @Property()
    private final String paisFabricacion;

    @Property()
    private final String fechaFabricacion;

    @Property()
    private final String propietario;

    @Property()
    private final String qr;

    @Property()
    private final List<String> propietarios;


    public AssetPrenda(@JsonProperty("id") final String id,
                      @JsonProperty("marca") final String marca,
                      @JsonProperty("categoria") final String categoria,
                      @JsonProperty("paisFabricacion") final String paisFabricacion,
                      @JsonProperty("fechaFabricacion") final String fechaFabricacion,
                      @JsonProperty("propietario") final String propietario,
                      @JsonProperty("qr") final String qr,
                      @JsonProperty("propietarios") final List<String> propietarios) {
        this.id = id;
        this.marca = marca;
        this.categoria = categoria;
        this.paisFabricacion = paisFabricacion;
        this.fechaFabricacion = fechaFabricacion;
        this.propietario = propietario;
        this.qr = qr;
        this.propietarios = propietarios;
    }

    public String getId() {
        return id;
    }

    public String getmarca() {
        return marca;
    }

    public String getcategoria() {
        return categoria;
    }

    public String getpaisFabricacion() {
        return paisFabricacion;
    }

    public String getfechaFabricacion() {
        return fechaFabricacion;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getqr() {
        return qr;
    }

    public List<String> getpropietarios() {
        return propietarios;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        AssetPrenda other = (AssetPrenda) obj;

        return Objects.deepEquals(
                new String[] {getId(), getmarca(), getcategoria(), getpaisFabricacion(), getfechaFabricacion(), getPropietario(), getqr(), getpropietarios().toString()},
                new String[] {other.getId(), other.getmarca(), other.getcategoria(), other.getpaisFabricacion(), other.getfechaFabricacion(), other.getPropietario(), other.getqr(), other.getpropietarios().toString()});
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getmarca(), getcategoria(), getpaisFabricacion(), getfechaFabricacion(), getPropietario(), getqr(), getpropietarios().toString());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + id + ", marca="
                + marca + ", categoria=" + categoria + ", paisFabricacion=" + paisFabricacion + ", fechaFabricacion=" + fechaFabricacion + ", propietario=" + propietario + ", qr=" + qr + ", propietarios=" + propietarios + "]";
    }
}
