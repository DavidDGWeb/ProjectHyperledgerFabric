package com.clases.gateway.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisrtarPrendaDTO {

    @ApiModelProperty(value = "Prenda ID", example = "1")
    String id;

    @ApiModelProperty(value = "Marca", example = "Gucci")
    String marca;

    @ApiModelProperty(value = "Categoria", example = "Bolso")
    String categoria;

    @ApiModelProperty(value = "Pais de fabricacion", example = "Italia")
    String paisFabricacion;

    @ApiModelProperty(value = "Fecha de fabricacion", example = "2024-01-19")
    String fechaFabricacion;

    @ApiModelProperty(value = "Propietario", example = "Pepe")
    String propietario;

    @ApiModelProperty(value = "Qr", example = "5w15s8w51599wa")
    String qr;

}
