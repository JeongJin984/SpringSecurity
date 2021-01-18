package com.example.SpringSecurity2.Controller.admin;

import com.example.SpringSecurity2.Security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.SpringSecurity2.Service.ResourcesService;
import com.example.SpringSecurity2.domain.dto.ResourcesDto;
import com.example.SpringSecurity2.domain.entity.Resources;
import com.example.SpringSecurity2.domain.entity.Role;
import com.example.SpringSecurity2.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class ResourceController {

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourcesService resourcesService;

    @PostMapping(value="/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/admin/resources";
    }

}
