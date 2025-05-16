package com.hsys.dv1.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsys.dv1.Entities.App;
import com.hsys.dv1.Repositories.AppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    // Devuelve solo las apps públicas
    public List<App> findPublicApps() {
        return appRepository.findByIsPublicTrue();
    }

    // Por si querés traer todas las apps
    public List<App> findAllApps() {
        return appRepository.findAll();
    }
}

