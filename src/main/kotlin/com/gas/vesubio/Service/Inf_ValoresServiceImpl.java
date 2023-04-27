package com.gas.vesubio.Service;

import com.gas.vesubio.Commons.GenericServiceImpl;
import com.gas.vesubio.DAO.Inf_ValoresDAO;
import com.gas.vesubio.Model.Inf_Valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Inf_ValoresServiceImpl extends GenericServiceImpl<Inf_Valores, Long> implements Inf_ValoresService{
    @Autowired
    private Inf_ValoresDAO valoresDao;
    @Override
    public CrudRepository<Inf_Valores, Long> getDao() {
        return null;
    }


}
