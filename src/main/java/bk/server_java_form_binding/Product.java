package bk.server_java_form_binding;

import javax.validation.constraints.*;
import lombok.Data;

@Data()
public class Product {
//    @NotBlank(message = "Tên sản phẩm không được để trống")
//    @Size(min = 3, max = 50, message = "Tên sản phẩm phải từ {min} đến {max} ký tự")
    private String name;

//    @NotNull(message = "Giá không được để trống")
//    @Min(value = 1000, message = "Giá phải từ {value} trở lên")
//    @Max(value = 100000000, message = "Giá không được vượt quá {value}")
    private Integer price;

    private String iconUrl;


    public String getIconUrl() {
        return "http://127.0.0.1:8080" + "/product/" +  iconUrl;
    }
}
