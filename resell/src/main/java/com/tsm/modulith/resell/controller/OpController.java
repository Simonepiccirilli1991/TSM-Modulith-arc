package com.tsm.modulith.resell.controller;

import com.tsm.modulith.resell.onepiece.dto.BaseResponse;
import com.tsm.modulith.resell.onepiece.dto.request.AddSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.request.AddVenditaSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddSealedOpResponse;
import com.tsm.modulith.resell.onepiece.dto.response.AddVenditaSealedOpResponse;
import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import com.tsm.modulith.resell.onepiece.service.SealedOpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/op")
@RequiredArgsConstructor
public class OpController {


    private final SealedOpService sealedOpService;

    // add sealed op
    @PostMapping("add/sealed")
    public ResponseEntity<AddSealedOpResponse> addSealedOp(AddSealedOpRequest addSealedOpRequest) {
        return ResponseEntity.ok(sealedOpService.addSealedOP(addSealedOpRequest));
    }
    // delete sealed op
    @DeleteMapping("delete/sealed/{idSealed}")
    public ResponseEntity<BaseResponse> deleteSealedOp(@PathVariable ("idSealed") String idSealed) {
        return ResponseEntity.ok(sealedOpService.deleteSealedById(idSealed));
    }
    // add vendita sealed op
    @PostMapping("add/vendita/sealed")
    public ResponseEntity<AddVenditaSealedOpResponse> addVenditaSealedOp(AddVenditaSealedOpRequest addVenditaSealedOpRequest) {
        return ResponseEntity.ok(sealedOpService.addVenditaSealedOP(addVenditaSealedOpRequest));
    }
    // fascia prezzo acquisto
    @GetMapping("get/sealed/fascia/prezzo/{prezzoMin}/{prezzoMax}")
    public ResponseEntity<List<SealedOp>> getSealedByFasciaPrezzo(@PathVariable("prezzoMin") Double prezzoMin, @PathVariable("prezzoMax") Double prezzoMax) {
        return ResponseEntity.ok(sealedOpService.getSealedOpByFasciaPrezzo(prezzoMin, prezzoMax));
    }
    // fascia date acquisto
    @GetMapping("get/sealed/fascia/date/{dataMin}/{dataMax}")
    public ResponseEntity<List<SealedOp>> getSealedByFasciaDate(@PathVariable("dataMin") LocalDate dataMin, @PathVariable("dataMax") LocalDate dataMax) {
        return ResponseEntity.ok(sealedOpService.getSealedOpByFasciaDataAcquisto(dataMin, dataMax));
    }
    // id
    @GetMapping("get/sealed/id/{idSealed}")
    public ResponseEntity<SealedOp> getSealedById(@PathVariable("idSealed") String idSealed) {
        return ResponseEntity.ok(sealedOpService.getIdSealedOP(idSealed));
    }
    // nome
    @GetMapping("get/sealed/nome/{nomeSealed}")
    public ResponseEntity<List<SealedOp>> getSealedByNome(@PathVariable("nomeSealed") String nomeSealed) {
        return ResponseEntity.ok(sealedOpService.getSealedOPByNome(nomeSealed));
    }

    // stato
    @GetMapping("get/sealed/stato/{stato}")
    public ResponseEntity<List<SealedOp>> getSealedByStato(@PathVariable("stato") String stato) {
        return ResponseEntity.ok(sealedOpService.getSealedOpByStato(stato));
     }

     // stato acquisto
    @GetMapping("get/sealed/statoAcquisto/{statoAcquisto}")
    public ResponseEntity<List<SealedOp>> getSealedByStatoAcquisto(@PathVariable("statoAcquisto") String statoAcquisto) {
        return ResponseEntity.ok(sealedOpService.getSealedOpByStatoAcquisto(statoAcquisto));
     }

}
