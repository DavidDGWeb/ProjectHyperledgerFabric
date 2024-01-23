package com.clases.gateway.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransferPrendaDTO {


    @ApiModelProperty(value = "Prenda ID", example = "1")
    String id;

    @ApiModelProperty(value = "Nuevo owner", example = "Juan")
    String newOwner;

}
