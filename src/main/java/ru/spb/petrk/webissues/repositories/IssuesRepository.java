package ru.spb.petrk.webissues.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ru.spb.petrk.webissues.issues.Status;
import ru.spb.petrk.webissues.model.Issue;

/**
 *
 * @author PetrK
 */
public interface IssuesRepository extends CrudRepository<Issue, Long> {
    
    @Override
    List<Issue> findAll();
    
    List<Issue> findByStatus(Status status);
    
}
