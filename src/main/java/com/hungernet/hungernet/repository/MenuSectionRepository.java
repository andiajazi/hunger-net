package com.hungernet.hungernet.repository;

import com.hungernet.hungernet.entity.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, Long> {
    Optional<MenuSection> findMenuSectionByMenuIdAndSectionName(Long menuId, String sectionName);
}
