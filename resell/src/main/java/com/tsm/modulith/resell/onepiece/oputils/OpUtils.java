package com.tsm.modulith.resell.onepiece.oputils;

import com.tsm.modulith.resell.onepiece.exception.OpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Component
@Slf4j
public class OpUtils {


    private ObjectMapper objectMapper = new ObjectMapper();


    public String generaIdSealed(){
        var idSealed = "OP-SEAL-" + UUID.randomUUID();
        return idSealed;
    }

    public String generaIdCarta(){
        var idCarta = "OP-CARTA-" + UUID.randomUUID();
        return idCarta;
    }


    //TODO: inserire metodo per rimappare entity su dto generico per recap, vedere se possibile usare le deserelizzation feature

    public Object mapEntityToRecap(Object o, Class<?> c){
        try{
            return objectMapper.convertValue(o, c);
        }catch (Exception e){
            log.error("Error on map entity to recap with error: {}",e.getMessage());
            throw new OpException("Errore Remapping OP to recap","INTERNAL ERROR");
        }
    }
}
