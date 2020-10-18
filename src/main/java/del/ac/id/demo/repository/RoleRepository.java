package del.ac.id.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import del.ac.id.demo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findById(int id);
	List<Role> findAll();
}
