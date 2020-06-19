package covidtest;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DiagnosisRepository extends PagingAndSortingRepository<Diagnosis, Long>{
    Optional<Diagnosis> findByInspectionId(Long inspectionId);

}