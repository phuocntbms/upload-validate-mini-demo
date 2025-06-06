package bk.server_java_form_binding;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String title;
    private String iconUrl;



    @Override
    public String toString() {
        return this.title;
    }
}
