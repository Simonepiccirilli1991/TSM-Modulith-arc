package com.tsm.modulith.resell.onepiece.dto.response;

import com.tsm.modulith.resell.onepiece.entity.SealedOp;

public record AddVenditaSealedOpResponse(
        String messaggio,
        SealedOp sealedOp
) {
}
