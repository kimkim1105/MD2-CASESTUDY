package service.Staff;

import model.Staff;
import service.IGenericService;

import java.util.List;

public interface IstaffService extends IGenericService<Staff> {
    boolean existedByUsername(String username);
}
