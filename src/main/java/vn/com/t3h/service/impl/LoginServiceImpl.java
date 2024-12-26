package vn.com.t3h.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.com.t3h.security.SecurityUtils;
import vn.com.t3h.service.ILoginService;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements ILoginService {

    @Override
    public String processAfterLogin() {
        Set<String> roleCode = SecurityUtils.getRolesCurrentUser();
        if (roleCode.contains("ROLE_ADMIN")){
            // tự động điều hướng sang dashboard khi có quyền admin
            return "redirect:/cms/dashboard";
        }
        return "redirect:/user/profile";
    }
}
