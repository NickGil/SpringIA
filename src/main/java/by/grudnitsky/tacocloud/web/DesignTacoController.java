package by.grudnitsky.tacocloud.web;


import by.grudnitsky.tacocloud.Ingredient;
import by.grudnitsky.tacocloud.Order;
import by.grudnitsky.tacocloud.Taco;
import by.grudnitsky.tacocloud.User;
import by.grudnitsky.tacocloud.data.IngredientRepository;
import by.grudnitsky.tacocloud.data.TacoRepository;
import by.grudnitsky.tacocloud.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import by.grudnitsky.tacocloud.Ingredient.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private TacoRepository designRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository designRepo,
            UserRepository userRepo) {
        this.ingredientRepository = ingredientRepo;
        this.designRepo = designRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors
            , @ModelAttribute Order order){
        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = designRepo.save(taco);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        log.info("   --- Designing taco");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}