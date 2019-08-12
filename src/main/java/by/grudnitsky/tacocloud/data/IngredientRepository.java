package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
