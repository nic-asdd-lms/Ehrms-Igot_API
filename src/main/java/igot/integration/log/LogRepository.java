package igot.integration.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogModel, Long> {
}