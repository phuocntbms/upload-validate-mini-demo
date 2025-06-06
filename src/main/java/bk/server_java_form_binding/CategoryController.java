package bk.server_java_form_binding;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private static final String UPLOAD_DIR = "uploads";

    @GetMapping("add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("")
    public String addCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file) throws IOException {
        // Print the received data
        System.out.println("Received category title: " + category.getTitle());
        System.out.println("Received file name: " + file.getOriginalFilename());

        if (!file.isEmpty()) {
            // Create uploads directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Save the file
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR, fileName);
            Files.write(path, file.getBytes());
            
            // Set the icon URL in the category
            category.setIconUrl("/uploads/" + fileName);
        }

        System.out.println("New category added: " + category);
        return "redirect:/categories";
    }

    @GetMapping()
    public String getCategories(Model model) {
        List<Category> categoryList = Arrays.asList(
                new Category(1, "Laptop", "data:image/jpeg;base64,..."),
                new Category(2, "Smartphone", "data:image/jpeg;base64,..."),
                new Category(3, "Tablet", "data:image/jpeg;base64,...")
        );

        model.addAttribute("categories", categoryList);
        return "categories";
    }
}
