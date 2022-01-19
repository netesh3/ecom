package com.nik.code.ecom.utils;

import java.util.List;

public interface DTOMapper<D, M> {

    public M toModel(D instance);

    public D toDTO(M instance);

    public List<M> toModel(List<D> instance);

    public List<D> toDTO(List<M> instance);
}
