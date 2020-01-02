package com.example.demo.service.impl;

import com.example.demo.dto.BannerDto;
import com.example.demo.entity.BannerEntity;
import com.example.demo.repository.IBannerRepository;
import com.example.demo.service.IBannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BannerService implements IBannerService {
    @Autowired
    private IBannerRepository bannerRepository;
    @Override
    public List<BannerDto> getAllBanner(){
        List<BannerEntity> bannerEntityList = bannerRepository.findAll();
        List<BannerDto> m_bannerDtoList = new CopyOnWriteArrayList<>();
        for(BannerEntity bannerEntity : bannerEntityList){
            BannerDto bannerDto = new BannerDto();
            BeanUtils.copyProperties(bannerEntity,bannerDto);
            m_bannerDtoList.add(bannerDto);
        }
        return m_bannerDtoList;

    }
}
