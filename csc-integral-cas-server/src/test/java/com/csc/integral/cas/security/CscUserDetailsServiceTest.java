package com.csc.integral.cas.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.csc.integral.identity.User;
import com.csc.integral.identity.dao.IdentityDao;

public class CscUserDetailsServiceTest extends TestCase {

    private IdentityDao identityDao;
    private UserDetailsService cscUserDetailsService;

    {
        identityDao = mock(IdentityDao.class);
        cscUserDetailsService = new CscUserDetailsService(identityDao);
    }

    public void testLoadUserByUsername() {
        String root = "root";
        String others = "others";
        User rootUserMock = createUserMock(root, root);
        User othersUserMock = createUserMock(others, others);

        when(identityDao.read(root)).thenReturn(rootUserMock);
        when(identityDao.read(others)).thenReturn(othersUserMock);

        UserDetails admin = cscUserDetailsService.loadUserByUsername(root);
        assertEquals(root, admin.getUsername());
        assertEquals(root, admin.getPassword());
        assertEquals(CscUserDetailsService.ROLE_ADMIN,
                admin.getAuthorities().iterator().next().getAuthority());
        assertEquals(true, admin.isAccountNonExpired());
        assertEquals(true, admin.isAccountNonLocked());
        assertEquals(true, admin.isCredentialsNonExpired());
        assertEquals(true, admin.isEnabled());

        UserDetails user = cscUserDetailsService.loadUserByUsername(others);
        assertEquals(others, user.getUsername());
        assertEquals(others, user.getPassword());
        assertEquals(CscUserDetailsService.ROLE_USER,
                user.getAuthorities().iterator().next().getAuthority());
        assertEquals(true, user.isAccountNonExpired());
        assertEquals(true, user.isAccountNonLocked());
        assertEquals(true, user.isCredentialsNonExpired());
        assertEquals(true, user.isEnabled());
    }

    public void testLoadUserByUsername_UserNotFound() {
        String username = "foobar";
        when(identityDao.read(username)).thenReturn(null);

        try {
            cscUserDetailsService.loadUserByUsername(username);
            fail();
        } catch (UsernameNotFoundException e) {
            // OK
        }
    }

    private User createUserMock(String username, String password) {
        User user = mock(User.class);

        when(user.getUserName()).thenReturn(username);
        when(user.getPassword()).thenReturn(password);

        return user;
    }

}
