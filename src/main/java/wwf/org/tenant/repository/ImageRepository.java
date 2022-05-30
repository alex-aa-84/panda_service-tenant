package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
