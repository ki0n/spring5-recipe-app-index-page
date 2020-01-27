package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredients;
import guru.springframework.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredients> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredients convert(IngredientCommand source) {

        if(source == null){
            return null;
        }

        final Ingredients ingredients = new Ingredients();
        ingredients.setId(source.getId());

        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredients.setRecipe(recipe);
            recipe.addIngredient(ingredients);
        }

        ingredients.setAmount(source.getAmount());
        ingredients.setDescription(source.getDescription());
        ingredients.setUom(uomConverter.convert(source.getUom()));

        return ingredients;
    }
}
