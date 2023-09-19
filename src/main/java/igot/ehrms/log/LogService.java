package igot.ehrms.log;

import igot.ehrms.log.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igot.ehrms.model.LogModel;

@Service
public class LogService {
    
    @Autowired
    private LogRepository logRepository;

    public  LogModel createLog(LogModel log) {
        return logRepository.save(log);
    }
}
