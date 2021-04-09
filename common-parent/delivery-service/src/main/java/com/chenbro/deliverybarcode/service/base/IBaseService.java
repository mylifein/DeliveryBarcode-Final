package com.chenbro.deliverybarcode.service.base;

import com.chenbro.deliverybarcode.model.CommonException;

import java.util.Set;

public interface IBaseService<T> {


    public T findByUUid(String uuid);

    public void deleteById(String uuid) throws CommonException;

    public void update(T t);

    public void insert(T t);




}
