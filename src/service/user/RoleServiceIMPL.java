package service.user;

import config.ConfigReadAndWriteFile;
import model.Role;
import model.RoleName;

import java.util.List;

public class RoleServiceIMPL implements IRoleService{
public static String path = "D:\\java\\GIT\\MODULE 2\\HUMAN\\src\\data\\role.csv";
public static List<Role> roleList = new ConfigReadAndWriteFile<Role>().readFromFile(path);
    @Override
    public List<Role> findAll() {
        if (roleList.size()>=3){
            return roleList;
        }else {
            roleList.add(new Role(1,RoleName.STAFF));
            roleList.add(new Role(2,RoleName.ADMIN));
            roleList.add(new Role(3,RoleName.MANAGE));
        }
        return null;
    }

    @Override
    public void save() {
new ConfigReadAndWriteFile<Role>().writeToFile(path,roleList);
    }
public void add(Role role){
        roleList.add(role);
        save();
}
    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if (name==roleList.get(i).getName()){
                return roleList.get(i);
            }
        }
        return null;
    }
}
