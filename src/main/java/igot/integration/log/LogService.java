package igot.integration.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;

    public  LogModel createLog(LogModel log) {
        return logRepository.save(log);
    }
}
