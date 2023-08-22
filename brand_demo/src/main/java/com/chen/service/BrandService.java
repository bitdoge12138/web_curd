package com.chen.service;

import com.chen.mapper.BrandMapper;
import com.chen.pojo.Brand;
import com.chen.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {

    private SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactoryUtil();


    public List<Brand> selectAll() {


        SqlSession sqlSession = factory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands = brandMapper.selectAll();

        sqlSession.close();

        return brands;

    }


    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.add(brand);

        sqlSession.commit();
        sqlSession.close();

    }


    public Brand selectById(int id) {


        SqlSession sqlSession = factory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.selectById(id);

        sqlSession.close();

        return brand;

    }

    public void update(Brand brand) {

        SqlSession sqlSession = factory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.update(brand);

        sqlSession.commit();
        sqlSession.close();

    }

    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();


        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();

    }




}
