package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
