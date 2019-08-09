package by.grudnitsky.tacocloud.data;

import by.grudnitsky.tacocloud.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
