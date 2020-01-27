package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredients;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredients, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredients ingredients) {

        if(ingredients == null){
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredients.getId());
        if(ingredients.getRecipe() != null){
            ingredientCommand.setRecipeId(ingredients.getRecipe().getId());
        }
        ingredientCommand.setAmount(ingredients.getAmount());
        ingredientCommand.setDescription(ingredients.getDescription());
        ingredientCommand.setUom(uomConverter.convert(ingredients.getUom()));

        return ingredientCommand;
    }
}
