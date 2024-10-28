package org.mystickers.java.repo;

import java.util.List;

import org.mystickers.java.model.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Integer>{
	
	List<Sticker> findAllOrderByCreatedAtDesc();

}
