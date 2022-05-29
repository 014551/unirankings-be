package cz.cvut.fel.unirankings.common.controller.response;

import java.util.List;

public class JwtResponse {

  private final String token;
  private final String type = "Bearer";
  private final Long id;
  private final String username;
  private final String email;
  private final List<String> roles;

  public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getToken() {
    return token;
  }

  public String getType() {
    return type;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public List<String> getRoles() {
    return roles;
  }
}
