package grupo5.control2_bda.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private Long id;
    private String name;
    private String email;
    private String password;
}
