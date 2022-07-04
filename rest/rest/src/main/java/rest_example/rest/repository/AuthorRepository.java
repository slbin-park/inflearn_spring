package rest_example.rest.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


@NoRepositoryBean
public interface JpaRepository <T,ID>     extends PagingAndSortingRepository<T,ID> , QueryByExampleExecutor<T> {

}
