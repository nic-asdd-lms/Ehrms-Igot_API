package igot.ehrms.log;

import org.springframework.data.jpa.repository.JpaRepository;

import igot.ehrms.model.LogModel;

public interface LogRepository extends JpaRepository<LogModel, Long> {
}