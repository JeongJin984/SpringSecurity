package com.example.SpringSecurity2.Service;

import com.example.SpringSecurity2.domain.entity.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ResourcesService {

    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}