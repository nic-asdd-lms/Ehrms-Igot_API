package igot.integration.ehrms;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import igot.integration.model.ApiResponse;

public interface EhrmsService {
    
    

    ApiResponse profileUpdate(Map<String, Object> request, UUID id, String token) throws IOException, ParseException;
}
