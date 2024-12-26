package vn.com.t3h.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.com.t3h.entity.RoleEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("username is empty");
        }
        UserEntity userEntity = userRepository.findByUsernameAndDeletedIsFalse(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        // Tạo ra danh sách quyền của spring security
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // Lấy ra danh sách quyền của user
        Set<RoleEntity> roleEntities = userEntity.getRoleEntities();
        // duyệt danh sách quyền của user để lấy ra và đưa vào trong spring seucrity, để spring security xác định được
        // các quyền của user đó
        for (RoleEntity roleEntity : roleEntities) {
            // thềm từng phần tử quyền cho security
            grantedAuthorities.add(new SimpleGrantedAuthority(SecurityUtils.PREFIX_ROLE+roleEntity.getCode()));
        }
        // trả về đối tượng user với các thông tin username, passowrd, roles để spring security thực hiện
        // xác thực với username, password được truyền lên từ FE
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),userEntity.getPassword(),grantedAuthorities);
    }
}
