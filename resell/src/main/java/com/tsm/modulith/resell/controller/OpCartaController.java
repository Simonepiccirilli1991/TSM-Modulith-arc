package com.tsm.modulith.resell.controller;

import com.tsm.modulith.resell.onepiece.dto.BaseResponse;
import com.tsm.modulith.resell.onepiece.dto.request.AddCardOpRequest;
import com.tsm.modulith.resell.onepiece.dto.request.AddVenditaCartaOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddCartaOpResponse;
import com.tsm.modulith.resell.onepiece.dto.response.AddVenditaCartaOpResponse;
import com.tsm.modulith.resell.onepiece.entity.CarteOp;
import com.tsm.modulith.resell.onepiece.service.CarteOpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/op/")
@RequiredArgsConstructor
public class OpCartaController {


    private final CarteOpService carteOpService;


    // add sealed op
    @PostMapping("add/carta")
    public ResponseEntity<AddCartaOpResponse> addCartaOp(@RequestBody AddCardOpRequest addSealedOpRequest) {
        return ResponseEntity.ok(carteOpService.addCarta(addSealedOpRequest));
    }
    // delete sealed op
    @DeleteMapping("delete/carta/{idSealed}")
    public ResponseEntity<BaseResponse> deleteCartadOp(@PathVariable("idSealed") String idSealed) {
        return ResponseEntity.ok(carteOpService.cancellaCartaOp(idSealed));
    }
    // add vendita sealed op
    @PostMapping("add/vendita/carta")
    public ResponseEntity<AddVenditaCartaOpResponse> addVenditaCartaOp(@RequestBody AddVenditaCartaOpRequest request) {
        return ResponseEntity.ok(carteOpService.addVenditaCarta(request));
    }
    // fascia prezzo acquisto
    @GetMapping("get/carta/fascia/prezzo/{prezzoMin}/{prezzoMax}")
    public ResponseEntity<List<CarteOp>> getCarteByFasciaPrezzo(@PathVariable("prezzoMin") Double prezzoMin, @PathVariable("prezzoMax") Double prezzoMax) {
        return ResponseEntity.ok(carteOpService.getCartaOpByFasciaPrezzo(prezzoMin, prezzoMax));
    }
    // fascia date acquisto
    @GetMapping("get/carta/fascia/date/{dataMin}/{dataMax}")
    public ResponseEntity<List<CarteOp>> getCartaByFasciaDate(@PathVariable("dataMin") LocalDate dataMin, @PathVariable("dataMax") LocalDate dataMax) {
        return ResponseEntity.ok(carteOpService.getCartaOpByFasciaDataAcquisto(dataMin, dataMax));
    }
    // id
    @GetMapping("get/carta/id/{idSealed}")
    public ResponseEntity<CarteOp> getCartaById(@PathVariable("idSealed") String idCarta) {
        return ResponseEntity.ok(carteOpService.getIdCartaOP(idCarta));
    }
    // nome
    @GetMapping("get/carta/nome/{nomeSealed}")
    public ResponseEntity<List<CarteOp>> getCartaByNome(@PathVariable("nomeSealed") String nomeCarta) {
        return ResponseEntity.ok(carteOpService.getCartaOPByNome(nomeCarta));
    }

    // stato
    @GetMapping("get/carta/stato/{stato}")
    public ResponseEntity<List<CarteOp>> getCartaByStato(@PathVariable("stato") String stato) {
        return ResponseEntity.ok(carteOpService.getCartaOpByStato(stato));
    }

    // stato acquisto
    @GetMapping("get/carta/statoAcquisto/{statoAcquisto}")
    public ResponseEntity<List<CarteOp>> getCartaByStatoAcquisto(@PathVariable("statoAcquisto") String statoAcquisto) {
        return ResponseEntity.ok(carteOpService.getCartaOpByStatoAcquisto(statoAcquisto));
    }
}
