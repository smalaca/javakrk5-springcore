package com.smalaca.messagesender.example;

import org.springframework.data.jpa.domain.Specification;

class UsersGroupSpecification {
    static Specification<UsersGroup> nameContains(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }

    static Specification<UsersGroup> nameIs(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }
}
