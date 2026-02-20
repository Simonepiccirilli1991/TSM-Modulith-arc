package com.tsm.modulith.resell.controller;

import com.tsm.modulith.resell.onepiece.dto.request.AddSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddSealedOpResponse;
import com.tsm.modulith.resell.onepiece.service.SealedOpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/op")
@RequiredArgsConstructor
public class OpController {


    private final SealedOpService sealedOpService;

    @PostMapping("add/sealed")
    public ResponseEntity<AddSealedOpResponse> addSealedOp(AddSealedOpRequest addSealedOpRequest) {
        return ResponseEntity.ok(sealedOpService.addSealedOP(addSealedOpRequest));
    }

}
