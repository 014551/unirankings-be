package cz.cvut.fel.unirankings.common.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

  @NotBlank
  @Size(min = 3, max = 20)
  private final String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private final String email;

  @NotBlank
  @Size(min = 6, max = 40)
  private final String password;

  private final Set<String> role;

  public SignupRequest(String username, String email, String password, Set<String> role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Set<String> getRole() {
    return role;
  }
}
