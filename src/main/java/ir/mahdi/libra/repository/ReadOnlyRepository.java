package ir.mahdi.libra.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    long count();

    List<T> findAllById(Iterable<ID> ids);
}