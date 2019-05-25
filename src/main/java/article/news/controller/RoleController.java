package article.news.controller;

import article.news.dto.request.RoleRequest;
import article.news.dto.response.DeleteResponse;
import article.news.dto.response.ResponseBuilder;
import article.news.model.Role;
import article.news.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manages CRUD operations for role
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */

@RestController
@Api(tags = {"Role Resource"})
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "List of available roles", response = Role.class, responseContainer = "List")
    @GetMapping
    public List<Role> getUsers() {
        return roleService.getAllRoles();
    }

    @ApiOperation(value = "Create a new role", response = Role.class)
    @PostMapping
    public ResponseEntity<Role> createUser(@RequestBody RoleRequest roleRequest) {
        Role role = roleService.createRole(roleRequest);
        return ResponseBuilder.created(role, role.getId());
    }

    @ApiOperation(value = "Get one role by id", response = Role.class)
    @GetMapping("{id}")
    public ResponseEntity<Role> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id, true));
    }

    @ApiOperation(value = "Update a role", response = Role.class)
    @PutMapping("{id}")
    public ResponseEntity<Role> updateUser(@PathVariable Long id, @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }

    @ApiOperation(value = "Delete a role", response = DeleteResponse.class)
    @DeleteMapping("{id}")
    public DeleteResponse DeleteUser(@PathVariable Long id) {
        return new DeleteResponse(roleService.deleteRole(id));
    }
}
