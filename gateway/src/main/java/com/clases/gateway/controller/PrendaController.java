package com.clases.gateway.controller;

import com.clases.gateway.dto.*;
import com.clases.gateway.service.PrendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/Prenda")
@RequiredArgsConstructor
public class PrendaController {

    private final PrendaService PrendaService;

    @PostMapping(path = "/registrarPrenda")
    public ResponseDTO registrarPrenda(@RequestBody RegisrtarPrendaDTO dto) {
        return PrendaService.registrarPrenda(dto.getId(), dto.getMarca(), dto.getCategoria(), dto.getPaisFabricacion(), dto.getFechaFabricacion(), dto.getPropietario(), dto.getQr());
    }

    @GetMapping(path = "/cargarPrenda")
    public ResponseDTO cargarPrenda(@RequestParam String id) {
        return PrendaService.cargarPrenda(id);}

    @DeleteMapping(path = "/borrarPrenda")
    public ResponseDTO borrarPrenda(@RequestParam String id) {
        return PrendaService.borrarPrenda(id);}

    @PutMapping(path = "/transferenciaPrenda")
    public ResponseDTO transferAsset(@RequestBody TransferPrendaDTO dto) {
        return PrendaService.transferenciaPrenda(dto.getId(), dto.getNewOwner());}

    @GetMapping(path = "/listarPrendas")
    public ResponseDTO listarPrendas() {
        return PrendaService.listarPrendas();}
}
