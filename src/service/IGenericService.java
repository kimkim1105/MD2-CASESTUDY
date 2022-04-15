package service;

import model.Staff;

import java.util.List;

public interface IGenericService<T> {
    List<T> findAll();
    void save();
}
