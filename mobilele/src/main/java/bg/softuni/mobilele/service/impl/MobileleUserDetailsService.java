package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MobileleUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public MobileleUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {

    return userRepository
        .findByEmail(email)
        .map(MobileleUserDetailsService::map)
        .orElseThrow(
            () -> new UsernameNotFoundException("User with email " + email + " not found!"));
  }

  private static UserDetails map(UserEntity userEntity) {

    return new MobileleUserDetails(
        userEntity.getUuid(),
        userEntity.getEmail(),
        userEntity.getPassword(),
        userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(MobileleUserDetailsService::map).toList(),
        userEntity.getFirstName(),
        userEntity.getLastName()
    );
  }

  private static GrantedAuthority map(UserRoleEnum role) {
    return new SimpleGrantedAuthority(
        "ROLE_" + role
    );
  }
}
