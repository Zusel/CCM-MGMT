package de.itstimetoforget.backend.rest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "password")
public class Password {

    String name;
    String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static final class PasswordBuilder {
        private Password password;

        private PasswordBuilder() {
            password = new Password();
        }

        public static PasswordBuilder aPassword() {
            return new PasswordBuilder();
        }

        public PasswordBuilder withName(String name) {
            password.setName(name);
            return this;
        }

        public PasswordBuilder withValue(String value) {
            password.setPassword(value);
            return this;
        }

        public PasswordBuilder withOrder(Order order) {
            password.setOrder(order);
            return this;
        }

        public PasswordBuilder withId(Long id) {
            password.setId(id);
            return this;
        }

        public Password build() {
            return password;
        }
    }
}
