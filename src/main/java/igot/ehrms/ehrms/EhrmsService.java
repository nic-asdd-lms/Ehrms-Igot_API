package igot.ehrms.ehrms;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import igot.ehrms.model.ApiResponse;

public interface EhrmsService {
    
    

    ApiResponse profileUpdate(Map<String, Object> request, UUID id, String token) throws IOException, ParseException;
}
