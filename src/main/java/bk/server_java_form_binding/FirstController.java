package bk.server_java_form_binding;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller()
@RequestMapping("/product")
public class FirstController  {
    private final String UPLOAD_DIR = "C:/uploads/products"; // Thư mục upload cố định

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("add")
    public String addSubmit(@Valid @ModelAttribute("product") Product newProduct, BindingResult result, @RequestParam("icon") MultipartFile file) throws IOException {
        System.out.println("Product: " + newProduct);
        System.out.println("Result: " + result);
        if (result.hasErrors()) {
            return "addProduct";
        }
        if (!file.isEmpty()) {
            // Tạo thư mục uploads nếu chưa tồn tại
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // Lưu file
            String fileName = file.getOriginalFilename();
            String uploadPath = UPLOAD_DIR + "/" + fileName;
            file.transferTo(new File(uploadPath));
            System.out.println("Upload path: " + uploadPath);
            
            // Lưu đường dẫn truy cập file
            newProduct.setIconUrl("/product/images/" + fileName);
        }
        return "addProduct";
    }

    @GetMapping("/images/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String fileName) throws IOException {
        File file = new File(UPLOAD_DIR + "/" + fileName);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(file.toPath());
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.badRequest().build();
        }

        byte[] image = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }
}
