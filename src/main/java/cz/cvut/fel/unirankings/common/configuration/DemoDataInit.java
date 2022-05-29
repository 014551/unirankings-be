package cz.cvut.fel.unirankings.common.configuration;

import cz.cvut.fel.unirankings.common.model.User;
import cz.cvut.fel.unirankings.common.repository.RoleRepository;
import cz.cvut.fel.unirankings.common.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/** Initializes the application data for showing application demo. */
@Component
public class DemoDataInit implements ApplicationRunner {

  private final PasswordEncoder encoder;
  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  public DemoDataInit(
      PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository) {
    this.encoder = encoder;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    User user = new User("admin", "admin@gmail.com", encoder.encode("Nss2022Password"));
    user.setRoles(new HashSet<>(roleRepository.findAll()));
    userRepository.save(user);
  }
}
