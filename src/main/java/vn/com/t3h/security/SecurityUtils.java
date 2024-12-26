package vn.com.t3h.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static final String PREFIX_ROLE="ROLE_";

    public static Set<String> getRolesCurrentUser() {
        // lấy ra thông tin quyền của user hiện tại đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roleCode = new HashSet<>();

        // kiểm tra user đã đăng nhập chưa
        if (authentication != null && authentication.isAuthenticated()) {
            // Nếu đã đăng nhập -> lấy danh sách quyền của user
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            System.out.println("LLLL"+roles);
            roleCode = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        }
        return roleCode;
    }
}
