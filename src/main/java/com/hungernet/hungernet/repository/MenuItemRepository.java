package com.hungernet.hungernet.repository;

import com.hungernet.hungernet.entity.MenuItem;
import com.hungernet.hungernet.entity.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByMenuSection(MenuSection menuSection);
}
