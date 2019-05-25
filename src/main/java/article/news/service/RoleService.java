package article.news.service;

import article.news.constant.ErrorCode;
import article.news.dto.request.RoleRequest;
import article.news.exception.ErrorException;
import article.news.model.Role;
import article.news.repository.RoleRepository;
import article.news.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Handles all logic relating to Role Management
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get a single role by ID
    public Role getRole(Long id) {
        return getRole(id, false);
    }

    // Get a single role by ID. Throw EntityNotFoundException if role does not exist
    public Role getRole(Long id, Boolean shouldThrow) {
        Role role = roleRepository.findById(id).orElse(null);
        CommonUtil.throwEntityNotFound(shouldThrow, role);
        return role;
    }

    // Create a new role. Throw ErrorException if role name already exist
    public Role createRole(RoleRequest roleRequest) {
        Role existingRole = roleRepository.findByName(roleRequest.getName());
        if(existingRole != null)
            throw new ErrorException(ErrorCode.DUPLICATE_ENTRY, "A role with name (" + roleRequest.getName() + "). already exist.");

        return roleRepository.save(new Role(roleRequest.getName()));
    }

    // Updates an existing role. Throw EntityNotFoundException if role doesn't exist. Throw ErrorException if name already exist
    public Role updateRole(Long id, RoleRequest roleRequest) {
        Role role = getRole(id, true);
        Role existingRole = roleRepository.findByNameAndIdNot(roleRequest.getName(), role.getId());
        if(existingRole != null)
            throw new ErrorException(ErrorCode.DUPLICATE_ENTRY, "A role with name (" + roleRequest.getName() + "). already exist.");

        role.setName(roleRequest.getName());
        return roleRepository.save(role);
    }

    // Delete a role. Throw EntityNotFoundException if role doesn't exist
    public Boolean deleteRole(Long id) {
        roleRepository.delete(getRole(id, true));
        return true;
    }
}
