package org.ragnarok.web.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ragnarok.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserRepoTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_HappyPath_ShouldReturn1User() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("USER");
        testEntityManager.persist(user);
        testEntityManager.flush();

        User actual = userRepository.findByUsername("username");

        assertThat(actual).isEqualTo(user);
    }

    public void save_HappyPath_ShouldSave1User() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole("USER");

        User actual = userRepository.save(user);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
    }
}
