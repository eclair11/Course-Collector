package org.coursecollector.esi.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

/**
 *
 * @author
 */
@Entity
@Data
public class User {
    @Id
    String userName;

    String displayName;

    String derivedPassword;

    @Transient
    String newPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
        this.displayName = userName;
        this.roles.add(UserRole.USER); // be a user by default
        this.derivedPassword = null;
    }

    public User(String userName, String displayName, List<String> roles, String derivedPassword) {
        this.userName = userName;
        this.displayName = displayName;
        this.roles.addAll(roles.stream().map(UserRole::valueOf).collect(Collectors.toList()));
        this.derivedPassword = derivedPassword;
    }

}
