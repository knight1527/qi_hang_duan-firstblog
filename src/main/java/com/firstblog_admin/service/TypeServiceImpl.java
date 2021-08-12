package com.firstblog_admin.service;

import com.firstblog_admin.NotFoundException;
import com.firstblog_admin.dao.TypeRepository;
import com.firstblog_admin.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author knight1527
 * Created with IntelliJ IDEA.
 * User: suse_QiHang
 * Date: 2021/06/30 15:07
 * Description:
 * Version: V1.0
 */
@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        //typeRepository.findById(id).get()提示为null好像会抛出异常
        return typeRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type temp = typeRepository.findById(id).orElse(null);
        if(temp == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,temp);
        return typeRepository.save(temp);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
