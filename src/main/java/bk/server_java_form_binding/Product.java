package bk.server_java_form_binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data()
public class Product {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String name;
    private Integer price;
    private String iconUrl;
}
