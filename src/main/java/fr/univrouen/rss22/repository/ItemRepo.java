package fr.univrouen.rss22.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.rss22.model.Item;

public interface ItemRepo extends JpaRepository<Item, Long >{


}
