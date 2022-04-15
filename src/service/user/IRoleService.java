package service.user;

import model.Role;
import model.RoleName;
import service.IGenericService;

public interface IRoleService extends IGenericService<Role> {

Role findByName(RoleName name);
}
