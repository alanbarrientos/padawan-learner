package balan.codes.crazylist.repository;

import balan.codes.crazylist.model.FailLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailLogsRepository extends JpaRepository<FailLogs, Integer> {
    FailLogs getFailLogsByName(String name);
}
