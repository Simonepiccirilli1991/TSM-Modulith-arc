package com.tsm.modulith.resell.onepiece.dto.response;

import com.tsm.modulith.resell.onepiece.entity.SealedOp;

public record AddSealedOpResponse(

        String messaggio,
        SealedOp sealedOp
) {
}
